import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;


public class MakeXmlFile extends TxtFile{
    //static String db = "C:\\Users\\gnier\\Dropbox\\buildxml\\src\\main\\resources\\database.txt";
    private static String[] sourceList;
    private static String[] targetExtract;
    private static String sourceCode;
    private static String targetCode;
public static String[] getSourceList() {
        return sourceList;
    }

    public static String[] getTargetExtract() {
        return targetExtract;
    }

    public static void setSourceCode(String sourceCode) {
        MakeXmlFile.sourceCode = sourceCode;
    }

    public static void setTargetCode(String targetCode) {
        MakeXmlFile.targetCode = targetCode;
    }

    public static String getSourceCode() {
        return sourceCode;
    }

    public static String getTargetCode() {
        return targetCode;
    }

    public static void main(String[] args) throws IOException, ParserConfigurationException, TransformerException{
        createXml("C\\users\\gnier\\target.xml");
        System.out.println("---");
        System.out.println(Arrays.toString(targetExtract));
        System.out.println("---");
    }

    public static void createXml(String directory) throws IOException, TransformerException, ParserConfigurationException {
        BufferedReader sourceReader = new BufferedReader(new FileReader(returnDatabase()));
        //make source array
        String sourceElements = sourceReader.readLine().replaceAll("\\[", "").replaceAll("]", "");
        sourceList = sourceElements.split(",\\s");


        //result: Array of target sentences to be input into url
        BufferedReader targetReader = Files.newBufferedReader(Paths.get(returnDatabase()));
        for (int i = 0; i < 1; i++) {
            targetReader.readLine();
        }
        //String extractedLine = targetReader.readLine();
        String targetElements = targetReader.readLine().replaceAll("\\[", "").replaceAll("]", "");
        System.out.println("--old targets--");
        targetExtract = targetElements.split(",\\s");



        //reads line with source code
        BufferedReader sCodeReader = new BufferedReader(new FileReader(returnDatabase()));
        for (int i = 0; i < 2; i++) {
            sCodeReader.readLine();
        }
        sourceCode = sCodeReader.readLine();
       // System.out.println(sourceCode);


        BufferedReader tCodeReader = new BufferedReader(new FileReader(returnDatabase()));
        for (int i = 0; i < 3; i++) {
            tCodeReader.readLine();
        }
        targetCode = tCodeReader.readLine();

        System.out.println("t code: " + targetCode);
        System.out.println("s code: " + sourceCode);
        System.out.println("t extract: " + Arrays.toString(targetExtract));
        System.out.println("s list: " + Arrays.toString(sourceList));
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder;
        dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.newDocument();

        //add elements to Document
        Element rootElement = doc.createElementNS("", "emekaxml");
        doc.appendChild(rootElement);


            //node 1
            Element translate1 = doc.createElement("translatable");
            rootElement.appendChild(translate1);
            Attr blockId1 = doc.createAttribute("blockId");
            blockId1.setValue("0");
            translate1.setAttributeNode(blockId1);
            Attr wordCount1 = doc.createAttribute("wordcount");
            wordCount1.setValue("4");
            translate1.setAttributeNode(wordCount1);
            //translatable attributes

            //segment tag
            Element segment1 = doc.createElement("segment");
            translate1.appendChild(segment1);
            Attr segmentId1 = doc.createAttribute("segmentId");
            segmentId1.setValue("1");
            segment1.setAttributeNode(segmentId1);
            //source tag
            Element source1 = doc.createElement("source");
            source1.appendChild(doc.createTextNode(sourceList[0]));
            segment1.appendChild(source1);
            //target tag
            Element target1 = doc.createElement("target");
            target1.appendChild(doc.createTextNode(
                    CallApiTemplate.callApi("https://www.worldlingo.com/S11887.1/api?wl_password=7DC5xl94&wl_"+
                            "errorstyle=1&wl_srclang=" + sourceCode + "&wl_trglang=" + targetCode + "&wl_data="
                            + targetExtract[0]).replaceAll("[^\\x00-\\x7f]", "")));
            segment1.appendChild(target1);


            //node 2
            Element translate2 = doc.createElement("translatable");
            rootElement.appendChild(translate2);
            Attr blockId2 = doc.createAttribute("blockId");
            blockId2.setValue("0");
            translate2.setAttributeNode(blockId2);
            Attr wordCount2 = doc.createAttribute("wordcount");
            wordCount2.setValue("4");
            translate2.setAttributeNode(wordCount2);


            Element segment2 = doc.createElement("segment");
            translate2.appendChild(segment2);
            Attr segmentId2 = doc.createAttribute("segmentId");
            segmentId2.setValue("2");
            segment2.setAttributeNode(segmentId2);
            Element source2 = doc.createElement("source");
            source2.appendChild(doc.createTextNode(sourceList[1]));
            segment2.appendChild(source2);
            //target tag
            Element target2 = doc.createElement("target");
            target2.appendChild(doc.createTextNode(CallApiTemplate.callApi("https://www.worldlingo.com/S11887.1/api?wl_password=7DC5xl94&wl_errorstyle=1&wl_srclang=" + sourceCode + "&wl_trglang=" + targetCode + "&wl_data=" + targetExtract[1]).replaceAll("[^\\x00-\\x7f]", "")));
            segment2.appendChild(target2);
            Attr createdBy2 = doc.createAttribute("createdby");
            createdBy2.setValue("MT!");
            target2.setAttributeNode(createdBy2);

            //node 3
            Element translate3 = doc.createElement("translatable");
            rootElement.appendChild(translate3);
            Attr blockId3 = doc.createAttribute("blockId");
            blockId3.setValue("0");
            translate3.setAttributeNode(blockId3);
            Attr wordCount3 = doc.createAttribute("wordcount");
            wordCount3.setValue("4");
            translate3.setAttributeNode(wordCount3);


            Element segment3 = doc.createElement("segment");
            translate3.appendChild(segment3);
            Attr segmentId3 = doc.createAttribute("segmentId");
            segmentId3.setValue("3");
            segment3.setAttributeNode(segmentId3);

            Element source3 = doc.createElement("source");
            source3.appendChild(doc.createTextNode(sourceList[2]));
            segment3.appendChild(source3);
            //target tag
            Element target3 = doc.createElement("target");
            target3.appendChild(doc.createTextNode(CallApiTemplate.callApi("https://www.worldlingo.com/S11887.1/api?wl_password=7DC5xl94&wl_errorstyle=1&wl_srclang=" + sourceCode + "&wl_trglang=" + targetCode + "&wl_data=" + targetExtract[2]).replaceAll("[^\\x00-\\x7f]", "")));
            segment3.appendChild(target3);
            Attr createdBy3 = doc.createAttribute("createdby");
            createdBy3.setValue("MT!");
            target3.setAttributeNode(createdBy3);

            //node 4
            Element translate4 = doc.createElement("translatable");
            rootElement.appendChild(translate4);
            Attr blockId4 = doc.createAttribute("blockId");
            blockId4.setValue("0");
            translate4.setAttributeNode(blockId4);
            Attr wordCount4 = doc.createAttribute("wordcount");
            wordCount4.setValue("4");
            translate4.setAttributeNode(wordCount4);


            Element segment4 = doc.createElement("segment");
            translate4.appendChild(segment4);
            Attr segmentId4 = doc.createAttribute("segmentId");
            segmentId4.setValue("4");
            segment4.setAttributeNode(segmentId4);

            Element source4 = doc.createElement("source");
            source4.appendChild(doc.createTextNode(sourceList[3]));
            segment4.appendChild(source4);
            //target tag
            Element target4 = doc.createElement("target");
            target4.appendChild(doc.createTextNode(CallApiTemplate.callApi("https://www.worldlingo.com/S11887.1/api?wl_password=7DC5xl94&wl_errorstyle=1&wl_srclang=" + sourceCode + "&wl_trglang=" + targetCode + "&wl_data=" + targetExtract[3]).replaceAll("[^\\x00-\\x7f]", "")));
            segment4.appendChild(target4);
            Attr createdBy4 = doc.createAttribute("createdby");
            createdBy4.setValue("MT!");
            target4.setAttributeNode(createdBy4);

            //node 5
            Element translate5 = doc.createElement("translatable");
            rootElement.appendChild(translate5);
            Attr blockId5 = doc.createAttribute("blockId");
            blockId5.setValue("0");
            translate5.setAttributeNode(blockId5);
            Attr wordCount5 = doc.createAttribute("wordcount");
            wordCount5.setValue("4");
            translate5.setAttributeNode(wordCount5);


            Element segment5 = doc.createElement("segment");
            translate5.appendChild(segment5);
            Attr segmentId5 = doc.createAttribute("segmentId");
            segmentId5.setValue("5");
            segment5.setAttributeNode(segmentId5);

            Element source5 = doc.createElement("source");
            source5.appendChild(doc.createTextNode(sourceList[4]));
            segment5.appendChild(source5);
            //target tag
            Element target5 = doc.createElement("target");
            target5.appendChild(doc.createTextNode(CallApiTemplate.callApi("https://www.worldlingo.com/S11887.1/api?wl_password=7DC5xl94&wl_errorstyle=1&wl_srclang=" + sourceCode + "&wl_trglang=" + targetCode + "&wl_data=" + targetExtract[4]).replaceAll("[^\\x00-\\x7f]", "")));
            segment5.appendChild(target5);
            Attr createdBy5 = doc.createAttribute("createdby");
            createdBy5.setValue("MT!");
            target5.setAttributeNode(createdBy5);


        DOMSource source = new DOMSource(doc);
        //write to console or file
        StreamResult console = new StreamResult(System.out);
        StreamResult file = new StreamResult(new File(directory));
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        //for pretty print
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        //write data
        transformer.transform(source, console);
        transformer.transform(source, file);
        sourceReader.close();

    }
}

