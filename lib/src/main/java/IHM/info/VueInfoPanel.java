package IHM.info;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;

import Object.Ecurie;
import Object.Equipe;
import Object.Joueur;
import Object.Tournoi;
import controleur.ControleurAdd;
import controleur.ControleurDelete;
import controleur.ControleurJList;
import controleur.ControleurModif;
import controleur.ModeleESporter;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.JLabel;

import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.ImageIcon;

public class VueInfoPanel extends JPanel{

	private static JList<Equipe> list = new JList<>();
	private static DefaultListModel<Equipe> modeleEquipe = new DefaultListModel<>();
	private static JList<Joueur> listJ = new JList<>();
	private static DefaultListModel<Joueur> modeleJoueur = new DefaultListModel<>();
	private static Equipe e;
	private static Ecurie ec;
	private static Tournoi t;
	private static Joueur j;
	private static JLabel nameLbl;
	private int classementCurrentEquipe;
	private static JLabel nomEcurie;
	private static JLabel nomTournoi;
	private static JLabel nomJoueur;
	private static JLabel prenomJoueur;
	private static JLabel pseudoJoueur;
	private static JLabel nationaliteJoueur;
	


	public VueInfoPanel(Equipe e) {
		modeleJoueur.clear();
		VueInfoPanel.e = null;
		VueInfoPanel.e = e;
		ControleurAdd c = new ControleurAdd(this, e);
		ControleurModif cm = new ControleurModif(e, this);
		ControleurDelete cd = new ControleurDelete(e, this);
		this.setSize(750, 450);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{258, 200, 258, 0};
		gridBagLayout.rowHeights = new int[]{381, 70, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 1.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		String nom = "Nom Equipe : " + e.getNom();
		
		JPanel panelInfo0 = new JPanel();
		panelInfo0.setLayout(null);
		GridBagConstraints gbc_panelInfo0 = new GridBagConstraints();
		gbc_panelInfo0.fill = GridBagConstraints.BOTH;
		gbc_panelInfo0.insets = new Insets(0, 0, 5, 5);
		gbc_panelInfo0.gridx = 0;
		gbc_panelInfo0.gridy = 0;
		add(panelInfo0, gbc_panelInfo0);

		JLabel lblNewLabel_1 = new JLabel("Nom Ecurie : ");
		lblNewLabel_1.setFont(ModeleESporter.FONT_MEDIUM);
		lblNewLabel_1.setBounds(10, 0, 159, 13);
		panelInfo0.add(lblNewLabel_1);
		
		JLabel ecurieLabel = new JLabel(e.getEcurie().getNom());
		ecurieLabel.setBounds(10, 23, 159, 22);
		panelInfo0.add(ecurieLabel);
		ecurieLabel.setVerticalAlignment(SwingConstants.TOP);
		ecurieLabel.setFont(ModeleESporter.FONT_MEDIUM);
		
		JLabel lblNewLabel_8 = new JLabel("");
		lblNewLabel_8.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_8.setIcon(e.getLogo());
		lblNewLabel_8.setBounds(10, 73, 258, 258);
		panelInfo0.add(lblNewLabel_8);
		
		
		JPanel panelInfo1 = new JPanel();
		panelInfo1.setLayout(null);
		GridBagConstraints gbc_panelInfo1 = new GridBagConstraints();
		gbc_panelInfo1.fill = GridBagConstraints.BOTH;
		gbc_panelInfo1.insets = new Insets(0, 0, 5, 5);
		gbc_panelInfo1.gridx = 1;
		gbc_panelInfo1.gridy = 0;
		add(panelInfo1, gbc_panelInfo1);
		
		nameLbl = new JLabel(e.getNom());
		nameLbl.setBounds(10, 20, 158, 17);
		panelInfo1.add(nameLbl);
		nameLbl.setVerticalAlignment(SwingConstants.TOP);
		nameLbl.setFont(ModeleESporter.FONT_MEDIUM);
		
		JLabel lblNewLabel_2 = new JLabel("Nom Equipe : ");
		lblNewLabel_2.setFont(ModeleESporter.FONT_MEDIUM);
		lblNewLabel_2.setBounds(10, 0, 158, 13);
		panelInfo1.add(lblNewLabel_2);
		
		JLabel lblNewLabel_4 = new JLabel("Classement : ");
		lblNewLabel_4.setFont(ModeleESporter.FONT_MEDIUM);
		lblNewLabel_4.setBounds(10, 47, 160, 13);
		panelInfo1.add(lblNewLabel_4);
		
		
		List<Equipe> classement = Equipe.getClassementByGame(e.getIdModeDeJeu());
		for(int i = 0; i<classement.size(); i++) {
			if(classement.get(i).getId() == e.getId()) {
				classementCurrentEquipe = i;
			}
		}
		JLabel lblNewLabel_5 = new JLabel(""+classementCurrentEquipe + "/" + classement.size());
		lblNewLabel_5.setFont(ModeleESporter.FONT_MEDIUM);
		lblNewLabel_5.setBounds(10, 70, 160, 13);
		panelInfo1.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Points :");
		lblNewLabel_6.setFont(ModeleESporter.FONT_MEDIUM);
		lblNewLabel_6.setBounds(10, 93, 160, 13);
		panelInfo1.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel(e.getPoints()+"");
		lblNewLabel_7.setFont(ModeleESporter.FONT_MEDIUM);
		lblNewLabel_7.setBounds(10, 113, 158, 13);
		panelInfo1.add(lblNewLabel_7);
		
		JPanel panelJoueur = new JPanel();
		panelJoueur.setLayout(null);
		GridBagConstraints gbc_panelJoueur = new GridBagConstraints();
		gbc_panelJoueur.insets = new Insets(0, 0, 5, 0);
		gbc_panelJoueur.fill = GridBagConstraints.BOTH;
		gbc_panelJoueur.gridx = 2;
		gbc_panelJoueur.gridy = 0;
		add(panelJoueur, gbc_panelJoueur);
		
		ControleurJList controleur = new ControleurJList();
		modeleJoueur.addAll(e.getJoueurs());
		//Solution pour ne pas dupliquer les listeners
		listJ = new JList<>();
		//
		listJ.setFont(ModeleESporter.FONT_MEDIUM);
		listJ.setModel(modeleJoueur);
		JScrollPane scrollPane = new JScrollPane(listJ);

		listJ.addMouseListener(controleur);
		
		scrollPane.setBounds(0, 36, 272, 264);
		panelJoueur.add(scrollPane);
		
		JLabel lblNewLabel_3 = new JLabel("Liste des joueurs : ");
		lblNewLabel_3.setFont(ModeleESporter.FONT_MEDIUM);
		lblNewLabel_3.setBounds(0, 0, 272, 35);
		panelJoueur.add(lblNewLabel_3);
		
		JButton addPlayer = new JButton("Ajouter un joueur");
		addPlayer.setFont(ModeleESporter.FONT_MEDIUM);
		addPlayer.setBounds(35, 311, 200, 25);
		addPlayer.addActionListener(c);
		panelJoueur.add(addPlayer);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 0, 5);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 1;
		add(panel, gbc_panel);
		
		JButton modfier_1 = new JButton("Modifier");
		modfier_1.setBounds(52, 10, 175, 50);
		modfier_1.setFont(ModeleESporter.FONT_MEDIUM);
		modfier_1.addActionListener(cm);
		panel.add(modfier_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.fill = GridBagConstraints.BOTH;
		gbc_panel_2.insets = new Insets(0, 0, 0, 5);
		gbc_panel_2.gridx = 1;
		gbc_panel_2.gridy = 1;
		add(panel_2, gbc_panel_2);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 2;
		gbc_panel_1.gridy = 1;
		add(panel_1, gbc_panel_1);
		
		JButton delete = new JButton("Supprimer");
		delete.setBounds(52, 10, 175, 50);
		delete.addActionListener(cd);
		panel_1.add(delete);
		delete.setFont(ModeleESporter.FONT_MEDIUM);
		
		
	}
	

	/**
	 * @wbp.parser.constructor
	 */	
	public VueInfoPanel(Ecurie e) {
		modeleEquipe.clear();
		VueInfoPanel.ec = null;
		VueInfoPanel.ec = e;
		this.setSize(500, 400);
		ControleurAdd c = new ControleurAdd(this, e);
		ControleurModif cm = new ControleurModif(e, this);
		ControleurDelete cd = new ControleurDelete(e, this);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{150, 258, 0};
		gridBagLayout.rowHeights = new int[]{0, 70, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.insets = new Insets(0, 0, 5, 5);
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 0;
		add(panel_1, gbc_panel_1);
		
		JLabel lblNewLabel_1 = new JLabel("Nom Ecurie : ");
		lblNewLabel_1.setFont(ModeleESporter.FONT_MEDIUM);
		lblNewLabel_1.setBounds(0, 10, 145, 17);
		panel_1.add(lblNewLabel_1);
		
		nomEcurie = new JLabel(e.getNom());
		nomEcurie.setFont(ModeleESporter.FONT_MEDIUM);
		nomEcurie.setBounds(0, 35, 145, 13);
		panel_1.add(nomEcurie);
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.insets = new Insets(0, 0, 5, 0);
		gbc_panel_2.fill = GridBagConstraints.BOTH;
		gbc_panel_2.gridx = 1;
		gbc_panel_2.gridy = 0;
		add(panel_2, gbc_panel_2);
		
		ControleurJList controleur = new ControleurJList();
		modeleEquipe.addAll(e.getEquipes());
		//
		list = new JList<>();
		//
		list.setModel(modeleEquipe);
		list.addMouseListener(controleur);
		JScrollPane scrollPane = new JScrollPane(list);
		scrollPane.addMouseListener(controleur);
		scrollPane.setSize(200, 300);
		scrollPane.setBounds(0, 50, 272, 200);
		panel_2.add(scrollPane);
		
		JLabel lblNewLabel = new JLabel("Listes des équipes :");
		lblNewLabel.setFont(ModeleESporter.FONT_MEDIUM);
		lblNewLabel.setBounds(0, 0, 200, 51);
		panel_2.add(lblNewLabel);
		
		JButton addTeam = new JButton("Ajouter une équipe");
		addTeam.setFont(ModeleESporter.FONT_MEDIUM);
		addTeam.setBounds(35, 261, 200, 25);
		addTeam.addActionListener(c);
		panel_2.add(addTeam);
		
		JPanel panel_3 = new JPanel();
		panel_3.setLayout(null);
		GridBagConstraints gbc_panel_3 = new GridBagConstraints();
		gbc_panel_3.insets = new Insets(0, 0, 0, 5);
		gbc_panel_3.fill = GridBagConstraints.BOTH;
		gbc_panel_3.gridx = 0;
		gbc_panel_3.gridy = 1;
		add(panel_3, gbc_panel_3);
		
		JButton delete = new JButton("Supprimer");
		delete.setBounds(20, 10, 150, 50);
		delete.setFont(ModeleESporter.FONT_MEDIUM);
		delete.addActionListener(cd);
		panel_3.add(delete);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 1;
		gbc_panel.gridy = 1;
		add(panel, gbc_panel);
		
		JButton modfier = new JButton("Modifier");
		modfier.setFont(ModeleESporter.FONT_MEDIUM);
		modfier.setBounds(62, 10, 200, 50);
		modfier.addActionListener(cm);
		panel.add(modfier);
	}
	
	public VueInfoPanel(Tournoi t) {
		modeleEquipe.clear();
		VueInfoPanel.t = null;
		VueInfoPanel.t = t;
		this.setSize(750, 400);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{150, 258, 0};
		gridBagLayout.rowHeights = new int[]{0, 70, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.insets = new Insets(0, 0, 5, 5);
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 0;
		add(panel_1, gbc_panel_1);
		
		JLabel lblNom = new JLabel("Nom Tournoi : ");
		lblNom.setFont(ModeleESporter.FONT_MEDIUM);
		lblNom.setBounds(0, 10, 145, 17);
		panel_1.add(lblNom);
		
		nomTournoi = new JLabel(t.getNom());
		nomTournoi.setFont(ModeleESporter.FONT_MEDIUM);
		nomTournoi.setBounds(0, 35, 145, 13);
		panel_1.add(nomTournoi);
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.insets = new Insets(0, 0, 5, 0);
		gbc_panel_2.fill = GridBagConstraints.BOTH;
		gbc_panel_2.gridx = 1;
		gbc_panel_2.gridy = 0;
		add(panel_2, gbc_panel_2);
		
		
		
		
	}
	
	public VueInfoPanel(Joueur j) {
		VueInfoPanel.j = null;
		VueInfoPanel.j = j;
		this.setSize(750, 400);
		ControleurModif cm = new ControleurModif(j, this);
		ControleurDelete cd = new ControleurDelete(j, this);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{150, 258, 0};
		gridBagLayout.rowHeights = new int[]{0, 70, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.insets = new Insets(0, 0, 5, 5);
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 0;
		add(panel_1, gbc_panel_1);
		
		JLabel lblNom = new JLabel("Nom Joueur : ");
		lblNom.setFont(ModeleESporter.FONT_MEDIUM);
		lblNom.setBounds(0, 10, 145, 17);
		panel_1.add(lblNom);
		
		nomJoueur = new JLabel(j.getNom());
		nomJoueur.setFont(ModeleESporter.FONT_MEDIUM);
		nomJoueur.setBounds(0, 35, 145, 13);
		panel_1.add(nomJoueur);
	
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.insets = new Insets(0, 0, 5, 0);
		gbc_panel_2.fill = GridBagConstraints.BOTH;
		gbc_panel_2.gridx = 1;
		gbc_panel_2.gridy = 0;
		add(panel_2, gbc_panel_2);
		
		JLabel prenomJoueurLabel = new JLabel("Prénom Joueur : ");
		prenomJoueurLabel.setFont(ModeleESporter.FONT_MEDIUM);
		prenomJoueurLabel.setBounds(0, 10, 145, 20);
		panel_2.add(prenomJoueurLabel);
		
		prenomJoueur = new JLabel(j.getPrenom());
		prenomJoueur.setBounds(0, 35, 145, 20);
		panel_2.add(prenomJoueur);
		prenomJoueur.setVerticalAlignment(SwingConstants.TOP);
		prenomJoueur.setFont(ModeleESporter.FONT_MEDIUM);
		
		
		JLabel pseudoJoueurLabel= new JLabel("Pseudonyme Joueur : ");
		pseudoJoueurLabel.setFont(ModeleESporter.FONT_MEDIUM);
		pseudoJoueurLabel.setBounds(0, 60, 145, 20);
		panel_1.add(pseudoJoueurLabel);
		
		pseudoJoueur = new JLabel(j.getPseudo());
		pseudoJoueur.setBounds(0, 85, 145, 20);
		panel_1.add(pseudoJoueur);
		pseudoJoueur.setVerticalAlignment(SwingConstants.TOP);
		pseudoJoueur.setFont(ModeleESporter.FONT_MEDIUM);
		
		JLabel dateJoueurLabel= new JLabel("Date de naissance Joueur : ");
		dateJoueurLabel.setFont(ModeleESporter.FONT_MEDIUM);
		dateJoueurLabel.setBounds(0, 60, 200, 20);
		panel_2.add(dateJoueurLabel);
		
		JLabel dateJoueur = new JLabel(j.getDateNaissance());
		dateJoueur.setBounds(0, 85, 145, 20);
		panel_2.add(dateJoueur);
		dateJoueur.setVerticalAlignment(SwingConstants.TOP);
		dateJoueur.setFont(ModeleESporter.FONT_MEDIUM);
		
		JLabel nationaliteJoueurLabel= new JLabel("Pays de Naissance Joueur : ");
		nationaliteJoueurLabel.setFont(ModeleESporter.FONT_MEDIUM);
		nationaliteJoueurLabel.setBounds(0, 110, 200, 20);
		panel_1.add(nationaliteJoueurLabel);
		
		nationaliteJoueur = new JLabel(j.getNationalite().getNom());
		nationaliteJoueur.setBounds(0, 135, 145, 20);
		panel_1.add(nationaliteJoueur);
		nationaliteJoueur.setVerticalAlignment(SwingConstants.TOP);
		nationaliteJoueur.setFont(ModeleESporter.FONT_MEDIUM);
		
		JLabel equipeJoueurLabel= new JLabel("Equipe Joueur : ");
		equipeJoueurLabel.setFont(ModeleESporter.FONT_MEDIUM);
		equipeJoueurLabel.setBounds(0, 110, 200, 20);
		panel_2.add(equipeJoueurLabel);
		
		JLabel equipeJoueur = new JLabel(Equipe.getNomEquipeFromId(j.getIdEquipe()));
		equipeJoueur.setBounds(0, 135, 200, 20);
		panel_2.add(equipeJoueur);
		equipeJoueur.setVerticalAlignment(SwingConstants.TOP);
		equipeJoueur.setFont(ModeleESporter.FONT_MEDIUM);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.gridwidth = 2;
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 1;
		add(panel, gbc_panel);
		panel.setLayout(null);
		
		JButton modfier_1 = new JButton("Modifier");
		modfier_1.setBounds(10, 10, 200, 50);
		modfier_1.setFont(ModeleESporter.FONT_MEDIUM);
		modfier_1.addActionListener(cm);
		panel.add(modfier_1);
		
		JButton supprimer = new JButton("Supprimer");
		panel.add(supprimer);
		supprimer.setBounds(290, 10, 200, 50);
		supprimer.addActionListener(cd);
		supprimer.setFont(ModeleESporter.FONT_MEDIUM);
		
		///
		
		///
	}

	public static void updateListJoueur() {
		modeleJoueur.clear();
		e = Equipe.getEquipeFromId(e.getId());
		modeleJoueur.addAll(e.getJoueurs());
		listJ.setModel(modeleJoueur);
	}
	
	public static void updateListEquipe() {
		modeleEquipe.clear();
		ec = Ecurie.getEcurieFromId(ec.getId());
		modeleEquipe.addAll(ec.getEquipes());
		list.setModel(modeleEquipe);
	}
	
	public static void updateInfoEquipe(String nom) {
		nameLbl.setText(nom);
	}
	
	public static void updateInfoEcurie(String nom) {
		nomEcurie.setText(nom);
	}

	public static void updateInfoJoueur(Joueur j) {
		nomJoueur.setText(j.getNom());
		prenomJoueur.setText(j.getPrenom());
		pseudoJoueur.setText(j.getPseudo());
		nationaliteJoueur.setText(j.getNationalite().getNom());
		
	}
}
