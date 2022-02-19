import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import javax.xml.transform.TransformerException;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
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
    public class CommandSetup extends TxtFile implements Callable<String> {
        private final static String[] codeList = {"en", "ar", "de", "el", "es", "fr", "it", "ja", "ko", "nl", "pt",
                "ru", "sv", "zh_cn", "zh_tw"};
        String approved = "en, ar, de, el, es, fr, it, ja, ko, nl, pt, ru, sv, zh_cn, zh_tw";

        @Option(names = "-f", description = " access the  filepath for source xml and extract sentences")
        private String file;

        @Option(names = "-s", description = "source language code\n list of approved codes: en, ar, de, el, es, fr, it, ja,"+
                "ko, nl, pt, ru, sv, zh_cn, zh_tw")
        private String source;

        @Option(names = "-t", description = "target language code\n list of approved codes: en, ar, de, el, es, fr, it, ja,"+
                "ko, nl, pt, ru, sv, zh_cn, zh_tw")
        private String target;

        @Option(names = "-o", description = "build .xml file and save in inputted location")
        private String output;

        public static void main(String ... args) throws Exception {
            int exitCode = new picocli.CommandLine(new CommandSetup()).execute(args);
            System.exit(exitCode);
        }

        public String call() throws Exception {
            FileWriter fileWriter = new FileWriter(returnDatabase());
            fileWriter.write("");
            fileWriter.close();

            if (file != null) {
                if (file.contains("\\") & file.contains(".xml")) {
                    GetSourceSentences.sourceSent(file);
                    System.out.println("------");
                    System.out.println("source.xml data retrieved\n");
                } else {
                    System.out.println("File \"source.xml\" not found. Check FileName and Directory.");
                    System.exit(2);
                }
            }
            if (source != null){
                List<String> sArray = new ArrayList<>(List.of(codeList));
                if (!sArray.contains(source)) {
                    System.out.println("Error: " + source + " is an unapproved language code");
                    System.exit(3);
                }
                System.out.println("inputted source language code approved: " + source + "\n");
                AddCodes.addSource(source);
            }
            if (target != null){
                List<String> sArray = new ArrayList<>(List.of(codeList));
                if (!sArray.contains(target)) {
                    System.out.println("Error: " + target + " is an unapproved language code");
                    System.exit(3);
                }
                System.out.println("inputted source language code approved: " + target);
                AddCodes.addTarget(target);
            }
            if (output != null) {
                    try{
                        MakeXmlFile.createXml(output);
                    }catch (TransformerException e) {
                        System.out.println("Transformation error".toUpperCase(Locale.ROOT));
                        System.out.println("Application will now exit");
                        System.exit(4);
                    }catch (FileNotFoundException e){
                        System.out.println("File Not Found".toUpperCase(Locale.ROOT));
                        System.out.println("Application will now exit");
                        System.exit(5);
                    }
                }

            return "success";
        }
    }
