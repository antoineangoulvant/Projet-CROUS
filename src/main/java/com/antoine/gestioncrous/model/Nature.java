package com.antoine.gestioncrous.model;

import java.util.HashSet;
import java.util.Set;

/**
 * Classe représentant la Nature d'un bien
 */
public class Nature {
    private long id;
    private String code;
    private double pourcentage;
    private Set biens = new HashSet<Bien>();

    /**
     * Constructeur de la classe Nature
     */
    public Nature(){}

    /**
     * Méthode permettant de récupérer l'ID d'une nature
     * @return id de la nature
     */
    public long getId() {
        return id;
    }

    /**
     * Méthode permettant de modifier l'ID d'une nature
     * @param id id de la nature
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Méthode permettant de récupérer le code d'une nature
     * @return code de la nature
     */
    public String getCode() {
        return code;
    }

    /**
     * Méthode permettant de modifier le code d'une nature
     * @param code code de le nature
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * Méthode permettant de récupérer le pourcentage récupéré par le CROUS pour un certain type de bien
     * @return pourcentage de commission
     */
    public double getPourcentage() {
        return pourcentage;
    }

    /**
     * Méthode permettant de modifier le pourcentage récupéré par le CROUS pour un certain type de bien
     * @param pourcentage pourcentage de commission
     */
    public void setPourcentage(double pourcentage) {
        this.pourcentage = pourcentage;
    }

    /**
     * Méthode permettant de récupérer la liste de biens d'une certaine nature
     * @return liste de biens
     */
    public Set getBiens() {
        return biens;
    }

    /**
     * Méthode permettant de modifier la liste de biens d'une certaine nature
     * @param biens liste de biens
     */
    public void setBiens(Set biens) {
        this.biens = biens;
    }
}
