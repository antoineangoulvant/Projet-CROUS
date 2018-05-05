package com.antoine.gestioncrous.model;

import java.util.HashSet;
import java.util.Set;

public class Personne {
    private long id;
    private String nom;
    private String prenom;
    private String adresse;
    private Set biens = new HashSet<Bien>();
    private Set bails = new HashSet<Bail>();


    public Personne(){}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public Set getBiens() {
        return biens;
    }

    public void setBiens(Set biens) {
        this.biens = biens;
    }

    public Set getBails() {
        return bails;
    }

    public void setBails(Set bails) {
        this.bails = bails;
    }
}
