package com.antoine.gestioncrous.dao;


import com.antoine.gestioncrous.model.Personne;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class PersonneDB {
    private Session session;

    public PersonneDB(Session session){
        this.session = session;
    }

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
}
