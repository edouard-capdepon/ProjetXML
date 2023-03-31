package org.example.Restaurant;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;

public class Menu {
    public ArrayList<Entree> entrees = new ArrayList<>();
    public ArrayList<Plat> plats = new ArrayList<>();
    public ArrayList<Fromage> fromages = new ArrayList<>();
    public ArrayList<Dessert> desserts = new ArrayList<>();
    public ArrayList<Boisson> boissons = new ArrayList<>();
    String boisson;

    public Menu() {

    }

    public Menu(ArrayList<Entree> entrees, ArrayList<Plat> plats, ArrayList<Fromage> fromages, ArrayList<Dessert> desserts, String boisson, String prix) {
        this.entrees = entrees;
        this.plats = plats;
        this.fromages = fromages;
        this.desserts = desserts;
        this.boissons = boissons;
        this.boisson = boisson;
        this.prix = prix;
    }

    public String getBoisson() {
        return boisson;
    }

    public void setBoisson(String boisson) {
        this.boisson = boisson;
    }

    public String getPrix() {
        return prix;
    }

    public void setPrix(String prix) {
        this.prix = prix;
    }

    public String prix;

    public static Menu generateMenuFromNode(Node node){
        Menu menu = new Menu();
        Node eMenu = node;
        //On lui définit son prix
        menu.setPrix(((Element)(eMenu)).getAttribute("prix"));
        //On défini si la boisson est incluse ou non
        menu.setBoisson(((Element)(eMenu)).getAttribute("boisson"));
        //On récupère les entrées
        NodeList entrees = ((Element)(eMenu)).getElementsByTagName("entree");
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
        NodeList plats = ((Element)(eMenu)).getElementsByTagName("plat");
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
        NodeList fromages = ((Element)(eMenu)).getElementsByTagName("fromage");
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
        NodeList desserts = ((Element)(eMenu)).getElementsByTagName("dessert");
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
    return menu;
    }

}
