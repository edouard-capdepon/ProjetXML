package org.example;

import org.example.Output.XSLTTransformFromCatalogue;
import org.example.Recherche.CatalogueSearcherXPATH;
import org.example.Restaurant.*;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import java.io.IOException;
import java.lang.reflect.Array;
import java.rmi.server.UID;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        try {


            //Affichage d'un xml dans la console JAVA
            Catalogue catalogue = Catalogue.createCatalogueFromFile("src/xml/Menu.xml");
            System.out.println("test : " + catalogue);


            //Recherche via XPath
            //Node res = CatalogueSearcherXPATH.evaluate("src/xml/Menu.xml", "//catalogue");
            //System.out.println(Util.displayContentOfNode(res));


            //Transforme un fichier xml en html
            //XSLTTransformFromCatalogue.transform("src/xml/Menu.xml", "src/xml/Menu.xsl", "src/xml/output.html");


            //Ajoute une nouvelle boisson
            Catalogue.addBoissonToCatalogue(new Boisson("Café", "1"), "src/xml/Menu.xml");

            //Ajoute un nouveau moyen de paiement
            Catalogue.addMoyenPaiementToCatalogue(new MoyenPaiement("Espece"), "src/xml/Menu.xml");

            //Ajoute un nouveau Menu
            ArrayList<Entree> entrees = new ArrayList<>();
            entrees.add(new Entree("TESTNOM", "TESTDESCRIPTION", "chaude"));
            ArrayList<Plat> plats = new ArrayList<>();
            plats.add(new Plat("TESTNOM", "TESTDESCRIPTION"));
            ArrayList<Fromage> fromages = new ArrayList<>();
            ArrayList<Dessert> desserts = new ArrayList<>();
            Catalogue.addMenuToCatalogue(new Menu(), "src/xml/Menu.xml");

            //Supprime une boisson
            //Catalogue.deleteBoissonFromCatalogue(new Boisson("Vin"),  "src/xml/Menu.xml");

            //Supprime un moyen de paiement
            //Catalogue.deleteMoyenPaiementFromCatalogue(new MoyenPaiement("Espece"),  "src/xml/Menu.xml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}

