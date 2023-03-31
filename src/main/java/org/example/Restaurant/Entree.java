package org.example.Restaurant;

public class Entree extends Item {

    String type;

    public Entree(){

    }

    public Entree(String nom, String description, String type) {
        super(nom, description);
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


}
