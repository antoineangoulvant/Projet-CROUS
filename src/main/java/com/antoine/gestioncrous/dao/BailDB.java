package com.antoine.gestioncrous.dao;

import com.antoine.gestioncrous.model.Bail;
import com.antoine.gestioncrous.model.Bien;
import com.antoine.gestioncrous.model.Personne;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.Date;
import java.util.List;

/**
 * Classe permettant d'accéder aux entités Bail de la BDD
 */
public class BailDB {
    private Session session;

    /**
     * Constructeur de la classe BailDB
     * @param session A la creation de la classe BailDB une session Hibernate doit être passé en paramètre
     */
    public BailDB(Session session){
        this.session = session;
    }

    /**
     * Méthode permettant de récupérer la liste des bails contenu dans la BDD
     * @return la liste des bails contenu dans la BDD
     */
    public List getListeBails(){
        Transaction tx = null;
        List listeBails = null;

        try {
            tx = session.beginTransaction();
            listeBails = session.createQuery("From Bail").list();
            tx.commit();
        }
        catch (HibernateException e){
            if(tx != null) tx.rollback();
            e.printStackTrace();
        }
        return listeBails;
    }

    /**
     * Méthode permettant la création d'un bail
     * @param loyer du bail
     * @param jour de début du bail
     * @param mois de début du bail
     * @param annee de début du bail
     * @param periode durée du bail en jour
     * @param bien Le bien concerné par le bail
     * @param locataire Le locataire (Personne) concerné par le bail
     */
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
            tx.commit();
        }
        catch (HibernateException e){
            if(tx != null) tx.rollback();
            e.printStackTrace();
        }
    }

    /**
     * Méthode permettant de supprimer un bail
     * @param id_bail du bail à supprimer
     */
    public void supprimerBail(int id_bail){
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            Bail bail = (Bail) session.get(Bail.class,id_bail);
            session.delete(bail);
            tx.commit();
        }
        catch (HibernateException e){
            if(tx != null) tx.rollback();
            e.printStackTrace();
        }
    }

    /**
     * Méthode permettant de mettre à jour le loyer du bail
     * @param id_bail du bail concerné
     * @param loyer à mettre à jour
     */
    public void actualiserBail(int id_bail, double loyer){
        Transaction tx = null;

        try{
            tx = session.beginTransaction();
            Bail bail = (Bail) session.get(Bail.class,id_bail);
            bail.setLoyer(loyer);
            session.update(bail);
            tx.commit();
        }
        catch (HibernateException e){
            if(tx != null) tx.rollback();
            e.printStackTrace();
        }
    }
}
