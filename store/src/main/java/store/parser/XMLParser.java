package store.parser;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

public class XMLParser {
    public static Map<String, String>  getSortTypes(String xmlPath) {
        LinkedHashMap<String, String> sortTypesMap = new LinkedHashMap<>();
        File configFile = new File(xmlPath);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(configFile);
            NodeList sortNode = document.getElementsByTagName("sort");
            NodeList sortTypesNodesList = sortNode.item(0).getChildNodes();
            for (int i = 0; i < sortTypesNodesList.getLength(); i++) {
                Node sortTypeNode = sortTypesNodesList.item(i);
                if (sortTypeNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element sortTypeElement = (Element) sortTypeNode;
                    sortTypesMap.put(sortTypeElement.getTagName().toLowerCase(Locale.ROOT), sortTypeElement.getTextContent().toUpperCase());
                }
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }

        return sortTypesMap;
    }
}
