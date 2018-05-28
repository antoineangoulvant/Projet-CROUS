package com.antoine.gestioncrous.dao;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Classe permettant de créer l'instance de fabrication de session
 */
public class ConnexionDB {
    private static SessionFactory factory;

    /**
     * Constructeur et initialisation de l'instance de fabrication de session
     */
    public ConnexionDB(){
        try {
            factory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Création de la sessionFactory impossible : " + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    /**
     * Méthode permettant de récupérer l'instance de fabrication de session
     * @return instance de fabrication de session (SessionFactory)
     */
    public SessionFactory getFactory(){
        return factory;
    }
}
