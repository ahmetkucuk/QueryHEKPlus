package src.domain;

import java.io.*;
import java.util.*;

/**
 * Created by ahmetkucuk on 11/10/15.
 */
public class DomainExplorer {

    private Map<String, Set<String>> attrByValue;
    private static final String SEPARATOR = "\t";
    private static final int MAX_EXAMPLE_COUNT = 3;

    public static final String INPUT_FILE = "/Users/ahmetkucuk/Documents/Research/DNNProject/formattedop/AR/RECORD/AR_Records.txt";
    public static final String OUTPUT_FILE = "/Users/ahmetkucuk/Documents/Research/DNNProject/formattedop/AR/RECORD/AR_Domain.txt";

    public DomainExplorer() {
        attrByValue = new HashMap<>();
    }

    public void processFile(String inputFileName) {

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(new File(inputFileName))));
            String[] headers = reader.readLine().split(SEPARATOR);

            for(String s: headers) {
                attrByValue.put(s, new HashSet<String>());
            }

            String line = null;
            while((line = reader.readLine()) != null) {
                String[] columnValues = line.split(SEPARATOR);
                if(columnValues.length != headers.length) {
                    System.err.println("There is a huge problem with data file");
                }

                for(int i = 0; i < columnValues.length; i++) {
                    attrByValue.get(headers[i]).add(columnValues[i]);
                }
            }

            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void writeResults(String outputFile) {

        try {
            // Create file
            FileWriter fStream = new FileWriter(outputFile);
            BufferedWriter out = new BufferedWriter(fStream);

            out.write("Attribute Type" + SEPARATOR + "numberOfDiffValues" + SEPARATOR + "min" + SEPARATOR + "max" + SEPARATOR + "Example\n" );
            for(Map.Entry<String, Set<String>> entry: attrByValue.entrySet()) {
                Set<String> attrValues = entry.getValue();
                String lineToWrite = entry.getKey();

                Collections.max(attrValues);

                String sampleValues = "";
                int maxNumberOfSampleValues = 0;

                for(String s: attrValues) {
                    sampleValues = sampleValues + " " + s;
                    if(maxNumberOfSampleValues > MAX_EXAMPLE_COUNT)
                        break;
                    maxNumberOfSampleValues++;
                }

                String max = Collections.max(attrValues);
                String min = Collections.min(attrValues);

                lineToWrite = lineToWrite + SEPARATOR + attrValues.size() + SEPARATOR + max + SEPARATOR + min + SEPARATOR + sampleValues.trim();

                out.write(lineToWrite + "\n");
            }


            out.flush();
            //Close the output stream
            out.close();
        } catch (Exception e) {//Catch exception if any
            System.err.println("Error: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        DomainExplorer domainExplorer = new DomainExplorer();
        System.out.println("Started Processing File");
        domainExplorer.processFile(INPUT_FILE);
        System.out.println("File Processing is finished. Output File started.");
        domainExplorer.writeResults(OUTPUT_FILE);
        System.out.println("Output Finished. Finished!");
    }

}
