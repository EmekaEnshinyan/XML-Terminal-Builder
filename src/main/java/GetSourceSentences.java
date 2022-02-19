import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.util.ArrayList;

import static java.lang.System.out;


public class GetSourceSentences extends TxtFile{
    static ArrayList<String> sourceArray = new ArrayList<>();
    static ArrayList<String> sourceArray2 = new ArrayList<>();
    static String sourceList;

    public static void sourceSent(String xmlFileLocation) throws ParserConfigurationException, SAXException, IOException, InterruptedException {
        out.println("---------------");
        out.println("Getting Text From Source File: \n");
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();

        builderFactory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
        //parse xml file
        DocumentBuilder builder = builderFactory.newDocumentBuilder();
        org.w3c.dom.Document document = builder.parse(new File(xmlFileLocation));
        //
        document.getDocumentElement().normalize();
        //specify tag in xml file and iterate
        NodeList nodeList = document.getElementsByTagName("segment");

        for (int i = 0; i < nodeList.getLength(); i++) {
            //this is tag index of where line of el are
            Node node = nodeList.item(i);

            //check if actually a node
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                //create a node object that will retrieve the element in xml file
                org.w3c.dom.Element element = (org.w3c.dom.Element) node;
                //get the element from the specified node in nodeList
                sourceList = element.getElementsByTagName("source").item(0).getTextContent();
                //add sentences to array list
                sourceArray.add(sourceList);
            }
        }
        //create array list of sentences from sourceArray to be inputted into uri
        for (String s : sourceArray) {
            sourceArray2.add(s.replaceAll("\\s+", "\\%20"));
        }

        //set filepath for .txt file
        FileWriter fw = new FileWriter(returnDatabase(), true); // Step 2
        PrintWriter out = new PrintWriter(fw);
        out.println(sourceArray);
        out.print(sourceArray2);
        out.close();
    }

    }
