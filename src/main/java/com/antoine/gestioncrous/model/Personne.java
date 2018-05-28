package com.antoine.gestioncrous.model;

import java.util.HashSet;
import java.util.Set;

/**
 * Classe représentant une Personne et ses attributs
 */
public class Personne {
    private long id;
    private String nom;
    private String prenom;
    private String adresse;
    private Set biens = new HashSet<Bien>();
    private Set bails = new HashSet<Bail>();

    /**
     * Constructeur de la classe Personne
     */
    public Personne(){}

    /**
     * Méthode permettant de récupérer l'ID d'une personne
     * @return id de la personne
     */
    public long getId() {
        return id;
    }

    /**
     * Méthode permettant de modifier l'ID d'une personne
     * @param id id de la personne
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Méthode permettant de récupérer le nom d'une personne
     * @return nom de la personne
     */
    public String getNom() {
        return nom;
    }

    /**
     * Méthode permettant de modifier le nom d'une personne
     * @param nom nom de la personne
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Méthode permettant de récupérer le prénom de la personne
     * @return prénom de la personne
     */
    public String getPrenom() {
        return prenom;
    }

    /**
     * Méthode permettant de modifier le prénom de la personne
     * @param prenom prénom de la personne
     */
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    /**
     * Méthode permettant de récupérer l'adresse d'une personne
     * @return adresse de la personne
     */
    public String getAdresse() {
        return adresse;
    }

    /**
     * Méthode permettant de modifier l'adresse de la personne
     * @param adresse adresse de la personne
     */
    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    /**
     * Méthode permettant de récupérer les biens que la personne possède
     * @return la liste des biens
     */
    public Set getBiens() {
        return biens;
    }

    /**
     * Méthode permettant de modifier les biens que la personne possède
     * @param biens liste des biens modifiés
     */
    public void setBiens(Set biens) {
        this.biens = biens;
    }

    /**
     * Méthode permettant de récupérer la liste des bails dont la personne est concerné
     * @return la liste des bails
     */
    public Set getBails() {
        return bails;
    }

    /**
     * Méthode permettant de modifier la liste des bails dont la personne est concerné
     * @param bails la liste des bails modifiés
     */
    public void setBails(Set bails) {
        this.bails = bails;
    }

    /**
     * Affichage du nom et prénom de la personne
     * @return prénom et nom de la personne en chaine de caractères
     */
    public String toString(){
        return prenom + " " + nom + "\n";
    }
}
