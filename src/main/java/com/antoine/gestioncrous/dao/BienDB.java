package com.antoine.gestioncrous.dao;

import com.antoine.gestioncrous.model.Bien;
import com.antoine.gestioncrous.model.Nature;
import com.antoine.gestioncrous.model.Personne;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class BienDB {
    private Session session;

    public BienDB(Session session){
        this.session = session;
    }

    public List getListeBiens(){
        Transaction tx = null;
        List listeBiens = null;

        try {
            tx = session.beginTransaction();
            listeBiens = session.createQuery("From Bien").list();
            tx.commit();
        }
        catch (HibernateException e){
            if(tx != null) tx.rollback();
            e.printStackTrace();
        }
        return listeBiens;
    }

    public void ajouterBien(String adresse, Nature nature, Personne proprietaire){
        Transaction tx = null;

        try{
            tx = session.beginTransaction();

            Bien nouveauBien = new Bien();
            nouveauBien.setAdresse(adresse);
            nouveauBien.setNature(nature);
            nouveauBien.setProprietaire(proprietaire);

            session.save(nouveauBien);
            tx.commit();
        }
        catch (HibernateException e){
            if(tx != null) tx.rollback();
            e.printStackTrace();
        }
    }
}
