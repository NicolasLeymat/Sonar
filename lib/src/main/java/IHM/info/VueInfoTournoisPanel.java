package IHM.info;

import javax.swing.*;

import IHM.tournois.FrameArbitrageTournois;
import Object.Tournoi;
import Object.Match;
import controleur.ControlleurListeMatch;
import controleur.ModeleESporter;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class VueInfoTournoisPanel extends JPanel {
    public VueInfoTournoisPanel(Tournoi tournoi, ControlleurListeMatch controleur) {

        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));

        ListeMatch listemodel1;
        // Création des modèles de tableau
        if (!(tournoi.getPhaseElim() == null)) {
             listemodel1 = new ListeMatch(tournoi.getPhaseElim().getMatchsAJouer());
        }
        else {
             listemodel1 = new ListeMatch(tournoi.getPhasePoule().getMatchs());
        }

        //Spacing
        this.add(Box.createRigidArea(new Dimension(0,5)));

        // Création des tableaux
        JList table1 = new JList(listemodel1);
        table1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int ligne = table1.getSelectedIndex();

                Match match  =(Match) listemodel1.getValueAt(ligne);

                System.out.println(tournoi);
                IHM.tournois.FrameArbitrageTournois window = new FrameArbitrageTournois(match);
                window.setVisible(true);

                window.addWindowListener(new WindowAdapter() {
                    public void windowClosing(WindowEvent e) {
                        Match.validerVainqueur(match);
                        table1.repaint();
                        controleur.setBoutonSuivant();
                    }
                });
            }
        });
        DefaultListCellRenderer centerRenderer = new DefaultListCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        //table1.setCellRenderer(centerRenderer);
        table1.setFont(new Font("monospaced",Font.PLAIN, ModeleESporter.FONT_SIZE_MEDIUM));

        // Création de la barre de défilement pour les tableaux
        JScrollPane scrollPane1 = new JScrollPane(table1);

        JLabel titre = new JLabel(tournoi.getNom());
        titre.setFont(ModeleESporter.FONT_LARGE);
        titre.setAlignmentX(0.5f);
        this.add(titre);
        this.add(Box.createRigidArea(new Dimension(0,10)));

        JLabel titreArbitrer = new JLabel("Matchs en cours");
        titreArbitrer.setFont(ModeleESporter.FONT_LARGE);
        titreArbitrer.setAlignmentX(0.5f);
        this.add(titreArbitrer);
        this.add(Box.createRigidArea(new Dimension(0,5)));
        this.add(scrollPane1);



    }


}