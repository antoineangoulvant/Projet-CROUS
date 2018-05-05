package com.antoine.gestioncrous.view;

import com.antoine.gestioncrous.Crous;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Fenetreprincipal extends JFrame implements ActionListener {
    private Crous monCrous;
    private JPanel panelPrincipal;
    private JButton bGestionPersonne;
    private JButton bGestionBien;
    private JButton bGestionBail;
    private JButton bAPropos;
    private GestionPersonne gestionPersonne = null;

    public Fenetreprincipal(Crous c){
        this.monCrous = c;

        this.setTitle("Gestion du Crous");
        this.setSize(400, 440);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);

        ImageIcon img = new ImageIcon("src/main/resources/images/logo.png");
        this.setIconImage(img.getImage());

        this.panelPrincipal = new JPanel();
        this.panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new FlowLayout());
        panelPrincipal.setBackground(Color.WHITE);

        JLabel logo = new JLabel( new ImageIcon( "src/main/resources/images/logo.png"));
        panelPrincipal.add(logo);

        bGestionPersonne = new JButton("Gestion des personnes");
        bGestionPersonne.addActionListener(this);
        panelPrincipal.add(bGestionPersonne);

        bGestionBail = new JButton("Gestion des bails");
        bGestionBail.addActionListener(this);
        panelPrincipal.add(bGestionBail);

        bGestionBien = new JButton("Gestion des biens");
        bGestionBien.addActionListener(this);
        panelPrincipal.add(bGestionBien);

        bAPropos = new JButton("A Propos");
        bAPropos.addActionListener(this);
        panelPrincipal.add(bAPropos);

        this.add(panelPrincipal);
        this.setVisible(true);
    }


    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if( source == bGestionBail ){

        }
        else if( source == bGestionBien ){

        }
        else if( source == bGestionPersonne ){
            if( gestionPersonne == null ){
                gestionPersonne = new GestionPersonne(monCrous);
            }
            else{
                gestionPersonne.afficher();
            }
        }
        else if( source == bAPropos ){
            JOptionPane aPropos = new JOptionPane();
            aPropos.showMessageDialog(null, "Projet réalisé par Antoine Angoulvant dans le cadre du cours de Projet de conception avancé (B353)", "A Propos", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
