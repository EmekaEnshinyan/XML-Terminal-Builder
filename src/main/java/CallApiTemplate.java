import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

import static java.lang.System.in;

public class CallApiTemplate extends TxtFile{
    static String[] sourceList;
    static String[] targetExtract;
    static String sourceCode;
    static String targetCode;
    public static String apiSentence;

    public static void main(String[] args) throws IOException{

    }
    public static String callApi(String url) throws IOException {

        BufferedReader sourceReader = new BufferedReader(new FileReader(returnDatabase()));
        //make source array
        String sourceElements = sourceReader.readLine().replaceAll("\\[", "").replaceAll("]", "");
        sourceList = sourceElements.split(",\\s");
        for (String words : sourceList) {
            System.out.println(words);
        }
        sourceReader.close();

        //result: Array of target sentences to be input into url
        BufferedReader targetReader = Files.newBufferedReader(Paths.get(returnDatabase()));
        for (int i = 0; i < 1; i++) {
            targetReader.readLine();
        }

        String targetElements = targetReader.readLine().replaceAll("\\[", "").replaceAll("]", "");
        targetExtract = targetElements.split(",\\s");
        for (String words : targetExtract) {
            System.out.println(words);

        }
        targetReader.close();

        //reads line with source code
        BufferedReader sCodeReader = new BufferedReader(new FileReader(returnDatabase()));
        for (int i = 0; i < 2; i++) {
            sCodeReader.readLine();
        }
        sourceCode = sCodeReader.readLine();
        sCodeReader.close();

        BufferedReader tCodeReader = new BufferedReader(new FileReader(returnDatabase()));
        for (int i = 0; i < 3; i++) {
            tCodeReader.readLine();
        }
        targetCode = tCodeReader.readLine();
        tCodeReader.close();

        //make request object
        URL getUrl = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) getUrl.openConnection();


        //gets response. if connection (200 OK) made, data buffered
        BufferedReader art = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder jsonResponseData = new StringBuilder();
        String readLine = null;
        //appends data from response line by line
        while ((readLine = art.readLine()) != null) {
            jsonResponseData.append(readLine);
        }
        in.close();
        //convert response to string
        apiSentence = jsonResponseData.toString();
        System.out.println(apiSentence);
        return apiSentence;


    }



}