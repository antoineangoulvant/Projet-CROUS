package com.antoine.gestioncrous.model;

import java.sql.Date;

/**
 * Classe Bail représentant le contrat de location faisant le lien entre un bien, son propriétaire et le locataire.
 */
public class Bail {
    private long id;
    private double loyer;
    private Date debut;
    private int periode;
    private Personne locataire;
    private Bien bien;

    /**
     * Constructeur de la classe Bail
     */
    public Bail(){}

    /**
     * Méthode permettant de récupérer l'ID
     * @return l'ID d'un bail
     */
    public long getId() {
        return id;
    }

    /**
     * Méthode permettant de modifier l'ID
     * @param id nouvel id du bail
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Méthode permettant de récupérer le loyer d'un bail
     * @return le loyer du bail
     */
    public double getLoyer() {
        return loyer;
    }

    /**
     * Méthode permettant de modifier le loyer d'un bail
     * @param loyer loyer d'un bail
     */
    public void setLoyer(double loyer) {
        this.loyer = loyer;
    }

    /**
     * Méthode permettant de récupérer la date de début du bail
     * @return date de début du bail
     */
    public Date getDebut() {
        return debut;
    }

    /**
     * Méthode permettant de modifier la date de début du bail
     * @param debut date de début d'un bail
     */
    public void setDebut(Date debut) {
        this.debut = debut;
    }

    /**
     * Méthode permettant de récupérer la durée d'un bail (jours)
     * @return durée d'un bail
     */
    public int getPeriode() {
        return periode;
    }

    /**
     * Méthode permettant de modifier la durée d'un bail (jours)
     * @param periode période d'un bail en jours
     */
    public void setPeriode(int periode) {
        this.periode = periode;
    }

    /**
     * Méthode permettant de récupérer le locataire concerné par le bail
     * @return la personne qui loue le bien
     */
    public Personne getLocataire() {
        return locataire;
    }

    /**
     * Méthode permettant de modifier le locataire concerné par le bail
     * @param locataire personne locataire de l'appartement
     */
    public void setLocataire(Personne locataire) {
        this.locataire = locataire;
    }

    /**
     * Méthode permettant de récupérer le bien concerné par le bail
     * @return bien concerné par le bail
     */
    public Bien getBien() {
        return bien;
    }

    /**
     * Méthode permettant de modifier le bien concerné par le bail
     * @param bien bien concerné par le bail
     */
    public void setBien(Bien bien) {
        this.bien = bien;
    }
}
