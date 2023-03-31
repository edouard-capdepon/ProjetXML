package org.example.Restaurant;

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

    public Menu(ArrayList<Entree> entrees, ArrayList<Plat> plats, ArrayList<Fromage> fromages, ArrayList<Dessert> desserts, ArrayList<Boisson> boissons, String boisson, String prix) {
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

}
