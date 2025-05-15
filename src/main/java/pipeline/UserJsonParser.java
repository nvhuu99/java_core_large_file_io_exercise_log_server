package pipeline;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.User;

public final class UserJsonParser extends LogPipeline {
    private final ObjectMapper mapper = new ObjectMapper();

    public UserJsonParser(LogPipeline next) {
        super(next);
    }

    @Override
    public void handle(Object data) throws Exception {
        if (data == EOF.SIGNAL) {
            pipe(EOF.SIGNAL);
        } else if (data instanceof String) {
            pipe(mapper.readValue((String)data, User.class));
        }
    }
}
