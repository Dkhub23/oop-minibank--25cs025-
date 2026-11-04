
import java.nio.file.*;
import java.nio.file.attribute.*;
import java.util.*;
import java.io.*;

public class FolderReport {

    public static void main(String[] args) throws IOException {

        Path folder = Path.of(".");

        List<String> report = new ArrayList<>();

        int count = 0;
        long totalSize = 0;

        report.add("----- FILE REPORT -----");

        try (DirectoryStream<Path> files =
                     Files.newDirectoryStream(folder)) {

            for (Path file : files) {

                if (Files.isRegularFile(file)) {

                    BasicFileAttributes attr =
                            Files.readAttributes(file, BasicFileAttributes.class);

                    String name = file.getFileName().toString();
                    long size = attr.size();
                    String time = attr.lastModifiedTime().toString();

                    report.add("Name: " + name);
                    report.add("Size: " + size + " bytes");
                    report.add("Modified: " + time);
                    report.add("----------------------");

                    count++;
                    totalSize += size;
                }
            }
        }

        report.add("Total Files: " + count);
        report.add("Total Size: " + totalSize + " bytes");

        Files.write(Path.of("report.txt"), report);

        System.out.println("Report created successfully!");
        System.out.println("Total Files: " + count);
        System.out.println("Total Size: " + totalSize + " bytes");
    }
}
