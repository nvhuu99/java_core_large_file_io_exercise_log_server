package pipeline;

import model.LogItem;

import java.io.BufferedWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

public final class LogLevelReport extends LogPipeline {
    private final Map<String,Integer> counts = new HashMap<>();
    private final Map<String,Instant> last = new HashMap<>();
    private final Map<String,Long> deltas = new HashMap<>();
    private final Path outDir;

    public LogLevelReport(String outDir, LogPipeline next) throws Exception {
        super(next);
        this.outDir = Paths.get(outDir);
        Files.createDirectories(this.outDir);
    }

    @Override
    public void handle(Object data) throws Exception {
        if (data == EOF.SIGNAL) {
            var outPath = outDir.resolve("log_level_occurrence_report.csv");
            try (BufferedWriter w = Files.newBufferedWriter(outPath)) {
                w.write("log_level,average_occur_time_seconds\n");
                for (var lvl: counts.keySet()){
                    long c = counts.get(lvl);
                    long sum = deltas.getOrDefault(lvl, 0L);
                    double avg = c > 1 ? (double)(sum/(c-1)) : 0.0;
                    w.write(lvl + "," + avg + "\n");
                }
            }
            pipe(EOF.SIGNAL);
        }
        else if (data instanceof LogItem it) {
            String lvl = it.getLevel();
            Instant ts = it.getTimestamp();
            if (last.containsKey(lvl)){
                long d = Duration.between(last.get(lvl), ts).getSeconds();
                deltas.merge(lvl, d, Long::sum);
                counts.merge(lvl, 1, Integer::sum);
            } else {
                counts.put(lvl, 1);
            }
            last.put(lvl, ts);
        }
    }
}
