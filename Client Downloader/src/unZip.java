import java.io.*;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class unZip {
    static String desktop = System.getProperty("user.home").replace("\\", "/") + "/Desktop/";
    static String zipFile = "DeltaScape Client.zip";

    public static void unzip() {
        int unzipCount = 0;
        try {
            ZipFile ZipFile = new ZipFile(desktop + zipFile);
            unzipCount = ZipFile.size();
            for (Enumeration Entries = ZipFile.entries(); Entries.hasMoreElements();) {
                ZipEntry Entry = (ZipEntry) Entries.nextElement();
                Design.label_status.setText(/*"Extracted: " + Entry +*/"Extracting - files remaining: " + (unzipCount -= 1));
                if (Entry.isDirectory()) {
                    (new File(desktop + Entry.getName())).mkdir();
                } else {
                    writeStream(ZipFile.getInputStream(Entry), new BufferedOutputStream(new FileOutputStream(desktop + Entry.getName())));
                }
            }
            ZipFile.close();
        } catch (Exception e) {
            // e.printStackTrace();
        }
        deleteFile();
    }

    public static void writeStream(InputStream In, OutputStream Out) throws IOException {
        byte Buffer[] = new byte[4096];
        int Len;
        while ((Len = In.read(Buffer)) >= 0) {
            Out.write(Buffer, 0, Len);
        }
        In.close();
        Out.close();
    }

    public static void deleteFile() {
        try {
            File file = new File(desktop + zipFile);
            file.delete();
        } catch (Exception e) {
            //  e.printStackTrace();
        }
    }
}