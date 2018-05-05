package com.antoine.gestioncrous.view;

import com.antoine.gestioncrous.Crous;
import com.antoine.gestioncrous.HibernateUtil;
import com.antoine.gestioncrous.model.Bail;
import com.antoine.gestioncrous.model.Bien;
import com.antoine.gestioncrous.model.Personne;
import org.hibernate.Hibernate;
import org.hibernate.Session;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class IHMCUI {
    private Crous monCrous;

    public IHMCUI(Crous c){
        this.monCrous = c;
        afficherMenu();

    }

    private void afficherMenu(){
        int choix;
        Scanner sc = new Scanner(System.in);

        do{
            System.out.println(
                    "\n######################################################"+
                    "\n#### Bienvenue sur le système de gestion du CROUS ####" +
                    "\n####            Que voulez-vous faire ?           ####" +
                    "\n######################################################\n\n"+
                    "1  - Afficher les personnes\n" +
                    "2  - Afficher les biens\n" +
                    "3  - Afficher les bails\n" +
                    "4  - Afficher les biens d'une nature\n" +
                    "10 - Quitter"

            );
            choix = sc.nextInt();

            switch (choix){
                case 1:
                    affichagePersonnes();
                    break;
                case 2:
                    affichageBiens();
                    break;
                case 3:
                    affichageBails();
                    break;
                case 4:
                    System.out.println("De quelle nature voulez vous afficher les biens ?");
                    System.out.println("Appartement (1) | Garage (2) | Villa (3)");
                    int choixNature = sc.nextInt();
                    affichageBiensNature(choixNature);
                    break;
                case 10:
                    System.out.println("Fermeture... A bientôt !");
                    System.exit(42);
                    break;
                default:
                    System.out.println("Saisie incorrect veuillez réessayer");
            }
        }while(choix != 10);

    }

    private void affichageBiensNature(int choixNature) {
        List resultat = monCrous.getListeBiensNature(choixNature);
        System.out.println("+-------+----------------------------------------------------+-----------------+");
        System.out.println(String.format("| %-5s | ","ID")+String.format("%-50s | ","Adresse")+String.format("%-15s |","Nature"));
        System.out.println("+-------+----------------------------------------------------+-----------------+");
        for(Iterator i = resultat.iterator(); i.hasNext();){
            Bien bien= (Bien) i.next();
            System.out.println(String.format("| %-5s | ",bien.getId())+String.format("%-50s | ",bien.getAdresse())+String.format("%-15s |",bien.getNature().getCode()));
        }
        System.out.println("+-------+----------------------------------------------------+-----------------+");
        monCrous.stopSession();
    }

    private void affichagePersonnes(){
        List resultat = monCrous.getListePersonnes();
        System.out.println("+-------+--------------------------------+--------------------------------+----------------------------------------------------------------------------------+");
        System.out.println(String.format("| %-5s","ID")+String.format(" | %-30s | ","Prénom")+String.format("%-30s | ","Nom")+String.format("%-80s |","Adresse"));
        System.out.println("+-------+--------------------------------+--------------------------------+----------------------------------------------------------------------------------+");
        for(Iterator i = resultat.iterator(); i.hasNext();) {
            Personne personne = (Personne) i.next();
            System.out.println(String.format("| %-5s",personne.getId())+String.format(" | %-30s | ",personne.getPrenom())+String.format("%-30s | ",personne.getNom())+String.format("%-80s |",personne.getAdresse()));
        }
        System.out.println("+-------+--------------------------------+--------------------------------+----------------------------------------------------------------------------------+");
    }

    private void affichageBiens(){
        List resultat = monCrous.getListeBiens();
        System.out.println("+-------+----------------------------------------------------+-----------------+");
        System.out.println(String.format("| %-5s | ","ID")+String.format("%-50s | ","Adresse")+String.format("%-15s |","Nature"));
        System.out.println("+-------+----------------------------------------------------+-----------------+");
        for(Iterator i = resultat.iterator(); i.hasNext();){
            Bien bien= (Bien) i.next();
            System.out.println(String.format("| %-5s | ",bien.getId())+String.format("%-50s | ",bien.getAdresse())+String.format("%-15s |",bien.getNature().getCode()));
        }
        System.out.println("+-------+----------------------------------------------------+-----------------+");
        monCrous.stopSession();
    }

    private void affichageBails(){
        List resultat = monCrous.getListeBails();
        System.out.println("+-------+-------------------------------------------------------------------------+-------------------------------------------+--------+-------------+-------+");
        System.out.println(String.format("| %-5s","ID")+String.format(" | %-70s ","Adresse du bien")+String.format(" | %-40s ","Locataire")+String.format(" | %-4s ","Loyer")+String.format(" | %-10s ","Date début")+String.format(" | %-5s |","Durée"));
        System.out.println("+-------+-------------------------------------------------------------------------+-------------------------------------------+--------+-------------+-------+");
        for(Iterator i = resultat.iterator(); i.hasNext();){
            Bail bail = (Bail) i.next();
            System.out.println(String.format("| %-5s",bail.getId())+String.format(" | %-70s ",bail.getBien().getAdresse())+String.format(" | %-40s ",bail.getLocataire().getPrenom() + " " + bail.getLocataire().getNom())+String.format(" | %-4s ",bail.getLoyer())+String.format(" | %-10s ",bail.getDebut())+String.format(" | %-5s |",bail.getPeriode()));
        }
        System.out.println("+-------+-------------------------------------------------------------------------+-------------------------------------------+--------+-------------+-------+");
        monCrous.stopSession();
    }
}


