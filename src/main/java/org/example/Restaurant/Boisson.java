package org.example.Restaurant;

public class Boisson {
    String nom;
    String prix;

    public String getPrix() {
        return prix;
    }

    public void setPrix(String prix) {
        this.prix = prix;
    }

    public Boisson(){

    }
    public Boisson(String nom){
        this.nom = nom;
    }

    public Boisson(String nom, String prix){
        this.nom = nom;
        this.prix = prix;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
