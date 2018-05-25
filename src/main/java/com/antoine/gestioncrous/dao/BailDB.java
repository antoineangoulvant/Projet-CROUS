package com.antoine.gestioncrous.dao;

import com.antoine.gestioncrous.model.Bail;
import com.antoine.gestioncrous.model.Bien;
import com.antoine.gestioncrous.model.Personne;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.Date;
import java.util.List;

public class BailDB {
    private Session session;

    public BailDB(Session session){
        this.session = session;
    }

    public List getListeBails(){
        Transaction tx = null;
        List listeBails = null;

        try {
            tx = session.beginTransaction();
            listeBails = session.createQuery("From Bail").list();
        }
        catch (HibernateException e){
            if(tx != null) tx.rollback();
            e.printStackTrace();
        }
        finally {
            tx.commit();
        }
        return listeBails;
    }

    public void ajouterBail(double loyer, int jour, int mois, int annee, int periode, Bien bien, Personne locataire){
        Transaction tx = null;

        try{
            tx = session.beginTransaction();

            Date dateDebut = new Date(annee-1900,mois-1,jour);
            Bail nouveauBail = new Bail();
            nouveauBail.setLoyer(loyer);
            nouveauBail.setPeriode(periode);
            nouveauBail.setLocataire(locataire);
            nouveauBail.setBien(bien);
            nouveauBail.setDebut(dateDebut);

            session.save(nouveauBail);
        }
        catch (HibernateException e){
            if(tx != null) tx.rollback();
            e.printStackTrace();
        }
        finally {
            tx.commit();
        }
    }
}
