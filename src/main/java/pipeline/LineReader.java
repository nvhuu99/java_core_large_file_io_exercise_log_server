package pipeline;

import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Paths;

public final class LineReader extends LogPipeline {
    public LineReader(String path, LogPipeline next) throws Exception {
        super(next);
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(path))) {
            String line;
            while ((line = reader.readLine()) != null) {
                pipe(line);
            }
        }
        pipe(EOF.SIGNAL);
    }

    @Override
    public void handle(Object data) {}
}
