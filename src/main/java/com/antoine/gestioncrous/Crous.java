package com.antoine.gestioncrous;

import com.antoine.gestioncrous.model.*;
import com.antoine.gestioncrous.view.IHMCUI;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Classe centrale de l'application qui fait office de controleur
 */
public class Crous {
    private HashSet<Personne> personnes;
    private HashSet<Bail> bails;
    private HashSet<Nature> natures;
    private HashSet<Bien> biens;
    private IHMCUI monIhm;

    /**
     * Constructeur de la classe CROUS
     */
    private Crous(){
        super();
        this.biens = new HashSet<Bien>();
        this.personnes = new HashSet<Personne>();
        this.bails = new HashSet<Bail>();
        this.natures = new HashSet<Nature>();

        this.actualiserValeurs();

        monIhm = new IHMCUI(this);
    }

    public void actualiserValeurs(){
        Model.getInstance().actualiserValeurs(this);
    }

    /**
     * Methode permettant de vérifier la consistance des données d'une nature et d'éviter la duplication
     * @return vrai si le consistance est respectée
     */
    private boolean invKeyNature(){
        Boolean isConsistent = true;
        for(Nature n1 : natures){
            for(Nature n2 : natures){
                if(n1!=n2) isConsistent = isConsistent && (!n1.getCode().equals(n2.getCode()));
                if(!isConsistent) return false;
            }
        }
        return isConsistent;
    }

    /**
     * Méthode permettant de vérifier qu'un propriétaire ne peut pas louer un bien ou qu'un locataire ne peut pas être propriétaire
     * @return vrai si le consistance est respectée
     */
    public boolean invXor(){
        Boolean isConsistent = true;
        for(Bien b : biens){
            Set listeBails = b.getBails();
            for(Iterator i = listeBails.iterator(); i.hasNext();){
                Bail l = (Bail) i.next();
                isConsistent = isConsistent && (!l.getLocataire().equals(b.getProprietaire()));
            }
        }
        return isConsistent;
    }

    /**
     * Methode permettant de vérifier la consistance des données d'une personne et d'éviter la duplication
     * @return vrai si le consistance est respectée
     */
    private boolean invKeyPersonne() {
        Boolean isConsistent = true;
        for(Personne p1 : personnes){
            for(Personne p2 : personnes){
                if(p1!=p2) isConsistent = isConsistent && (p1.getId() != p2.getId());
                if(!isConsistent) return false;
            }
        }
        return isConsistent;
    }

    /**
     * Methode permettant de vérifier la consistance des données d'un bien et d'éviter la duplication
     * @return vrai si le consistance est respectée
     */
    private Boolean invKeyBien() {
        Boolean isConsistent = true;
        for(Bien b1 : biens){
            for(Bien b2 : biens){
                if(b1!=b2) isConsistent = isConsistent && (b1.getId() != b2.getId());
                if(!isConsistent) return false;
            }
        }
        return isConsistent;
    }

    /**
     * Méthode d'appel de toutes les classes de vérifications de la consistance
     * @return vrai si le consistance est respectée
     */
    protected Boolean inv(){
        return this.invKeyBien() && this.invKeyNature() && this.invKeyPersonne() && this.invXor();
    }

    public void ajouterBien(String adresse,int id_nature, int id_proprietaire){
        Model.getInstance().ajouterBien(adresse,id_nature,id_proprietaire,this);
    }

    public void ajouterPersonne(String nom, String prenom, String adresse){
        Model.getInstance().ajouterPersonne(nom,prenom,adresse,this);
    }

    public void ajouterBail(double loyer, int jour, int mois, int annee, int periode, int id_bien, int id_locataire){
        Model.getInstance().ajouterBail(loyer,jour,mois,annee,periode,id_bien,id_locataire,this);
    }

    public List getListeBiensNature(int id_nature){
        return Model.getInstance().getListeBiensNature(id_nature);
    }

    /**
     * Méthode permettant de récupérer les biens de l'application
     * @return liste des biens de l'application
     */
    public HashSet<Bien> getBiens() {
        return biens;
    }

    /**
     * Méthode permettant de récupérer les personnes de l'application
     * @return liste des personnes de l'application
     */
    public HashSet<Personne> getPersonnes() {
        return personnes;
    }

    /**
     * Méthode permettant de récupérer les bails de l'application
     * @return liste des biens de l'application
     */
    public HashSet<Bail> getBails() {
        return bails;
    }

    /**
     * Méthode permettant de récupérer les natures de l'application
     * @return liste des natures de l'application
     */
    public HashSet<Nature> getNatures() {
        return natures;
    }

    public void setPersonnes(HashSet<Personne> personnes) {
        this.personnes = personnes;
    }

    public void setBails(HashSet<Bail> bails) {
        this.bails = bails;
    }

    public void setNatures(HashSet<Nature> natures) {
        this.natures = natures;
    }

    public void setBiens(HashSet<Bien> biens) {
        this.biens = biens;
    }

    /**
     * Méthode permettant de lancer l'application
     * @param args valeurs passés en paramètres (inutile)
     */
    public static void main(String[] args){
        new Crous();
    }
}

