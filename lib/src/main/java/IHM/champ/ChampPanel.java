package IHM.champ;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.sql.Date;
import java.text.ParseException;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;
import javax.swing.text.MaskFormatter;

import Object.Ecurie;
import Object.Equipe;
import Object.Joueur;
import Object.ModeDeJeu;
import Object.Nationalite;
import Object.Organisateur;
import Object.Tournoi;
import Object.Tournoi.ETAT;
import controleur.ControleurAdd;
import controleur.ControleurAjout;
import controleur.ControleurFilter;
import controleur.ControleurJList;
import controleur.ModeleESporter;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JButton;

public class ChampPanel extends JPanel {

	//Class Variable
	private final int WIDTH = 450;
	private final int HEIGHT = 500;
	private final int MAIN_FONT_SIZE = 17;
	private String mode;
	
	private JComboBox<String> filterMode;
	
	private static JList<Equipe> listEquipe = new JList<>();
	private static DefaultListModel<Equipe> modelEquipe = new DefaultListModel<>();
	
	
	public ChampPanel() {
		ControleurJList controleurEquipe = new ControleurJList();
		ChampPanel.changeModelResultChamp(Equipe.getClassementByGame(0));
		listEquipe.addMouseListener(controleurEquipe);
		listEquipe.setModel(modelEquipe);
		JScrollPane teamPanel = new JScrollPane(listEquipe);
		teamPanel.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		teamPanel.setSize(400, 625);
		teamPanel.setMaximumSize(new Dimension(400, 625));
		teamPanel.setMinimumSize(new Dimension(400, 625));
		add(teamPanel);
		
		
		
		JPanel filterTeamPanel = new JPanel();
		teamPanel.setColumnHeaderView(filterTeamPanel);
		
		ControleurFilter<String> controleurMode = new ControleurFilter<>("Championnat",this);
		filterMode = new JComboBox<>();
		for(String s : ModeleESporter.getAllModeName()){
			filterMode.addItem(s);
		}
		filterMode.setFont(ModeleESporter.FONT_MEDIUM);
		filterMode.addActionListener(controleurMode);
		filterTeamPanel.add(filterMode);
	}
	
	public static void changeModelResultChamp(List<Equipe> l) {
		modelEquipe.clear();
		modelEquipe.addAll(l);
	}

}
