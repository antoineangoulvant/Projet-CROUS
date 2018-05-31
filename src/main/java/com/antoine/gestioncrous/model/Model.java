package com.antoine.gestioncrous.model;

import com.antoine.gestioncrous.Crous;
import com.antoine.gestioncrous.dao.*;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

public class Model {
    private static Model instance = null;

    private Model(){}

    public static synchronized Model getInstance(){
        if(instance == null){
            instance = new Model();
        }
        return instance;
    }

    /**
     * Méthode permettant de récupérer toutes les informations contenus dans la base de données
     */
    public void actualiserValeurs(Crous monCrous) {
        Session session = ConnexionDB.getInstance().getFactory().openSession();

        HashSet<Personne> personnes = monCrous.getPersonnes();
        HashSet<Bien> biens = monCrous.getBiens();
        HashSet<Nature> natures = monCrous.getNatures();
        HashSet<Bail> bails = monCrous.getBails();

        personnes.clear();
        PersonneDB personneDB = new PersonneDB(session);
        List listePersonnes =  personneDB.getListePersonnes();
        for(Iterator i = listePersonnes.iterator(); i.hasNext();){
            Personne personne = (Personne) i.next();
            personnes.add(personne);
        }

        natures.clear();
        NatureDB natureDB = new NatureDB(session);
        List listeNature = natureDB.getListeNatures();
        for(Iterator i = listeNature.iterator(); i.hasNext();){
            Nature nature = (Nature) i.next();
            natures.add(nature);
        }

        biens.clear();
        BienDB bienDB = new BienDB(session);
        List listeBiens = bienDB.getListeBiens();
        for(Iterator i = listeBiens.iterator(); i.hasNext();){
            Bien bien = (Bien) i.next();
            biens.add(bien);
        }

        bails.clear();
        BailDB bailDB = new BailDB(session);
        List listeBails = bailDB.getListeBails();
        for(Iterator i = listeBails.iterator(); i.hasNext();){
            Bail bail = (Bail) i.next();
            bails.add(bail);
        }

        monCrous.setBails(bails);
        monCrous.setBiens(biens);
        monCrous.setNatures(natures);
        monCrous.setPersonnes(personnes);

        session.close();
    }

    /**
     * Méthode permettant d'ajouter une personne
     * @param nom nom de la personne
     * @param prenom prénom de la personne
     * @param adresse adresse de la personne
     */
    public void ajouterPersonne(String nom, String prenom, String adresse, Crous monCrous){
        Session session = ConnexionDB.getInstance().getFactory().openSession();
        new PersonneDB(session).ajouterPersonne(nom,prenom,adresse);
        session.close();
        monCrous.actualiserValeurs();
    }

    /**
     * Méthode permettant d'ajouter un bien
     * @param adresse adresse du bien
     * @param id_nature id de la nature du bien
     * @param id_proprietaire id du propriétaire du bien
     */
    public void ajouterBien(String adresse,int id_nature, int id_proprietaire, Crous monCrous){
        Session session = ConnexionDB.getInstance().getFactory().openSession();

        Personne proprietaire = null;
        for(Personne p : monCrous.getPersonnes()){
            if( p.getId() == id_proprietaire ){
                proprietaire = p;
            }
        }

        Nature nature = null;
        for(Nature n : monCrous.getNatures()){
            if( n.getId() == id_nature ){
                nature = n;
            }
        }

        if( nature != null ){
            if( proprietaire != null ){
                new BienDB(session).ajouterBien(adresse,nature,proprietaire);
                session.close();
                monCrous.actualiserValeurs();
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

    /**
     * Méthode permettant d'ajouter un bail
     * @param loyer loyer du bail
     * @param jour jour de début du bail
     * @param mois mois de début du bail
     * @param annee annee de début du bail
     * @param periode période de durée du bail en jours
     * @param id_bien id du bien concerné
     * @param id_locataire id du locataire concerné
     */
    public void ajouterBail(double loyer, int jour, int mois, int annee, int periode, int id_bien, int id_locataire, Crous monCrous){
        Session session = ConnexionDB.getInstance().getFactory().openSession();

        Personne locataire = null;
        for(Personne p : monCrous.getPersonnes()){
            if( p.getId() == id_locataire ){
                locataire = p;
            }
        }

        Bien tempBien = null;
        for(Bien b : monCrous.getBiens()){
            if(b.getId() == id_bien){
                tempBien = b;
            }
        }

        if(locataire != null){
            if( tempBien != null ){
                new BailDB(session).ajouterBail(loyer,jour,mois,annee,periode,tempBien,locataire);
                session.close();
                monCrous.actualiserValeurs();
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

    /**
     * Méthode permettant de récupérer la liste des biens d'une nature
     * @param id_nature id de la nature souhaité
     * @return liste des biens d'une nature
     */
    public List getListeBiensNature(int id_nature){
        Session session = ConnexionDB.getInstance().getFactory().openSession();
        Transaction tx = session.beginTransaction();
        List resultat = session.createQuery("From Bien WHERE ID_NATURE = " + id_nature).list();
        tx.commit();
        return resultat;
    }
}
