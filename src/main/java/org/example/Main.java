package org.example;

import org.example.Restaurant.*;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        Catalogue catalogue = Catalogue.createCatalogueFromFile("src/xml/Menu.xml");
        System.out.println(catalogue.toString());
    }


}