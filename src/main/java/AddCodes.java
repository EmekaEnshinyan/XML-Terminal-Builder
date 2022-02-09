import java.io.*;

public class AddCodes extends TxtFile {

    public static void main(String[] args) throws IOException {
        addSource("en");
        addTarget("fr");

    }

    public static void addSource(String sourceCode) throws IOException {
        FileWriter fw = new FileWriter(returnDatabase(), true);
        fw.write("\n" + sourceCode);
        System.out.println(sourceCode);
        fw.close();

            }


    public static void addTarget(String targetCode) throws IOException {
        FileWriter fw = new FileWriter(returnDatabase(), true);
        fw.write("\n" + targetCode);
        System.out.println(targetCode);
        fw.close();
        }
    }

