package pipeline;

public abstract class LogPipeline {
    private final LogPipeline next;

    protected LogPipeline(LogPipeline next) {
        this.next = next;
    }

    public abstract void handle(Object data) throws Exception;

    protected void pipe(Object data) throws Exception {
        if (next != null) next.handle(data);
    }
}
