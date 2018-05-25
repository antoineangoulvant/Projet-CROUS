package com.antoine.gestioncrous.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class NatureDB {
    private Session session;

    public NatureDB(Session session){
        this.session = session;
    }

    public List getListeNatures(){
        Transaction tx = null;
        List listeNatures = null;

        try {
            tx = session.beginTransaction();
            listeNatures = session.createQuery("From Nature").list();
        }
        catch (HibernateException e){
            if(tx != null) tx.rollback();
            e.printStackTrace();
        }
        finally {
            tx.commit();
        }
        return listeNatures;
    }
}
