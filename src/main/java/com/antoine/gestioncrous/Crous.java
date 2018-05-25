package com.antoine.gestioncrous;

import com.antoine.gestioncrous.dao.*;
import com.antoine.gestioncrous.model.*;
import com.antoine.gestioncrous.view.IHMCUI;
import org.hibernate.Session;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class Crous {
    private HashSet<Personne> personnes;
    private HashSet<Bail> bails;
    private HashSet<Nature> natures;
    private HashSet<Bien> biens;
    private ConnexionDB connexionDB;
    private IHMCUI monIhm;

    private Crous(){
        super();
        this.biens = new HashSet<Bien>();
        this.personnes = new HashSet<Personne>();
        this.bails = new HashSet<Bail>();
        this.natures = new HashSet<Nature>();
        this.connexionDB = new ConnexionDB();

        this.actualiserValeurs();

        monIhm = new IHMCUI(this);
    }

    private void actualiserValeurs() {
        Session session = connexionDB.getFactory().openSession();

        personnes.clear();
        PersonneDB personneDB = new PersonneDB(session);
        List listePersonnes =  personneDB.getListePersonnes();
        for(Iterator i = listePersonnes.iterator(); i.hasNext();){
            Personne personne = (Personne) i.next();
            this.personnes.add(personne);
        }

        natures.clear();
        NatureDB natureDB = new NatureDB(session);
        List listeNature = natureDB.getListeNatures();
        for(Iterator i = listeNature.iterator(); i.hasNext();){
            Nature nature = (Nature) i.next();
            this.natures.add(nature);
        }

        biens.clear();
        BienDB bienDB = new BienDB(session);
        List listeBiens = bienDB.getListeBiens();
        for(Iterator i = listeBiens.iterator(); i.hasNext();){
            Bien bien = (Bien) i.next();
            this.biens.add(bien);
        }

        bails.clear();
        BailDB bailDB = new BailDB(session);
        List listeBails = bailDB.getListeBails();
        for(Iterator i = listeBails.iterator(); i.hasNext();){
            Bail bail = (Bail) i.next();
            this.bails.add(bail);
        }

        session.close();
    }

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

    protected Boolean inv(){
        return this.invKeyBien() && this.invKeyNature() && this.invKeyPersonne() && this.invXor();
    }

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

    public void ajouterPersonne(String nom, String prenom, String adresse){
        Session session = connexionDB.getFactory().openSession();
        new PersonneDB(session).ajouterPersonne(nom,prenom,adresse);
        session.close();
        actualiserValeurs();
    }

    public void ajouterBien(String adresse,int id_nature, int id_proprietaire){
        Session session = connexionDB.getFactory().openSession();

        Personne proprietaire = null;
        for(Personne p : getPersonnes()){
            if( p.getId() == id_proprietaire ){
                proprietaire = p;
            }
        }

        Nature nature = null;
        for(Nature n : getNatures()){
            if( n.getId() == id_nature ){
                nature = n;
            }
        }

        if( nature != null ){
            if( proprietaire != null ){
                new BienDB(session).ajouterBien(adresse,nature,proprietaire);
                session.close();
                actualiserValeurs();
            }
            else{
                session.close();
                System.out.println("\n### Cette personne n'existe pas ###");
            }
        }
        else{
            session.close();
            System.out.println("\n### Cette nature n'existe pas ###");
        }
    }

    public void ajouterBail(double loyer, int jour, int mois, int annee, int periode, int id_bien, int id_locataire){
        Session session = connexionDB.getFactory().openSession();

        Personne locataire = null;
        for(Personne p : getPersonnes()){
            if( p.getId() == id_locataire ){
                locataire = p;
            }
        }

        Bien tempBien = null;
        for(Bien b : getBiens()){
            if(b.getId() == id_bien){
                tempBien = b;
            }
        }

        if(locataire != null){
            if( tempBien != null ){
                new BailDB(session).ajouterBail(loyer,jour,mois,annee,periode,tempBien,locataire);
                session.close();
                actualiserValeurs();
            }
            else{
                session.close();
                System.out.println("\n### Ce bien n'existe pas ###");
            }
        }
        else{
            session.close();
            System.out.println("\n### Cette personne n'existe pas ###");
        }
    }

    /*1
    public List getListeBiensNature(int id_nature){
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        List resultat = session.createQuery("From Bien WHERE ID_NATURE = " + id_nature).list();
        //session.getTransaction().commit();
        return resultat;
    }*/

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
        new Crous();
    }
}

