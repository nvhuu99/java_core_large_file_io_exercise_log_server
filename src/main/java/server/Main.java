package server;

import builder.PipelinesBuilder;

public class Main {
    public static void main(String[] args)throws Exception{
        if(args.length<3){ System.err.println("Usage: <wordfreq/userfilter/logreport> <inputFilePath> <outputDir>"); return; }
        String m=args[0],in=args[1],out=args[2];
        switch(m){
            case "wordfreq": PipelinesBuilder.buildWordFreq(in,out); break;
            case "userfilter": PipelinesBuilder.buildUserFilter(in,out); break;
            case "logreport": PipelinesBuilder.buildLogReport(in,out); break;
            default: System.err.println("Unknown mode: "+m); return;
        }
        System.out.println("Pipeline "+m+" completed.");
    }
}