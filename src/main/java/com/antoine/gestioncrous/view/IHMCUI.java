package com.antoine.gestioncrous.view;

import com.antoine.gestioncrous.Crous;
import com.antoine.gestioncrous.model.Bail;
import com.antoine.gestioncrous.model.Bien;
import com.antoine.gestioncrous.model.Personne;

import java.util.*;

/**
 * Classe permettant de faire l'interface de la console entre l'utilisateur et l'application
 */
public class IHMCUI {
    private Crous monCrous;
    private Scanner sc;

    /**
     * Constructeur de la classe IHMCUI
     * @param c Controleur Crous
     */
    public IHMCUI(Crous c){
        this.monCrous = c;
        sc = new Scanner(System.in);
        afficherMenu();
    }

    /**
     * Affichage du menu principal avec les choix
     */
    private void afficherMenu(){
        int choix;

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
                    "5  - Ajouter une personne\n" +
                    "6  - Ajouter un bien\n" +
                    "7  - Ajouter un bail\n" +
                    "10 - Quitter\n"
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
                case 5:
                    afficherAjoutPersonne();
                    break;
                case 6:
                    afficherAjoutBien();
                    break;
                case 7:
                    afficherAjoutBail();
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

    /**
     * Méthode permettant d'afficher les biens d'une nature
     * @param choixNature identifiant d'une nature
     */
    private void affichageBiensNature(int choixNature) {
        List listeBiens = monCrous.getListeBiensNature(choixNature);

        System.out.println("+-------+----------------------------------------------------+-----------------+");
        System.out.println(String.format("| %-5s | ","ID")+String.format("%-50s | ","Adresse")+String.format("%-15s |","Nature"));
        System.out.println("+-------+----------------------------------------------------+-----------------+");
        for(Object b : listeBiens){
            Bien bien = (Bien) b;
            System.out.println(String.format("| %-5s | ",bien.getId())+String.format("%-50s | ",bien.getAdresse())+String.format("%-15s |",bien.getNature().getCode()));
        }
        System.out.println("+-------+----------------------------------------------------+-----------------+");
    }

    /**
     * Méthode permettant d'afficher la liste de personnes
     */
    private void affichagePersonnes(){
        System.out.println("+-------+--------------------------------+--------------------------------+----------------------------------------------------------------------------------+");
        System.out.println(String.format("| %-5s","ID")+String.format(" | %-30s | ","Prénom")+String.format("%-30s | ","Nom")+String.format("%-80s |","Adresse"));
        System.out.println("+-------+--------------------------------+--------------------------------+----------------------------------------------------------------------------------+");
        for(Personne personne : monCrous.getPersonnes()) {
            System.out.println(String.format("| %-5s",personne.getId())+String.format(" | %-30s | ",personne.getPrenom())+String.format("%-30s | ",personne.getNom())+String.format("%-80s |",personne.getAdresse()));
        }
        System.out.println("+-------+--------------------------------+--------------------------------+----------------------------------------------------------------------------------+");
    }

    /**
     * Methode permettant d'afficher la liste des biens
     */
    private void affichageBiens(){
        System.out.println("+-------+----------------------------------------------------+--------------------------------+-----------------+");
        System.out.println(String.format("| %-5s | ","ID")+String.format("%-50s | ","Adresse")+String.format("%-30s | ","Propriétaire")+String.format("%-15s |","Nature"));
        System.out.println("+-------+----------------------------------------------------+--------------------------------+-----------------+");
        for(Bien bien : monCrous.getBiens()){
            System.out.println(String.format("| %-5s | ",bien.getId())+String.format("%-50s | ",bien.getAdresse())+String.format("%-30s | ",bien.getProprietaire().getPrenom()+" "+bien.getProprietaire().getNom())+String.format("%-15s |",bien.getNature().getCode()));
        }
        System.out.println("+-------+----------------------------------------------------+--------------------------------+-----------------+");
    }

    /**
     * Methode permettant d'afficher la liste des bails
     */
    private void affichageBails(){
        System.out.println("+-------+-------------------------------------------------------------------------+-------------------------------------------+--------+-------------+-------+");
        System.out.println(String.format("| %-5s","ID")+String.format(" | %-70s ","Adresse du bien")+String.format(" | %-40s ","Locataire")+String.format(" | %-4s ","Loyer")+String.format(" | %-10s ","Date début")+String.format(" | %-5s |","Durée"));
        System.out.println("+-------+-------------------------------------------------------------------------+-------------------------------------------+--------+-------------+-------+");
        for(Bail bail : monCrous.getBails()){
            System.out.println(String.format("| %-5s",bail.getId())+String.format(" | %-70s ",bail.getBien().getAdresse())+String.format(" | %-40s ",bail.getLocataire().getPrenom() + " " + bail.getLocataire().getNom())+String.format(" | %-4s ",bail.getLoyer())+String.format(" | %-10s ",bail.getDebut())+String.format(" | %-5s |",bail.getPeriode()));
        }
        System.out.println("+-------+-------------------------------------------------------------------------+-------------------------------------------+--------+-------------+-------+");
    }

    /**
     * Affichage de la saisie de l'ajout d'une personne
     */
    private void afficherAjoutPersonne(){
        System.out.println("Veuillez saisir les informations de la personne :");
        System.out.print("Prénom : ");
        sc.nextLine();
        String prenom = sc.nextLine();
        System.out.print("Nom : ");
        String nom = sc.nextLine();
        System.out.print("Adresse : ");
        String adresse = sc.nextLine();
        monCrous.ajouterPersonne(nom,prenom,adresse);
    }

    /**
     * Affichage de la saisie de l'ajout d'un bien
     */
    private void afficherAjoutBien(){
        System.out.println("Veuillez saisir les informations du bien : ");
        System.out.print("Adresse : ");
        sc.nextLine();
        String adresseBien = sc.nextLine();
        System.out.println("Appartement (1) | Garage (2) | Villa (3)");
        System.out.print("ID Nature : ");
        int id_nature = sc.nextInt();
        System.out.print("ID Propriétaire : ");
        int id_proprietaire = sc.nextInt();
        monCrous.ajouterBien(adresseBien,id_nature,id_proprietaire);
    }

    /**
     * Affichage de la saisie de l'ajout d'un bail
     */
    private void afficherAjoutBail(){
        System.out.println("Veuillez saisir les informations du bail : ");
        System.out.print("Montant du loyer : ");
        double loyer = sc.nextDouble();
        System.out.print("Jour du début : ");
        int jourDebut = sc.nextInt();
        System.out.print("Mois du début : ");
        int moisDebut = sc.nextInt();
        System.out.print("Année du début : ");
        int anneeDebut = sc.nextInt();
        System.out.print("Durée (en jours) : ");
        int duree = sc.nextInt();
        System.out.print("ID du bien : ");
        int id_bien = sc.nextInt();
        System.out.print("ID du locataire : ");
        int id_locataire = sc.nextInt();

        monCrous.ajouterBail(loyer,jourDebut,moisDebut,anneeDebut,duree,id_bien,id_locataire);
    }
}


