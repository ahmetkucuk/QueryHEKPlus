package src.downloader;

import java.io.File;
import java.net.MalformedURLException;

/**
 * Created by ahmetkucuk on 22/10/15.
 */
public class DownloadFromFeatures extends BaseImageDownloader {


    protected String[] imageUrlAttributes;
    private int limit;
    private int waitBetween;

    public DownloadFromFeatures(String inputFile, String outputDir, String[] imageUrlAttributes, int limit, int waitBetween) {
        super(inputFile, outputDir);
        this.imageUrlAttributes = imageUrlAttributes;
        this.limit = limit;
        this.waitBetween = waitBetween;
    }

    public void run() {
        downloadImageFromFile();
    }

    private void downloadImageFromFile() {
        int[] indexOfSelectedFeatures = findIndexesOfAttributes();

        String[] record = null;
        int limitCounter = 0;
        while((record = nextRecord()) != null) {
            if(limitCounter > limit) break;

            try {
                Thread.sleep(waitBetween * 1000 * 60);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            for(int i = 0; i < indexOfSelectedFeatures.length; i++) {
                String imageUrl = record[indexOfSelectedFeatures[i]];
                try {
                    downloadImage(imageUrl, imageUrl.substring(imageUrl.lastIndexOf(File.separator)));
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                    System.out.println("feature is not a url: " + imageUrlAttributes[i]);
                }
            }
            limitCounter++;
        }

    }

    private int[] findIndexesOfAttributes() {

        int[] indexOfSelectedFeatures = new int[imageUrlAttributes.length];
        String[] attributes = getAttributes();

        for(int i = 0; i < imageUrlAttributes.length; i++) {
            indexOfSelectedFeatures[i] = findIndex(attributes, imageUrlAttributes[i]);
        }
        return indexOfSelectedFeatures;
    }


}
