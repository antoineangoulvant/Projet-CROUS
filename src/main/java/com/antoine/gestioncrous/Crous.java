package com.antoine.gestioncrous;

import com.antoine.gestioncrous.model.*;
import com.antoine.gestioncrous.view.Fenetreprincipal;
import com.antoine.gestioncrous.view.IHMCUI;
import org.hibernate.Session;

import java.util.HashSet;
import java.util.List;
import java.util.logging.Level;

public class Crous {
    private HashSet<Bien> biens;
    private HashSet<Personne> personnes;
    private HashSet<Nature> natures;
    private Fenetreprincipal maFenetre;
    private Session session;

    public Crous(){
        super();
        this.biens = new HashSet<Bien>();
        this.personnes = new HashSet<Personne>();
        this.natures = new HashSet<Nature>();
    }

    public void ajouterBien(Bien b){
        this.biens.add(b);
    }

    public void ajouterNature(Nature n){
        this.natures.add(n);
    }

    public boolean invKeyNature(){
        Boolean isConsistent = true;
        for(Nature n1 : natures){
            for(Nature n2 : natures){
                if(n1!=n2) isConsistent = isConsistent && (n1.getCode() != n2.getCode());
                if(!isConsistent) return false;
            }
        }
        return isConsistent;
    }

    /*public boolean invXor(){
        Boolean isConsistent = true;
        for(Bien b : biens){
            for(Bail l : b.getLoue()){
                isConsistent = isConsistent && (l.getLocataire() != b.getProprietaire());
            }
        }
        return isConsistent;
    }*/

    protected Boolean inv(){
        return this.invKeyBien() && this.invKeyNature() && this.invKeyPersonne() /*&& this.invXor()*/;
    }

    private boolean invKeyPersonne() {
        return true;
    }

    private Boolean invKeyBien() {
        return true;
    }

    /*private void ajouterPersonne(String nom, String prenom, String adresse) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        Personne nouvelleP = new Personne();
        nouvelleP.setNom(nom);
        nouvelleP.setPrenom(prenom);
        nouvelleP.setAdresse(adresse);
        session.save(nouvelleP);

        session.getTransaction().commit();
    }*/

    public List getListeBiensNature(int id_nature){
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        List resultat = session.createQuery("From Bien WHERE ID_NATURE = " + id_nature).list();
        return resultat;
    }

    public List getListePersonnes(){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        List resultat = session.createQuery("From Personne").list();
        session.getTransaction().commit();
        return resultat;
    }

    public List getListeBiens(){
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        List resultat = session.createQuery("From Bien").list();
        //session.getTransaction().commit();
        return resultat;
    }

    public List getListeBails(){
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        List resultat = session.createQuery("From Bail").list();
        return resultat;
    }

    public void stopSession(){
        session.getTransaction().commit();
    }

    public static void main(String[] args){
        Crous c = new Crous();
        //new IHMCUI(c);
        new Fenetreprincipal(c);
    }
}

