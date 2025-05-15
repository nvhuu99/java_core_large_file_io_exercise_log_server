package pipeline;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

public final class WordFrequencyOutput extends LogPipeline {
    private final String outDir;

    public WordFrequencyOutput(String outDir, LogPipeline next) throws Exception {
        super(next);
        this.outDir = outDir;
        Files.createDirectories(Paths.get(outDir));
    }

    @Override
    public void handle(Object data) throws Exception {
        if (data instanceof Map) {
            Map<String, Integer> freq = (Map)data;
            String fileName = "keyword_frequency_" + System.currentTimeMillis() + ".txt";
            Path outputPath = Paths.get(outDir, fileName);

            try (BufferedWriter w = Files.newBufferedWriter(outputPath)) {
                freq.forEach((k, v) -> writeEntry(w, k, v));
            }
            pipe(outputPath.toString());
        }
    }

    private void writeEntry(BufferedWriter writer, String key, Object value) {
        try {
            writer.write(key + "," + value);
            writer.newLine();
        } catch (IOException ignored) {}
    }
}
