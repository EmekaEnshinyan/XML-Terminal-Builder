import java.io.*;

public class AddCodes extends GetSourceSentences {
    static String dataBase = "C:\\Users\\gnier\\Dropbox\\buildxml\\src\\main\\resources\\database.txt";

    public static void main(String[] args) throws IOException {
        addSource("en");
        addTarget("fr");

    }

    public static void addSource(String sourceCode) throws IOException {
        FileWriter fw = new FileWriter(dataBase, true);
        fw.write("\n" + sourceCode);
        System.out.println(sourceCode);
        fw.close();

            }


    public static void addTarget(String targetCode) throws IOException {
        FileWriter fw = new FileWriter(dataBase, true);
        fw.write("\n" + targetCode);
        System.out.println(targetCode);
        }
    }

