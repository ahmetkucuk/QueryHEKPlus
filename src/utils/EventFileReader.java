package src.utils;

import java.io.*;

/**
 * Created by ahmetkucuk on 04/10/15.
 */
public class EventFileReader {


    public static final String SEPARATOR = "\t";
    private static EventFileReader instance;
    private BufferedReader reader;
    private String[] attributes;

    private int problematicRecord = 0;
    private int indexOfNextElement = 1;

    private EventFileReader(){}

    public static void init(String fileName) {
        instance = new EventFileReader();
        instance.loadFileContent(fileName);
    }

    public static EventFileReader getInstance() {
        if(instance == null) {
            System.err.println("You have to init before you call getInstance");
        }
        return instance;
    }

    private void loadFileContent(String inputFile) {
        FileInputStream fStream1 = null;
        try {
            fStream1 = new FileInputStream(inputFile);
            DataInputStream in = new DataInputStream(fStream1);
            reader = new BufferedReader(new InputStreamReader(in));

            attributes = reader.readLine().split(SEPARATOR);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String[] getAttributes() {
        return attributes;
    }

    public String[] next() {
        String line = null;
        try {
            line = reader.readLine();
            if(line == null) return null;

            return line.split(SEPARATOR);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getStats() {
        return "problem: " + problematicRecord + " total index: " + indexOfNextElement;
    }

}
