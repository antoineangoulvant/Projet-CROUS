package com.antoine.gestioncrous.model;

import java.util.HashSet;
import java.util.Set;

/**
 * Classe représentant un Bien et ses attributs
 */
public class Bien {
    private long id;
    private String adresse;
    private Nature nature;
    private Personne proprietaire;
    private Set bails = new HashSet<Bail>();

    /**
     * Constructeur de la classe Bien
     */
    public Bien(){}

    /**
     * Méthode permettant de récupérer l'ID d'un bien
     * @return id du bien
     */
    public long getId() {
        return id;
    }

    /**
     * Méthode permettant de modifier l'ID d'un bien
     * @param id id du bien
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Méthode permettant de récupérer l'adresse du bien
     * @return adresse du bien
     */
    public String getAdresse() {
        return adresse;
    }

    /**
     * Méthode permettant de modifier l'adresse d'un bien
     * @param adresse adresse du bien
     */
    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    /**
     * Méthode permettant de récupérer les bails d'un bien
     * @return liste des bails concernant un bien
     */
    public Set getBails() {
        return bails;
    }

    /**
     * Méthode permettant de modifier la liste des bails d'un bien
     * @param bails nouvelle liste des bails
     */
    public void setBails(Set bails) {
        this.bails = bails;
    }

    /**
     * Méthode permettant de récupérer la nature d'un bien
     * @return la nature d'un bien
     */
    public Nature getNature() {
        return nature;
    }

    /**
     * Méthode permettant de modifier la nature d'un bien
     * @param nature nature d'un bien
     */
    public void setNature(Nature nature) {
        this.nature = nature;
    }

    /**
     * Méthode permettant de récupérer le propriétaire d'un bien
     * @return propriétaire du bien
     */
    public Personne getProprietaire() {
        return proprietaire;
    }

    /**
     * Méthode permettant de modifier le propriétaire d'un bien
     * @param proprietaire propriétaire d'un bien
     */
    public void setProprietaire(Personne proprietaire) {
        this.proprietaire = proprietaire;
    }
}
