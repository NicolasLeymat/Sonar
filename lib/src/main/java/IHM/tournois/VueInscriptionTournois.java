package IHM.tournois;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import Object.Equipe;
import Object.Joueur;
import Object.Nationalite;
import Object.Tournoi;
import controleur.ControleurInscription;
import controleur.ModeleESporter;
import java.awt.GridLayout;
import javax.swing.ScrollPaneConstants;

public class VueInscriptionTournois extends JPanel{

	private Tournoi t;
	private Object obj;
	private static JList<Equipe> listEquipe = new JList<>();
	private static DefaultListModel<Equipe> modeleEquipe = new DefaultListModel<>();
	private static JList<Equipe> listEquipesTournoi = new JList<>();
	private static DefaultListModel<Equipe> modeleEquipeTournoi = new DefaultListModel<>();
	private static JButton btnAjouter;
	private static JButton btnSupprimer;
	
	
	public VueInscriptionTournois(Tournoi t) {
		
		this.t = t;
		
		modeleEquipe.clear();
		modeleEquipe.addAll(equipeDif(t, Equipe.getAllEquipesFromModeDeJeu(t.getId_Mode().getIdMode())));		
		modeleEquipeTournoi.clear();
		modeleEquipeTournoi.addAll(Equipe.getAllEquipesFromTournoi(t));
		listEquipe.setModel(modeleEquipe);
		listEquipesTournoi.setModel(modeleEquipeTournoi);
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{450, 0};
		gridBagLayout.rowHeights = new int[]{40, 200, 50, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 1.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JPanel panelTitre = new JPanel();
		GridBagConstraints gbc_panelTitre = new GridBagConstraints();
		gbc_panelTitre.insets = new Insets(0, 0, 5, 0);
		gbc_panelTitre.fill = GridBagConstraints.HORIZONTAL;
		gbc_panelTitre.gridx = 0;
		gbc_panelTitre.gridy = 0;
		add(panelTitre, gbc_panelTitre);
		GridBagLayout gbl_panelTitre = new GridBagLayout();
		gbl_panelTitre.columnWidths = new int[]{450, 0};
		gbl_panelTitre.rowHeights = new int[]{35, 0};
		gbl_panelTitre.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panelTitre.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panelTitre.setLayout(gbl_panelTitre);
		
		JLabel titreLbl = new JLabel(t.getNom());
		titreLbl.setFont(ModeleESporter.FONT_LARGE);
		titreLbl.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_titreLbl = new GridBagConstraints();
		gbc_titreLbl.fill = GridBagConstraints.BOTH;
		gbc_titreLbl.gridx = 0;
		gbc_titreLbl.gridy = 0;
		panelTitre.add(titreLbl, gbc_titreLbl);
		
		JPanel listPanel = new JPanel();
		GridBagConstraints gbc_listPanel = new GridBagConstraints();
		gbc_listPanel.insets = new Insets(0, 0, 5, 0);
		gbc_listPanel.fill = GridBagConstraints.BOTH;
		gbc_listPanel.gridx = 0;
		gbc_listPanel.gridy = 1;
		add(listPanel, gbc_listPanel);
		GridBagLayout gbl_listPanel = new GridBagLayout();
		gbl_listPanel.columnWidths = new int[]{225, 225, 0};
		gbl_listPanel.rowHeights = new int[]{102, 0};
		gbl_listPanel.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_listPanel.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		listPanel.setLayout(gbl_listPanel);
		
		JPanel panelEquipe = new JPanel();
		panelEquipe.setLayout(null);
		GridBagConstraints gbc_panelEquipe = new GridBagConstraints();
		gbc_panelEquipe.fill = GridBagConstraints.BOTH;
		gbc_panelEquipe.insets = new Insets(0, 0, 0, 5);
		gbc_panelEquipe.gridx = 0;
		gbc_panelEquipe.gridy = 0;
		listPanel.add(panelEquipe, gbc_panelEquipe);
		
		JLabel lblNewLabel = new JLabel("Equipes : ");
		lblNewLabel.setBounds(0, 0, 223, 30);
		panelEquipe.add(lblNewLabel);
		lblNewLabel.setFont(ModeleESporter.FONT_MEDIUM);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		JScrollPane scrollPaneEquipe = new JScrollPane(listEquipe);
		scrollPaneEquipe.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPaneEquipe.setBounds(22, 40, 180, 150);
		panelEquipe.add(scrollPaneEquipe);
		
		JPanel panelEquipeTournoi = new JPanel();
		panelEquipeTournoi.setLayout(null);
		
		JScrollPane scrollPaneEquipeTournoi = new JScrollPane(listEquipesTournoi);
		scrollPaneEquipeTournoi.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPaneEquipeTournoi.setBounds(22, 40, 180, 150);
		panelEquipeTournoi.add(scrollPaneEquipeTournoi);
		
		GridBagConstraints gbc_panelEquipeTournoi = new GridBagConstraints();
		gbc_panelEquipeTournoi.fill = GridBagConstraints.BOTH;
		gbc_panelEquipeTournoi.gridx = 1;
		gbc_panelEquipeTournoi.gridy = 0;
		listPanel.add(panelEquipeTournoi, gbc_panelEquipeTournoi);
		
		JLabel lblNewLabel_1 = new JLabel("Equipes Tournoi :\r\n");
		lblNewLabel_1.setBounds(0, 0, 225, 30);
		panelEquipeTournoi.add(lblNewLabel_1);
		lblNewLabel_1.setFont(ModeleESporter.FONT_MEDIUM);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		
		JPanel btnPanel = new JPanel();
		btnPanel.setLayout(null);
		GridBagConstraints gbc_btnPanel = new GridBagConstraints();
		gbc_btnPanel.fill = GridBagConstraints.BOTH;
		gbc_btnPanel.gridx = 0;
		gbc_btnPanel.gridy = 2;
		add(btnPanel, gbc_btnPanel);
		
		btnAjouter = new JButton("Ajouter");
		btnAjouter.setLocation(60, 0);
		btnAjouter.setSize(100, 50);
		btnSupprimer = new JButton("Supprimer");
		btnSupprimer.setLocation(290, 0);
		btnSupprimer.setSize(100, 50);
		
		ControleurInscription ci = new ControleurInscription(this);
		btnAjouter.addActionListener(ci);
		btnSupprimer.addActionListener(ci);
		btnPanel.add(btnAjouter);
		btnPanel.add(btnSupprimer);
		
		JButton confirmer = new JButton("Confirmer");
		confirmer.setBounds(175, 0, 100, 50);
		btnPanel.add(confirmer);
		confirmer.addActionListener(ci);
		
		if (listEquipesTournoi.getModel().getSize() >= 16) {
			btnAjouter.setEnabled(false);
		}
		if (listEquipesTournoi.getModel().getSize() <=0) {
			btnSupprimer.setEnabled(false);
		}
	}

	private static List<Equipe> equipeDif(Tournoi t,List<Equipe> allEquipesFromModeDeJeu) {
		List<Equipe> res = new ArrayList<>();
		List<Equipe> test = Equipe.getAllEquipesFromTournoi(t);
		boolean check = false;
		for (Equipe e : allEquipesFromModeDeJeu) {
			check = false;
			for (Equipe e2 : test) {
				if (e.getId() == e2.getId()) {
					check = true;
					break;
				}
			}
			if (check == false) {
				res.add(e);
			}
		}
		return res;
	}

	public Tournoi getTournoi() {
		return t;
	}

	public Equipe getInfoToObject() {
		if(listEquipe.getSelectedValue() == null) {
			return listEquipe.getModel().getElementAt(0);
		}
		return listEquipe.getSelectedValue();
	}
	public Equipe getListEquipesTournoi() {
		if(listEquipesTournoi.getSelectedValue() == null) {
			return listEquipesTournoi.getModel().getElementAt(0);
		}
		return listEquipesTournoi.getSelectedValue();
	}

	public List<Equipe> getAllListEquipesTournoi() {
		List<Equipe> e = new LinkedList<Equipe>();
		
		for(int i = 0; i < listEquipesTournoi.getModel().getSize(); i++) {
			e.add(listEquipesTournoi.getModel().getElementAt(i));
		}
		
		return e;
	}
	

	public static void updateListEquipe(Tournoi t) {
		modeleEquipe.clear();
		modeleEquipe.addAll(equipeDif(t, Equipe.getAllEquipesFromModeDeJeu(t.getId_Mode().getIdMode())));	
		listEquipe.setModel(modeleEquipe);
		
		modeleEquipeTournoi.clear();
		modeleEquipeTournoi.addAll(Equipe.getAllEquipesFromTournoi(t));
		listEquipesTournoi.setModel(modeleEquipeTournoi);
		
		if (listEquipesTournoi.getModel().getSize() >= 16) {
			btnAjouter.setEnabled(false);
		}
		else {
			btnAjouter.setEnabled(true);
		}
		
		if (listEquipesTournoi.getModel().getSize() <=0) {
			btnSupprimer.setEnabled(false);
		}
		else {
			btnSupprimer.setEnabled(true);
		} 
	}
}
