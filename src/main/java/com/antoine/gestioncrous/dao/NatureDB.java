package com.antoine.gestioncrous.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * Classe permettant d'accéder aux entités Nature de la BDD
 */
public class NatureDB {
    private Session session;

    /**
     * Constructeur de la classe NatureDB
     * @param session A la creation de la classe NatureDB une session Hibernate doit être passé en paramètre
     */
    public NatureDB(Session session){
        this.session = session;
    }

    /**
     * Méthode permettant de récupérer la liste des bien de la bdd
     * @return la liste des natures contenu dans la BDD
     */
    public List getListeNatures(){
        Transaction tx = null;
        List listeNatures = null;

        try {
            tx = session.beginTransaction();
            listeNatures = session.createQuery("From Nature").list();
            tx.commit();
        }
        catch (HibernateException e){
            if(tx != null) tx.rollback();
            e.printStackTrace();
        }
        return listeNatures;
    }
}
