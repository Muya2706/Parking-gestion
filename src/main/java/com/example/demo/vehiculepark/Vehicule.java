package com.example.demo.vehiculepark;

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
}
