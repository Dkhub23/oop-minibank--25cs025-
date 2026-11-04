
import java.nio.file.*;
import java.nio.file.attribute.*;
import java.io.*;
import java.util.*;

public class LogAnalyzer {

    public static void main(String[] args) throws IOException {

        // Create files
        Files.write(Path.of("log1.txt"), Arrays.asList(
                "INFO: Server started",
                "ERROR: Database failed",
                "INFO: User logged in"
        ));

        Files.write(Path.of("log2.txt"), Arrays.asList(
                "INFO: Application running",
                "ERROR: Connection failed",
                "WARNING: Low memory"
        ));

        Files.write(Path.of("log3.txt"), Arrays.asList(
                "INFO: Request received",
                "INFO: Request completed",
                "ERROR: Server timeout"
        ));

        String keyword = "ERROR";

        int totalLines = 0;
        int errorLines = 0;

        Path[] files = {
                Path.of("log1.txt"),
                Path.of("log2.txt"),
                Path.of("log3.txt")
        };

        for (Path path : files) {

            System.out.println("\nFile: " + path);

            int fileLines = 0;

            // Read file line by line
            try (BufferedReader br = Files.newBufferedReader(path)) {

                String line;

                while ((line = br.readLine()) != null) {

                    fileLines++;
                    totalLines++;

                    if (line.contains(keyword)) {
                        errorLines++;
                    }
                }
            }

            // Get file attributes
            BasicFileAttributes attr =
                    Files.readAttributes(path, BasicFileAttributes.class);

            System.out.println("Total Lines: " + fileLines);
            System.out.println("File Size: " + attr.size() + " bytes");
        }

        System.out.println("\n----- FINAL REPORT -----");
        System.out.println("Total Lines: " + totalLines);
        System.out.println("Lines containing ERROR: " + errorLines);

    }
}
