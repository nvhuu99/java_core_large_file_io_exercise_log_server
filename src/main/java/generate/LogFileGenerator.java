package generate;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

class LogFileGenerator {
    public static void main(String[] args) throws IOException {
        String path = args.length > 0 ? args[0] : "large_application.log";
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(path))) {
            for (int i = 1; i <= Integer.parseInt(args[1]); i++) {
                writer.write("This is a test log line");
                writer.newLine();
            }
        }
        System.out.println("Generated\n" + path);
    }
}
