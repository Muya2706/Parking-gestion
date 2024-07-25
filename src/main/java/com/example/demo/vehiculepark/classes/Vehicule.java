package com.example.demo.vehiculepark.classes;
public class Vehicule {
    private String id;
    private String nom;
    private String type;
    private String attributs;

    public Vehicule(String id, String nom, String type, String attributs) {
        this.id = id;
        this.nom = nom;
        this.type = type;
        this.attributs = attributs;
    }

    // Getters et setters

    @Override
    public String toString() {
        return "ID: " + id + ", Nom: " + nom + ", Type: " + type + ", Attributs: " + attributs;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNom() {
        return this.nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAttributs() {
        return this.attributs;
    }

    public void setAttributs(String attributs) {
        this.attributs = attributs;
    }

    
}
