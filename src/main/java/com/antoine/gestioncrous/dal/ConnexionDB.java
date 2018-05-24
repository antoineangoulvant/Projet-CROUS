package com.antoine.gestioncrous.dal;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ConnexionDB {
    private static SessionFactory factory;

    public ConnexionDB(){
        try {
            factory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Création de la sessionFactory impossible : " + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
}
