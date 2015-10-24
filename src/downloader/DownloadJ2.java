package src.downloader;

import src.utils.Constants;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by ahmetkucuk on 25/09/15.
 *
 * Sample Class to try downloader
 *
 */
public class DownloadJ2 {


    public static void main(String[] args) throws IOException {

        downloadImage("http://gs671-suske.ndc.nasa.gov/api/v1/getJP2Image/?date=2014-01-01T23:59:59Z&instrument=AIA&observatory=SDO&detector=AIA&measurement=171",
                Constants.IMAGE_FILE_LOCATION,
                "sample.jp2"
        );


    }
    public static void downloadImage(String sourceUrl, String targetDirectory, String targetFileName)
            throws MalformedURLException, IOException, FileNotFoundException
    {
        URL imageUrl = new URL(sourceUrl);
        try (InputStream imageReader = new BufferedInputStream(
                imageUrl.openStream());
             OutputStream imageWriter = new BufferedOutputStream(
                     new FileOutputStream(targetDirectory + File.separator
                             + targetFileName));)
        {
            int readByte;

            while ((readByte = imageReader.read()) != -1)
            {
                imageWriter.write(readByte);
            }
        }
    }
}
