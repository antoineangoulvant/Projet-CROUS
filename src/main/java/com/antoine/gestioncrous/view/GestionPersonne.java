package com.antoine.gestioncrous.view;

import com.antoine.gestioncrous.Crous;
import com.antoine.gestioncrous.model.Personne;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.List;

public class GestionPersonne extends JFrame implements ActionListener {
    private final String[] entetes = {"ID","Prénom","Nom","Adresse"};

    private Crous monCrous;
    private JPanel boutons;
    private JTable tableau;
    private JButton ajouter;
    private JButton actualiser;

    public GestionPersonne(Crous c){
        this.monCrous = c;
        this.setTitle("Gestion des personnes");
        this.setSize(600, 400);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.setResizable(false);

        ImageIcon img = new ImageIcon("src/main/resources/images/logo.png");
        this.setIconImage(img.getImage());
        this.setLayout(new BorderLayout());

        boutons = new JPanel();

        tableau = new JTable();
        DefaultTableModel dtm = new DefaultTableModel(0,0);
        String header[] = {"ID","Prénom","Nom","Adresse"};
        dtm.setColumnIdentifiers(header);
        tableau.setModel(dtm);

        for(Personne p : monCrous.getPersonnes()){
            dtm.addRow(new Object[]{p.getId(),p.getPrenom(),p.getNom(),p.getAdresse()});
        }

        ajouter = new JButton("Ajouter une personne");
        ajouter.addActionListener(this);
        boutons.add(ajouter);

        actualiser = new JButton("Actualiser");
        actualiser.addActionListener(this);
        boutons.add(actualiser);

        this.add(tableau,BorderLayout.CENTER);
        this.add(boutons,BorderLayout.SOUTH);

        this.setVisible(true);
    }

    public void afficher(){
        this.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if( source == ajouter ) {

        }
        else if( source == actualiser ){

        }
    }
}
