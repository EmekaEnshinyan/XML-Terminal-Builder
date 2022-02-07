import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;


//TODO:
    //TODO: Improvements:
    // 1. create a wordcount algorithm that counts the words in source sentence and puts it in translatable wordcount value
    // 2. loop over xml writer and automate segmentId value incrementally
    // 3. create an algorithm that will allow further writing of more than 5 source-target sentences pairs
    // 4. created a counter loop for wordCount in xml
    // 5. make an errorstyle check
    // 6. design -e
    // 7. make .txt write global

    @Command(name = "fileCli", description = "Performs file manipulation operations", mixinStandardHelpOptions = true, version = "File Client 1.0")
    public class CommandSetup implements Callable<String> {

        @Option(names = "-f", description = " access f path for source xml and extract sentences")
        private String file;

        @Option(names = "-s", description = "source language")
        private String source;

        @Option(names = "-t", description = "target language")
        private String target;

        @Option(names = "-o", description = " build xml and save as file in location o")
        private String output;




        public static void main(String... args) throws Exception {
            int exitCode = new picocli.CommandLine(new CommandSetup()).execute(args);
            System.exit(exitCode);
        }

        public String call() throws Exception {
            FileWriter fileWriter = new FileWriter("C:\\Users\\gnier\\Dropbox\\buildxml\\src\\main\\resources\\database.txt");
            fileWriter.close();

            if (file != null) {
                if (file.contains("\\") & file.contains(".txml")) {
                    GetSourceSentences.sourceSent(file);
                    System.out.println("------");
                    System.out.println("source.txml data retrieved\n");
                } else {
                    System.out.println("File \"source.txml\" not found. Check FileName and Directory.");
                    System.exit(2);
                }
            }
            if (source != null){
                String[] codeList = {"en", "ar", "de", "el", "es", "fr", "it", "ja", "ko", "nl", "pt",
                        "ru", "sv", "zh_cn", "zh_tw"};
                List<String> sArray = new ArrayList<>(List.of(codeList));
                if (!sArray.contains(source)) {
                    System.out.println("Error: " + source + " is an unapproved language code");
                    System.exit(3);
                }
                System.out.println("inputted source language code approved: " + source + "\n");
                AddCodes.addSource(source);
            }
            if (target != null){
                String[] codeList = {"en", "ar", "de", "el", "es", "fr", "it", "ja", "ko", "nl", "pt",
                        "ru", "sv", "zh_cn", "zh_tw"};
                List<String> sArray = new ArrayList<>(List.of(codeList));
                if (!sArray.contains(source)) {
                    System.out.println("Error: " + source + " is an unapproved language code");
                    System.exit(3);
                }
                System.out.println("inputted source language code approved: " + source + "\n");
                AddCodes.addTarget(target);
            }
            if (output != null) {
                if (output.contains("\\") & output.contains(".txml")){
                    MakeXmlFile.createXml(output);
                }else{
                    System.out.println("directory output of .txml file is invalid");
                }

            }

            return "success";
        }
    }
