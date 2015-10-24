package src.downloader;

import src.utils.EventFileReader;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by ahmetkucuk on 24/10/15.
 */
public abstract class BaseImageDownloader {

    protected BufferedWriter out;
    protected String inputFile;
    protected String outputDir;

    protected static final String SEPARATOR = "\t";

    public BaseImageDownloader(String inputFile, String outputDir) {
        this.inputFile = inputFile;
        this.outputDir = outputDir;
        EventFileReader.init(inputFile);
    }

    protected String[] nextRecord() {
        return EventFileReader.getInstance().next();
    }

    protected String[] getAttributes() {
        return EventFileReader.getInstance().getAttributes();
    }

    protected int findIndex(String[] features, String value) {
        for(int i = 0; i < features.length; i++) {
            if(features[i].equalsIgnoreCase(value))
                return i;
        }
        return -1;
    }

    protected void downloadImage(String sourceUrl, String targetFileName) throws MalformedURLException {

        URL imageUrl = new URL(sourceUrl);
        try (InputStream imageReader = new BufferedInputStream(
                imageUrl.openStream());
             OutputStream imageWriter = new BufferedOutputStream(
                     new FileOutputStream(outputDir + File.separator
                             + targetFileName))) {
            int readByte;

            while ((readByte = imageReader.read()) != -1)
            {
                imageWriter.write(readByte);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
