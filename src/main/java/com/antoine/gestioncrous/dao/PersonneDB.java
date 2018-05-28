package com.antoine.gestioncrous.dao;


import com.antoine.gestioncrous.model.Personne;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * Classe permettant d'accéder aux entités Personne de la BDD
 */
public class PersonneDB {
    private Session session;

    /**
     * Constructeur de la classe PersonneDB
     * @param session A la creation de la classe PersonneDB une session Hibernate doit être passé en paramètre
     */
    public PersonneDB(Session session){
        this.session = session;
    }

    /**
     * Méthode permettant de récupérer la liste des personnes contenu dans la BDD
     * @return la liste des personnes contenu dans la BDD
     */
    public List getListePersonnes(){
        Transaction tx = null;
        List listePersonnes = null;

        try {
            tx = session.beginTransaction();
            listePersonnes = session.createQuery("From Personne").list();
            tx.commit();
        }
        catch (HibernateException e){
            if(tx != null) tx.rollback();
            e.printStackTrace();
        }
        return listePersonnes;
    }

    /**
     * Méthode permettant d'ajouter une personne
     * @param nom de la personne
     * @param prenom de la personne
     * @param adresse de la personne
     */
    public void ajouterPersonne(String nom, String prenom, String adresse) {
        Transaction tx = null;

        try{
            tx = session.beginTransaction();

            Personne nouvellePersonne = new Personne();
            nouvellePersonne.setNom(nom);
            nouvellePersonne.setPrenom(prenom);
            nouvellePersonne.setAdresse(adresse);
            session.save(nouvellePersonne);
            tx.commit();
        }
        catch (HibernateException e){
            if(tx != null) tx.rollback();
            e.printStackTrace();
        }
    }

    /**
     * Méthode permettant de supprimer une personne
     * @param id_personne de la personne à supprimer
     */
    public void supprimerPersonne(int id_personne){
        Transaction tx = null;

        try{
            tx = session.beginTransaction();
            Personne personne = (Personne) session.get(Personne.class,id_personne);
            session.delete(personne);
            tx.commit();
        }
        catch (HibernateException e){
            if(tx != null) tx.rollback();
            e.printStackTrace();
        }
    }

    /**
     * Méthode permettant de mettre à jour à l'adresse d'une personne
     * @param id_personne de la personne dont on veut actualiser l'adresse
     * @param adresse nouvelle adresse de la personne
     */
    public void actualiserPersonne(int id_personne, String adresse){
        Transaction tx = null;

        try{
            tx = session.beginTransaction();
            Personne personne = (Personne) session.get(Personne.class,id_personne);
            personne.setAdresse(adresse);
            session.update(personne);
            tx.commit();
        }
        catch (HibernateException e){
            if(tx != null) tx.rollback();
            e.printStackTrace();
        }
    }
}
