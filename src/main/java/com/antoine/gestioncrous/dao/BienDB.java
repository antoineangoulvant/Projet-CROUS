package com.antoine.gestioncrous.dao;

import com.antoine.gestioncrous.model.Bien;
import com.antoine.gestioncrous.model.Nature;
import com.antoine.gestioncrous.model.Personne;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * Classe permettant d'accéder aux entités Bien de la BDD
 */
public class BienDB {
    private Session session;

    /**
     * Constructeur de la classe BienDB
     * @param session A la creation de la classe NatureDB une session Hibernate doit être passé en paramètre
     */
    public BienDB(Session session){
        this.session = session;
    }

    /**
     * Méthode permettant de récupérer la liste des biens de la BDD
     * @return la liste des biens contenu dans la BDD
     */
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

    /**
     * Méthode permettant d'ajouter un bien dans la BDD
     * @param adresse du bien
     * @param nature la nature du bien
     * @param proprietaire le propriétaire du bien
     */
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

    /**
     * Méthode permettant de supprimer un bien
     * @param id_bien du bien devant être supprimé
     */
    public void supprimerBien(int id_bien){
        Transaction tx = null;

        try{
            tx = session.beginTransaction();
            Bien bien = (Bien) session.get(Bien.class,id_bien);
            session.delete(bien);
            tx.commit();
        }
        catch (HibernateException e){
            if(tx != null) tx.rollback();
            e.printStackTrace();
        }
    }
}
