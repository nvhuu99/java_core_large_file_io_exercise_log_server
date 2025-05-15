package generate;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;

class UserJsonFileGenerator {
    private static final String[] NAMES = {"Alice","Bob","Carol","Dave","Eve"};
    public static void main(String[] args) throws IOException {
        String path = args.length > 0 ? args[0] : "large_users.json";
        Random rnd = new Random();
        int total = Integer.parseInt(args[1]);
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(path))) {
            for (int i = 1; i <= total; i++) {
                String json = String.format("{\"name\":\"%s%d\",\"age\":%d,\"address\":\"Addr%d\",\"email\":\"user%d@example.com\"}",
                        NAMES[rnd.nextInt(NAMES.length)], i, rnd.nextInt(100) + 1, i, i);
                writer.write(json);
                writer.newLine();
            }
        }
        System.out.println("Generated\n" + path);
    }
}
