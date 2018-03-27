import org.apache.commons.io.IOUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.URL;

/**
 * Created by IntelliJ IDEA.
 * User: Kyle
 * Date: 1/14/11
 * Time: 10:01 AM
 */
public class Download {


    private static class ProgressListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // e.getSource() gives you the object of DownloadCountingOutputStream
            // because you sett it interface overriden afterWrite method
            int clientSize = 26028548;
            long bytes = ((DownloadCountingOutputStream) e.getSource()).getByteCount();
            Design.label_status.setText("- Downloading... " + bytes + " bytes saved.");
            int progress = (int) (100 - (((clientSize - bytes) / (double) clientSize) * 100));
            //System.out.println(progress);
            Design.progressBar.setValue(progress);
            //("DeltaScape - " + progress + "%");
        }
    }

    public static void download() {
        URL dl;
        File fl;
        ProgressListener progressListener = new ProgressListener();
        try {
            fl = new File(System.getProperty("user.home").replace("\\", "/") + "/Desktop/DeltaScape Client.zip");
            dl = new URL("http://deltascape.googlecode.com/files/DeltaScape%20Client.zip");
            OutputStream os = new FileOutputStream(fl);
            InputStream is = dl.openStream();

            DownloadCountingOutputStream dcount = new DownloadCountingOutputStream(os);
            dcount.setListener(progressListener);

            // this line give you the total length of source stream as a String.
            // you may want to convert to integer and store this value to
            // calculate percentage of the progression.
            // int size = Integer.parseInt(dl.openConnection().getHeaderField("Content-Length"));//calculate bytes of required download
            // System.out.println(size);
            IOUtils.copy(is, dcount);//begin transfer

            os.close();//close streams
            is.close();//^
        } catch (Exception e) {
            // System.out.println(e);
        }
    }

    public static void update() {
        boolean updated;
        Design.label_status.setText("- Checking for updates...");
        double latestVersion = 0;
        try {         //get the version number
            String inputLine;
            URL url = new URL("http://deltascape.googlecode.com/svn/version.txt");
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
            while ((inputLine = in.readLine()) != null)
                latestVersion = Double.parseDouble(inputLine.trim());
            in.close();
            //System.out.println("The version is: " + latestVersion);
        } catch (Exception x) {
            //System.out.println(x);
        }
        if (Design.version != latestVersion) {
            JOptionPane.showMessageDialog(null, "An update is available. Click OK to update.", "Updater", JOptionPane.INFORMATION_MESSAGE);
            try {//download latest version
                String fl = "Client Downloader V" + latestVersion + ".jar", dl = "http://deltascape.googlecode.com/files/Client%20Downloader%20V" + latestVersion + ".jar";
                URL x = new URL(dl);
                OutputStream os = new FileOutputStream(fl);
                InputStream is = x.openStream();
                IOUtils.copy(is, os);//begin transfer

                os.close();//close streams
                is.close();//^
                updated = true;

            } catch (Exception e) {    //can't download so no update is available.
                updated = false;
                // System.out.println(e);
            }
            if (updated) {
                try {
                    File file = new File("Client Downloader V" + Design.version + ".jar");
                    Runtime.getRuntime().exec(new String[]{"java", "-jar", "Client Downloader V" + latestVersion + ".jar"});
                    System.exit(0);
                } catch (java.io.IOException err) {
                    //System.out.println(err);
                }
            }
        } else {
            Design.label_status.setText("- You are up to date.");
            try {
                Thread.sleep(650);
            } catch (InterruptedException e1) {
                //e1.printStackTrace();
            }
        }
    }

    public static void run() {
        // startTime = System.currentTimeMillis();
        update();
        Design.label_status.setText("- Initializing download");
        download();
        unZip.unzip();
        //   endTime = System.currentTimeMillis();
        //   JOptionPane.showMessageDialog(null, "Downloaded file in " + (float) (endTime - startTime) / 1000 + " seconds.");
    }

    //  private static long startTime;
    //  private static long endTime;
}