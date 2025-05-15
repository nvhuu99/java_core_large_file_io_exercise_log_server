package builder;

import pipeline.*;

public class PipelinesBuilder {
    public static void buildWordFreq(String in,String out)throws Exception{
        new LineReader(in,new TrackWordFrequency(new WordFrequencyOutput(out,null)));
    }
    public static void buildUserFilter(String in,String out)throws Exception{
        new LineReader(in,new UserJsonParser(new UserAgeFilter(out,null)));
    }
    public static void buildLogReport(String in,String out)throws Exception{
        new LineReader(in,new LogItemParser(new LogLevelReport(out,null)));
    }
}
