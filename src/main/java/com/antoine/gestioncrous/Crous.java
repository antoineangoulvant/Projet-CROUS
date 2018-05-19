package com.antoine.gestioncrous;

import com.antoine.gestioncrous.model.*;
import com.antoine.gestioncrous.view.Fenetreprincipal;
import com.antoine.gestioncrous.view.IHMCUI;
import org.hibernate.Session;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

public class Crous {
    private HashSet<Personne> personnes;
    private HashSet<Bail> bails;
    private HashSet<Nature> natures;
    private Fenetreprincipal maFenetre;
    private Session session;

    private Crous(){
        super();
        this.biens = new HashSet<Bien>();
        this.personnes = new HashSet<Personne>();
        this.bails = new HashSet<Bail>();
        this.natures = new HashSet<Nature>();
        this.actualiserValeurs();
    }

    private void actualiserValeurs() {
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        List listeBiens = session.createQuery("From Bien").list();
        for(Iterator i = listeBiens.iterator(); i.hasNext();){
            Bien bien = (Bien) i.next();
            this.biens.add(bien);
        }

        List listePersonnes = session.createQuery("From Personne ORDER BY id_personne").list();
        for(Iterator i = listePersonnes.iterator(); i.hasNext();){
            Personne personne = (Personne) i.next();
            this.personnes.add(personne);
        }

        List listeBails = session.createQuery("From Bail").list();
        for(Iterator i = listeBails.iterator(); i.hasNext();){
            Bail bail = (Bail) i.next();
            this.bails.add(bail);
        }

        List listeNature = session.createQuery("From Nature").list();
        for(Iterator i = listeNature.iterator(); i.hasNext();){
            Nature nature = (Nature) i.next();
            this.natures.add(nature);
        }

        session.getTransaction().commit();
    }

    public void ajouterBien(Bien b){
        this.biens.add(b);
    }

    public void ajouterNature(Nature n){
        this.natures.add(n);
    }

    private boolean invKeyNature(){
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

    public void ajouterPersonne(String nom, String prenom, String adresse) {
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        Personne nouvelleP = new Personne();
        nouvelleP.setNom(nom);
        nouvelleP.setPrenom(prenom);
        nouvelleP.setAdresse(adresse);
        session.save(nouvelleP);
        personnes.add(nouvelleP);

        session.getTransaction().commit();
    }

    public List getListeBiensNature(int id_nature){
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        List resultat = session.createQuery("From Bien WHERE ID_NATURE = " + id_nature).list();
        return resultat;
    }

    public void stopSession(){
        session.getTransaction().commit();
    }

    private HashSet<Bien> biens;

    public HashSet<Bien> getBiens() {
        return biens;
    }

    public HashSet<Personne> getPersonnes() {
        return personnes;
    }

    public HashSet<Bail> getBails() {
        return bails;
    }

    public HashSet<Nature> getNatures() {
        return natures;
    }

    public static void main(String[] args){
        Crous c = new Crous();
        new IHMCUI(c);
        //new Fenetreprincipal(c);
    }
}

