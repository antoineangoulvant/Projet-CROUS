package com.antoine.gestioncrous.model;

import java.util.HashSet;
import java.util.Set;

public class Bien {
    private long id;
    private String adresse;
    private Nature nature;
    private Personne proprietaire;
    private Set bails = new HashSet<Bail>();

    public Bien(){}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public Set getBails() {
        return bails;
    }

    public void setBails(Set bails) {
        this.bails = bails;
    }

    public Nature getNature() {
        return nature;
    }

    public void setNature(Nature nature) {
        this.nature = nature;
    }

    public Personne getProprietaire() {
        return proprietaire;
    }

    public void setProprietaire(Personne proprietaire) {
        this.proprietaire = proprietaire;
    }
}
