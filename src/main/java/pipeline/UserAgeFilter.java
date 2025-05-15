package pipeline;

import model.User;

import java.io.BufferedWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public final class UserAgeFilter extends LogPipeline {
    private final BufferedWriter below, above;

    public UserAgeFilter(String outDir, LogPipeline next) throws Exception {
        super(next);
        Path d = Paths.get(outDir);
        Files.createDirectories(d);
        below = Files.newBufferedWriter(d.resolve("users_below_30.csv"));
        above = Files.newBufferedWriter(d.resolve("users_above_30.csv"));
        below.write("name,age,address,email\n");
        above.write("name,age,address,email\n");
    }

    @Override
    public void handle(Object data) throws Exception {
        if (data == null) { below.close(); above.close(); }
        else if (data instanceof User u) {
            var w = u.getAge() < 30 ? below : above;
            w.write(u.getName()+","+u.getAge()+","+u.getAddress()+","+u.getEmail()+"\n");
        }
    }
}
