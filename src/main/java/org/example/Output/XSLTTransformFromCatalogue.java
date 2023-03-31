package org.example.Output;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.*;

public class XSLTTransformFromCatalogue {

    public static void transform(String xmlpath, String xsltpath, String htmlOutput){

        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new File(xmlpath));
            DOMSource dom = new DOMSource(document);
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer(new StreamSource(new File(xsltpath)));
            File out = new File(htmlOutput);
            transformer.transform(dom, new StreamResult(out));

        } catch (TransformerException | ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();

        }

    }

}
