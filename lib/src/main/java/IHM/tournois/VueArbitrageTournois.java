package IHM.tournois;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import IHM.info.ListeJoueur;
import Object.Joueur;
import Object.Match;
import controleur.ControleurArbitrageTournoi;
import controleur.ControleurJList;

public class VueArbitrageTournois extends JPanel{

	private Match match;
	private JButton buttonWin1;
	private JButton buttonWin2;

	public Match getMatch() {
		return match;
	}

	public JButton getButtonWin1() {
		return buttonWin1;
	}

	public JButton getButtonWin2() {
		return buttonWin2;
	}

	public VueArbitrageTournois(Match m) {
		this.match = m;
		ControleurArbitrageTournoi controleurM = new ControleurArbitrageTournoi(this);
		// création des listes et de leurs modèles de données
	    GridBagLayout gridBagLayout = new GridBagLayout();
	    gridBagLayout.columnWidths = new int[]{193, 193, 0};
	    gridBagLayout.rowHeights = new int[]{185, 25, 0};
	    gridBagLayout.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
	    gridBagLayout.rowWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
	    this.setLayout(gridBagLayout);
	    
	    // création des titres
	    JLabel titreListe1 = new JLabel(match.getEquipe1().getNom());
	    titreListe1.setBounds(80, 30, 80, 13);
	    titreListe1.setHorizontalAlignment(SwingConstants.CENTER);

	    
	    // création des panneaux pour contenir les titres et les listes
	    JPanel panneauListe1 = new JPanel();
	    panneauListe1.setLayout(null);
	    panneauListe1.add(titreListe1);
		List<Joueur> joueursE1 = match.getEquipe1().getJoueurs();
		ListeJoueur<Joueur> modelL1 = new ListeJoueur<>(joueursE1);
		JList<Joueur> listeJE1 = new JList<Joueur>(modelL1);
	    JScrollPane scrollPane_1 = new JScrollPane(listeJE1);
		System.out.println("-----------\n"+ joueursE1);
		System.out.println("-----------\n"+ listeJE1.getSize());
	    scrollPane_1.setBounds(40, 65, 160, 100);
	    panneauListe1.add(scrollPane_1);
	    GridBagConstraints gbc_panneauListe1 = new GridBagConstraints();
	    gbc_panneauListe1.fill = GridBagConstraints.BOTH;
	    gbc_panneauListe1.insets = new Insets(0, 0, 5, 5);
	    gbc_panneauListe1.gridx = 0;
	    gbc_panneauListe1.gridy = 0;
	    this.add(panneauListe1, gbc_panneauListe1);
	    JLabel titreListe2 = new JLabel(match.getEquipe2().getNom());
	    titreListe2.setBounds(80, 30, 80, 15);
	    titreListe2.setHorizontalAlignment(SwingConstants.CENTER);
	    
	    JPanel panneauListe2 = new JPanel();
	    panneauListe2.setLayout(null);
	    panneauListe2.add(titreListe2);
		List<Joueur> joueursE2 = match.getEquipe2().getJoueurs(); 
		ControleurJList controleur = new ControleurJList();
		ListeJoueur<Joueur> modelL2 = new ListeJoueur<>(joueursE2);
		JList<Joueur> listeJE2 = new JList<Joueur>(modelL2);
		listeJE2.addMouseListener(controleur);
		listeJE1.addMouseListener(controleur);
	    JScrollPane scrollPane = new JScrollPane(listeJE2);
	    scrollPane.setBounds(40, 65, 160, 100);
	    panneauListe2.add(scrollPane);
	    GridBagConstraints gbc_panneauListe2 = new GridBagConstraints();
	    gbc_panneauListe2.fill = GridBagConstraints.BOTH;
	    gbc_panneauListe2.insets = new Insets(0, 0, 5, 0);
	    gbc_panneauListe2.gridx = 1;
	    gbc_panneauListe2.gridy = 0;
	    this.add(panneauListe2, gbc_panneauListe2);
	    
	    JPanel panel = new JPanel();
	    panel.setLayout(null);
	    GridBagConstraints gbc_panel = new GridBagConstraints();
	    gbc_panel.fill = GridBagConstraints.BOTH;
	    gbc_panel.insets = new Insets(0, 0, 0, 5);
	    gbc_panel.gridx = 0;
	    gbc_panel.gridy = 1;
	    this.add(panel, gbc_panel);
	    
	    // création des boutons
		buttonWin1 = new JButton("Gagné");
	    buttonWin1.setBounds(80, 0, 80, 21);
	    panel.add(buttonWin1);
	    buttonWin1.setPreferredSize(new Dimension(20, 10));
		buttonWin1.addActionListener(controleurM);

		if (match.getWinner() == match.getEquipe1()) {
			buttonWin1.setEnabled(false);
		}
	    
	    JPanel panel_1 = new JPanel();
	    panel_1.setLayout(null);
	    GridBagConstraints gbc_panel_1 = new GridBagConstraints();
	    gbc_panel_1.fill = GridBagConstraints.BOTH;
	    gbc_panel_1.gridx = 1;
	    gbc_panel_1.gridy = 1;
	    this.add(panel_1, gbc_panel_1);
		buttonWin2 = new JButton("Gagné");
	    buttonWin2.setBounds(81, 0, 82, 21);
	    panel_1.add(buttonWin2);
	    buttonWin2.addActionListener(controleurM);

		if (match.getWinner() == match.getEquipe2()) {
			buttonWin2.setEnabled(false);
		}
	}
}
	
