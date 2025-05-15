package generate;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Instant;
import java.util.Random;

class ServerLogFileGenerator {
    private static final String[] LEVELS = {"INFO","WARN","ERROR"};
    public static void main(String[] args) throws IOException {
        String path = args.length > 0 ? args[0] : "large_server.log";
        Instant start = Instant.parse("2025-01-01T00:00:00Z");
        Random rnd = new Random();
        int last = 0;
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(path))) {
            for (int i = 0; i <= Integer.parseInt(args[1]); i++) {
                last = last + rnd.nextInt(1, 50);
                Instant ts = start.plusSeconds(last);
                String level = LEVELS[i % LEVELS.length];
                writer.write(ts.toString() + " - " + level + " - Message number " + i);
                writer.newLine();
            }
        }
        System.out.println("Generated\n" + path);
    }
}
