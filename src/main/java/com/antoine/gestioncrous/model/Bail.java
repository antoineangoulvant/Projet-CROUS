package com.antoine.gestioncrous.model;

import java.sql.Date;

public class Bail {
    private long id;
    private double loyer;
    private Date debut;
    private int periode;
    private Personne locataire;
    private Bien bien;

    public Bail(){}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getLoyer() {
        return loyer;
    }

    public void setLoyer(double loyer) {
        this.loyer = loyer;
    }

    public Date getDebut() {
        return debut;
    }

    public void setDebut(Date debut) {
        this.debut = debut;
    }

    public int getPeriode() {
        return periode;
    }

    public void setPeriode(int periode) {
        this.periode = periode;
    }

    public Personne getLocataire() {
        return locataire;
    }

    public void setLocataire(Personne locataire) {
        this.locataire = locataire;
    }

    public Bien getBien() {
        return bien;
    }

    public void setBien(Bien bien) {
        this.bien = bien;
    }
}
