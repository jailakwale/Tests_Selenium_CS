package utility;

import java.io.File;

public class ReportCleaner {
    public static void cleanReports() {
        String[] reportDirs = {
            "target/surefire-reports",
            "test-output"
        };
        for (String dirPath : reportDirs) {
            File dir = new File(System.getProperty("user.dir"), dirPath);
            if (dir.exists() && dir.isDirectory()) {
                for (File file : dir.listFiles()) {
                    if (file.isFile() && !file.getName().equals(".gitkeep")) {
                        file.delete();
                    }
                }
            }
        }
    }
}
