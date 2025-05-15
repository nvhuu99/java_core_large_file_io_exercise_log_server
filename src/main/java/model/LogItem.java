package model;

import java.time.Instant;

public final class LogItem {
    private final Instant timestamp;
    private final String level;
    private final String message;
    public LogItem(Instant ts,String lvl,String msg){timestamp=ts;level=lvl;message=msg;}
    public Instant getTimestamp(){return timestamp;}
    public String getLevel(){return level;}
    public String getMessage(){return message;}
}
