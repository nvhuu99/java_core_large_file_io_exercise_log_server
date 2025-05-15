package pipeline;

import model.LogItem;

import java.time.Instant;

public final class LogItemParser extends LogPipeline {
    public LogItemParser(LogPipeline next){
        super(next);
    }

    @Override
    public void handle(Object data) throws Exception {
        if (data == EOF.SIGNAL) {
            pipe(EOF.SIGNAL);
        } else if (data instanceof String){
            String[] parts = ((String)data).split(" - ", 3);
            pipe(new LogItem(Instant.parse(parts[0]), parts[1], parts[2]));
        }
    }
}

