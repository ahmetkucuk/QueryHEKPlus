package src.downloader;

/**
 * Created by ahmetkucuk on 24/10/15.
 */
public class DownloadRunner {

    static final String INPUT_FILE = "/Users/ahmetkucuk/Documents/GEORGIA_STATE/COURSES/Database_Systems/Project/image_downloader/SG_Records_Sample.txt";
    static final String OUTPUT_DIR = "/Users/ahmetkucuk/Documents/GEORGIA_STATE/COURSES/Database_Systems/Project/image_downloader/images/";
    static final String[] imageUrlAttributes = new String[]{"ref_url_1", "gs_imageurl"};
    static final int waitBetween = 0;


    public static void main(String[] args) {
        new DownloadFromFeatures(INPUT_FILE, OUTPUT_DIR, imageUrlAttributes, 10, waitBetween).run();
    }
}
