package com.antoine.gestioncrous;

import com.antoine.gestioncrous.model.Personne;
import org.hibernate.Session;

import java.util.Scanner;

public class TestBD {

    public static void main(String[] args){
        TestBD test = new TestBD();
        Scanner sc = new Scanner(System.in);

        System.out.println("1 : Ajouter personne");
        int choix = sc.nextInt();

        switch (choix){
            case 1:
                System.out.println("Saisir successivement le nom, le prénom et l'adresse de la personne");
                String nom = sc.next();
                String prenom = sc.next();
                String adresse = sc.next();
                System.out.println(prenom + " " + nom + "\n Adresse : " + adresse + "\nConfirmer ? (y/n)");
                String confirm = sc.next();
                if( confirm.equals("y")) ajouterPersonne(nom,prenom,adresse);
                else System.out.println("Annulation...");
        }
    }

    private static void ajouterPersonne(String nom, String prenom, String adresse) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        Personne nouvelleP = new Personne();
        nouvelleP.setNom(nom);
        nouvelleP.setPrenom(prenom);
        nouvelleP.setAdresse(adresse);
        session.save(nouvelleP);

        session.getTransaction().commit();
    }
}
