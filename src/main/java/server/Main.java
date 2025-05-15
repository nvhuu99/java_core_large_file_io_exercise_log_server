package server;

import builder.PipelinesBuilder;
import java.util.Scanner;

public class Main {
    public static void main(String[] args)throws Exception{
        Scanner scanner = new Scanner(System.in);

        System.out.print("Chọn chế độ (wordfreq/userfilter/logreport): ");
        String mode = scanner.nextLine().trim();

        System.out.print("Đường dẫn file đầu vào: ");
        String inputPath = scanner.nextLine().trim();

        System.out.print("Thư mục đầu ra: ");
        String outDir = scanner.nextLine().trim();

        switch (mode) {
            case "wordfreq":
                PipelinesBuilder.buildWordFreq(inputPath, outDir);
                break;
            case "userfilter":
                PipelinesBuilder.buildUserFilter(inputPath, outDir);
                break;
            case "logreport":
                PipelinesBuilder.buildLogReport(inputPath, outDir);
                break;
            default:
                System.err.println("Chế độ không hợp lệ.");
                scanner.close();
                return;
        }
        System.out.println("Pipeline " + mode + " hoàn tất.");
        scanner.close();
    }
}