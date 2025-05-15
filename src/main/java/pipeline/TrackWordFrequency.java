package pipeline;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public final class TrackWordFrequency extends LogPipeline {
    private final Map<String,Integer> freq = new HashMap<>();

    public TrackWordFrequency(LogPipeline next) {
        super(next);
    }

    @Override
    public void handle(Object data) throws Exception {
        if (data == EOF.SIGNAL) {
            pipe(Collections.unmodifiableMap(freq));
            pipe(EOF.SIGNAL);
        }
        else if (data instanceof String) {
            for (String w : ((String) data).split("\\s+")) {
                if (!w.isBlank()) {
                    freq.merge(w, 1, Integer::sum);
                }
            }
        }
    }
}
