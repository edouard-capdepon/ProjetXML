package org.example.Restaurant;


import org.example.Util;
import org.w3c.dom.*;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.events.Attribute;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Objects;

public class Catalogue {
    public ArrayList<Menu> menus = new ArrayList<>();
    public ArrayList<Boisson> boissons = new ArrayList<>();
    ArrayList<String> moyensPaiement = new ArrayList<>();


    public static Catalogue createCatalogueFromFile(String xmlPath) {
        Catalogue catalogue = new Catalogue();
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new File(xmlPath));
            Element root = document.getDocumentElement();
            //On récupère les moyens de paiement acceptés par le restaurant
            NodeList moyensPaiement = document.getElementsByTagName("moyenPaiement");
            for (int i = 0; i < moyensPaiement.getLength(); i++) {
                catalogue.moyensPaiement.add(moyensPaiement.item(i).getTextContent());
            }

            //On récupère les menus
            NodeList menus = document.getElementsByTagName("menu");
            for (int i = 0; i < menus.getLength(); i++) {
                //On créer un objet menu
                Menu menu = new Menu();
                Element eMenu = (Element) menus.item(i);
                //On lui définit son prix
                menu.setPrix(eMenu.getAttribute("prix"));
                //On défini si la boisson est incluse ou non
                menu.setBoisson(eMenu.getAttribute("boisson"));
                //On récupère les entrées
                NodeList entrees = eMenu.getElementsByTagName("entree");
                for (int i2 = 0; i2 < entrees.getLength(); i2++) {
                    //On créer un objet entree
                    Entree entree = new Entree();
                    Element eEntree = (Element) entrees.item(i2);
                    entree.setType(eEntree.getAttribute("type"));
                    //On récupère le nom de l'entrée
                    entree.setNom(eEntree.getElementsByTagName("nom").item(0).getTextContent());
                    //On récupère la description
                    entree.setDescription(eEntree.getElementsByTagName("description").item(0).getTextContent());
                    //Enfin on ajoute l'entrée au menu
                    menu.entrees.add(entree);

                }
                //On récupère les plats
                NodeList plats = eMenu.getElementsByTagName("plat");
                for (int i3 = 0; i3 < plats.getLength(); i3++) {
                    //On créer un objet plat
                    Plat plat = new Plat();
                    Element ePlat = (Element) plats.item(i3);

                    //On récupère le nom du plat
                    plat.setNom(ePlat.getElementsByTagName("nom").item(0).getTextContent());
                    //On récupère la description
                    plat.setDescription(ePlat.getElementsByTagName("description").item(0).getTextContent());
                    //Enfin on ajoute le plat au menu
                    menu.plats.add(plat);

                }

                //On récupère les fromages
                NodeList fromages = eMenu.getElementsByTagName("fromage");
                if (fromages.getLength() != 0) {
                    for (int i4 = 0; i4 < entrees.getLength(); i4++) {
                        //On créer un objet fromage
                        Fromage fromage = new Fromage();
                        Element eFromage = (Element) fromages.item(i4);

                        //On récupère le nom du fromage
                        fromage.setNom(eFromage.getElementsByTagName("nom").item(0).getTextContent());
                        //On récupère la description
                        fromage.setDescription(eFromage.getElementsByTagName("description").item(0).getTextContent());
                        //Enfin on ajoute le fromage au menu
                        menu.fromages.add(fromage);

                    }
                }

                //On récupère les desserts
                NodeList desserts = eMenu.getElementsByTagName("dessert");
                for (int i5 = 0; i5 < entrees.getLength(); i5++) {
                    //On créer un objet fromage
                    Dessert dessert = new Dessert();
                    Element eDessert = (Element) desserts.item(i5);

                    //On récupère le nom du fromage
                    dessert.setNom(eDessert.getElementsByTagName("nom").item(0).getTextContent());
                    //On récupère la description
                    dessert.setDescription(eDessert.getElementsByTagName("description").item(0).getTextContent());
                    //Enfin on ajoute le fromage au menu
                    menu.desserts.add(dessert);

                }

                //On ajoute le menu au catalogue
                catalogue.menus.add(menu);

            }
            //On récupère les menus
            NodeList boissons = document.getElementsByTagName("boisson");
            for (int i = 0; i < boissons.getLength(); i++) {
                //On créer un objet Boisson
                Boisson boisson = new Boisson();
                Element eBoisson = (Element) boissons.item(i);

                //On récupère le nom de la boisson
                boisson.setNom(eBoisson.getElementsByTagName("nom").item(0).getTextContent());
                //On récupère le prix de la boisson
                boisson.setPrix(eBoisson.getAttribute("prix"));
                //Enfin on ajoute la boisson dans le catalogue
                catalogue.boissons.add(boisson);
            }


        } catch (ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();

        }

        return catalogue;
    }

    //Affichage
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Catalogue du Restaurant : \n");
        stringBuilder.append("--------------------------------------------------\n");
        stringBuilder.append("\tMenu(s) :\n");
        //On itère sur chaque menu
        for (Menu menu : menus
        ) {
            //On affiche Menu et son prix
            stringBuilder.append("\t\tMenu à " + menu.getPrix() + "€ : Boisson " + menu.boisson + "\n");
            //On vérifie s'il y a des entrées
            //Si jamais demain on décide que des menus peuvent exister sans, cela n'aura pas d'impact.
            if (!menu.entrees.isEmpty()) {
                //Si oui
                stringBuilder.append("\t\t\tEntree(s) :\n");
                //On itère sur toutes les entrées
                for (Entree entree : menu.entrees
                ) {
                    stringBuilder.append("\t\t\t\tEntree " + entree.getType() + " : " + entree.getNom() + "\n");
                    stringBuilder.append("\t\t\t\t" + entree.getDescription() + "\n");
                }
            }
            //On part du principe qu'il y aura toujours au moin un plat pour un menu
            stringBuilder.append("\t\t\tPlat(s) :\n");
            //On itère sur tous les plats
            for (Plat plat : menu.plats
            ) {
                stringBuilder.append("\t\t\t\tPlat : " + plat.getNom() + "\n");
                stringBuilder.append("\t\t\t\t" + plat.getDescription() + "\n");
            }
            //On vérifie s'il y a des fromages
            //Si jamais demain on décide que des menus peuvent exister sans, cela n'aura pas d'impact.
            if (!menu.fromages.isEmpty()) {
                //Si oui
                stringBuilder.append("\t\t\tFromage(s) :\n");
                //On itère sur tous les fromages
                for (Fromage fromage : menu.fromages
                ) {
                    stringBuilder.append("\t\t\t\tFromage : " + fromage.getNom() + "\n");
                    stringBuilder.append("\t\t\t\t" + fromage.getDescription() + "\n");
                }
            }
            //On vérifie s'il y a des desserts
            //Si jamais demain on décide que des menus peuvent exister sans, cela n'aura pas d'impact.
            if (!menu.desserts.isEmpty()) {
                //Si oui
                stringBuilder.append("\t\t\tDessert(s) :\n");
                //On itère sur les desserts
                for (Dessert dessert : menu.desserts
                ) {
                    stringBuilder.append("\t\t\t\tDessert : " + dessert.getNom() + "\n");
                    stringBuilder.append("\t\t\t\t" + dessert.getDescription() + "\n");
                }
            }
            //On vérifie si la boisson est incluse dans le menu
            stringBuilder.append("----------------------------------------------\n");
        }

        //On vérifie s'il y a des boissons
        //Si jamais demain on décide que des menus peuvent exister sans, cela n'aura pas d'impact.
        if (!boissons.isEmpty()) {
            //Si oui
            stringBuilder.append("\tBoisson(s) :\n");
            //On itère sur les boissons
            for (Boisson boisson : boissons
            ) {
                stringBuilder.append("\t\t\t\t" + boisson.getNom() + " " + boisson.getPrix() + "€" + "\n");
            }
        }

        stringBuilder.append("----------------------------------------------\n");

        stringBuilder.append("Moyens de Paiement : ");
        for (int i = 0; i < moyensPaiement.size(); i++) {
            stringBuilder.append(moyensPaiement.get(i));
            if (i < moyensPaiement.size() - 1) {
                stringBuilder.append(", ");
            }
        }

        stringBuilder.append("\n----------------------------------------------\n");


        return stringBuilder.toString();
    }

    public static void addBoissonToCatalogue(Boisson boisson, String xmlPath) {

        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            org.w3c.dom.Document document = builder.parse(new File(xmlPath));
            Node boissons = document.getElementsByTagName("boissons").item(0);
            Element eBoisson = document.createElement("boisson");
            Element nom = document.createElement("nom");
            nom.appendChild(document.createTextNode(boisson.getNom()));
            eBoisson.appendChild(nom);
            System.out.println("lqzkbflkflkn : " + boisson.getPrix());
            eBoisson.setAttribute("prix", (boisson.getPrix()));
            boissons.appendChild(eBoisson);
            DOMSource source = new DOMSource(document);
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            StreamResult result = new StreamResult(xmlPath);
            transformer.transform(source, result);


        } catch (ParserConfigurationException | IOException | SAXException | TransformerException e) {
            e.printStackTrace();
        }

    }

    public static void addMoyenPaiementToCatalogue(MoyenPaiement paiement, String xmlPath) {

        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            org.w3c.dom.Document document = builder.parse(new File(xmlPath));
            Node catalogue = document.getElementsByTagName("catalogue").item(0);
            Element eMoyen = document.createElement("moyenPaiement");
            eMoyen.appendChild(document.createTextNode(paiement.getMoyen()));
            catalogue.appendChild(eMoyen);
            DOMSource source = new DOMSource(document);
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            StreamResult result = new StreamResult(xmlPath);
            transformer.transform(source, result);


        } catch (ParserConfigurationException | IOException | SAXException | TransformerException e) {
            e.printStackTrace();
        }

    }

    public static void addMenuToCatalogue(Menu menu, String xmlPath){
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            org.w3c.dom.Document document = builder.parse(new File(xmlPath));
            Node catalogue = document.getElementsByTagName("catalogue").item(0);
            Node nMenu = document.createElement("menu");
            for (Entree e: menu.entrees
                 ) {
                Node nEntree = document.createElement("entree");
                Node nom = document.createElement("nom");
                nom.appendChild(document.createTextNode(e.getNom()));
                Node description = document.createElement("description");
                description.appendChild(document.createTextNode(e.getDescription()));
                nMenu.appendChild(nEntree);

            }

            for (Plat p: menu.plats
            ) {
                Node nPlat = document.createElement("plat");
                Node nom = document.createElement("nom");
                nom.appendChild(document.createTextNode(p.getNom()));
                Node description = document.createElement("description");
                description.appendChild(document.createTextNode(p.getDescription()));
                nMenu.appendChild(nPlat);

            }

            for (Fromage f: menu.fromages
            ) {
                Node nFromage = document.createElement("fromage");
                Node nom = document.createElement("nom");
                nom.appendChild(document.createTextNode(f.getNom()));
                Node description = document.createElement("description");
                description.appendChild(document.createTextNode(f.getDescription()));
                nMenu.appendChild(nFromage);

            }

            for (Dessert d: menu.desserts
            ) {
                Node nDessert = document.createElement("dessert");
                Node nom = document.createElement("nom");
                nom.appendChild(document.createTextNode(d.getNom()));
                Node description = document.createElement("description");
                description.appendChild(document.createTextNode(d.getDescription()));
                nMenu.appendChild(nDessert);

            }

            DOMSource source = new DOMSource(document);
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            StreamResult result = new StreamResult(xmlPath);
            transformer.transform(source, result);


        } catch (ParserConfigurationException | IOException | SAXException | TransformerException e) {
            e.printStackTrace();
        }
    }

    public static void deleteMoyenPaiementFromCatalogue(MoyenPaiement paiement, String xmlPath) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            org.w3c.dom.Document document = builder.parse(new File(xmlPath));
            Node catalogue = document.getElementsByTagName("catalogue").item(0);
            System.out.println("catalogue : " + catalogue.getNodeName());
            NodeList moyenPaiements = document.getElementsByTagName("moyenPaiement");
            for (int i = 0; i < moyenPaiements.getLength(); i++) {
                Node moyen = moyenPaiements.item(i);
                if(moyen.getTextContent().equals(paiement.getMoyen())){
                    catalogue.removeChild(moyen);
                }

            }
            DOMSource source = new DOMSource(document);
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            StreamResult result = new StreamResult(xmlPath);
            transformer.transform(source, result);


        } catch (ParserConfigurationException | IOException | SAXException | TransformerException e) {
            e.printStackTrace();
        }
    }

    public static void deleteBoissonFromCatalogue(Boisson boisson, String xmlPath) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            org.w3c.dom.Document document = builder.parse(new File(xmlPath));
            Node catalogue = document.getElementsByTagName("catalogue").item(0);
            System.out.println("catalogue : " + catalogue.getNodeName());
            Node boissonS = document.getElementsByTagName("boissons").item(0);
            NodeList boissons = document.getElementsByTagName("boisson");
            for (int i = 0; i < boissons.getLength(); i++) {
                Node bs = boissons.item(i);
                NodeList childs = bs.getChildNodes();
                for (int j = 0; j < childs.getLength(); j++) {
                    Node item = childs.item(j);
                    if (item.getNodeName().equals("nom")) {
                        if (item.getTextContent().equals(boisson.getNom())) {
                            boissonS.removeChild(bs);
                        }
                    }

                }

            }
            DOMSource source = new DOMSource(document);
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            StreamResult result = new StreamResult(xmlPath);
            transformer.transform(source, result);


        } catch (ParserConfigurationException | IOException | SAXException | TransformerException e) {
            e.printStackTrace();
        }
    }


}
