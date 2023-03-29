package IHM;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.Dimension;
import javax.swing.ScrollPaneConstants;

import Object.Ecurie;
import Object.Equipe;
import Object.Tournoi;
import controleur.ControleurJList;
import controleur.ControleurFilter;
import controleur.ModeleESporter;

import java.awt.GridLayout;
import java.util.List;
import java.awt.Font;
import javax.swing.JComboBox;

public class MainPanel extends JPanel{


	private static JList<Equipe> listEquipe = new JList<>();
	private static DefaultListModel<Equipe> modelEquipe = new DefaultListModel<>();
	
	private static JList<Ecurie> listEcurie = new JList<>();
	private static DefaultListModel<Ecurie> modelEcurie = new DefaultListModel<>();
	
	private static JList<Tournoi> listTournoi = new JList<>();
	private static DefaultListModel<Tournoi> modelTournoi = new DefaultListModel<>();

	private static List<Equipe> e = null;
	private static List<Ecurie> ec = null;
	private static List<Tournoi> t = null;
	private JComboBox<String> filterTournament;
	private JComboBox<String> filterOrga;
	private JComboBox<String> filterTeam;
	
	public MainPanel() throws Exception {
		ControleurJList controleurEquipe = new ControleurJList();
		ControleurJList controleurEcurie = new ControleurJList();
		ControleurJList controleurTournoi = new ControleurJList();
		
		setLayout(new GridLayout(0, 3, 0, 0));
		listEquipe.setBorder(null);
		listEquipe.setFont(ModeleESporter.FONT_LARGE);
		modelEquipe.addAll(ModeleESporter.getAllEquipe());
		listEquipe.setModel(modelEquipe);
		listEquipe.addMouseListener(controleurEquipe);
		JScrollPane teamPanel = new JScrollPane(listEquipe);
		teamPanel.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		teamPanel.setSize(400, 625);
		teamPanel.setMaximumSize(new Dimension(400, 625));
		teamPanel.setMinimumSize(new Dimension(400, 625));
		add(teamPanel);
		
		listEcurie.setBorder(null);
		listEcurie.setFont(ModeleESporter.FONT_LARGE);
		modelEcurie.addAll(ModeleESporter.getAllEcurie());
		listEcurie.setModel(modelEcurie);
		listEcurie.addMouseListener(controleurEcurie);
		JScrollPane ecuriePanel = new JScrollPane(listEcurie);
		ecuriePanel.setSize(400, 625);
		ecuriePanel.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		ecuriePanel.setMaximumSize(new Dimension(400, 625));
		ecuriePanel.setMinimumSize(new Dimension(400, 625));
		add(ecuriePanel);
		
		listTournoi.setBorder(null);
		listTournoi.setFont(ModeleESporter.FONT_LARGE);
		modelTournoi.addAll(ModeleESporter.getAllTournoi());
		listTournoi.setModel(modelTournoi);
		listTournoi.addMouseListener(controleurTournoi);
		JScrollPane tournoiPanel = new JScrollPane(listTournoi);
		tournoiPanel.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		tournoiPanel.setSize(400, 625);
		tournoiPanel.setMaximumSize(new Dimension(400, 625));
		tournoiPanel.setMinimumSize(new Dimension(400, 625));
		add(tournoiPanel);
		
		JPanel filterTeamPanel = new JPanel();
		teamPanel.setColumnHeaderView(filterTeamPanel);
		
		ControleurFilter<String> controleurTeam = new ControleurFilter<>("Equipe",this);
		filterTeam = new JComboBox<>();
		filterTeam.addItem("Sans filtre");
		for(String s : ModeleESporter.getAllModeName()){
			filterTeam.addItem(s);
		}
		filterTeam.setFont(ModeleESporter.FONT_MEDIUM);
		filterTeam.addActionListener(controleurTeam);
		filterTeamPanel.add(filterTeam);
		
		
		JPanel filterOrgaPanel = new JPanel();
		ecuriePanel.setColumnHeaderView(filterOrgaPanel);
		ControleurFilter<String> controleurOrga = new ControleurFilter<>("Ecurie", this);
		filterOrga = new JComboBox<>();
		filterOrga.addItem("Sans filtre");
		String[] listNat = ModeleESporter.getAllNat();
		for(String s : listNat) {
			filterOrga.addItem(s);
		}
		filterOrga.setFont(ModeleESporter.FONT_MEDIUM);
		filterOrga.addActionListener(controleurOrga);
		filterOrgaPanel.add(filterOrga);
		
		JPanel filterTournamentPanel = new JPanel();
		tournoiPanel.setColumnHeaderView(filterTournamentPanel);
		
		ControleurFilter<String> filterTournoi = new ControleurFilter<>("Tournament", this);
		filterTournament = new JComboBox<>();
		filterTournament.addItem("Sans filtre");
		filterTournament.addItem("Phase d'inscription");
		filterTournament.addItem("En cours");
		filterTournament.addItem("Terminés");
		filterTournament.setFont(ModeleESporter.FONT_MEDIUM);
		filterTournament.addActionListener(filterTournoi);
		filterTournamentPanel.add(filterTournament);

	}
	
	public static void changeModelElementEquipe(List<Equipe> l) {
		modelEquipe.clear();
		modelEquipe.addAll(l);
	}
	
	public static void changeModelElementEcurie(List<Ecurie> l) {
		modelEcurie.clear();
		modelEcurie.addAll(l);
	}
	
	public static void changeModelElementTournoi(List<Tournoi> l) {
		modelTournoi.clear();
		modelTournoi.addAll(l);
	}
	
	public static void updateListEquipe() {
		modelEquipe.clear();
		e = Equipe.getAllEquipes();
		modelEquipe.addAll(e);
		listEquipe.setModel(modelEquipe);
	}
	
	public static void updateListTournoi() {
		modelTournoi.clear();
		t = Tournoi.getAllTournois();
		modelTournoi.addAll(t);
		listTournoi.setModel(modelTournoi);
	}
	
	public static void updateListEcuries() {
		modelEcurie.clear();
		ec = Ecurie.getAllEcuries();
		modelEcurie.addAll(ec);
		listEcurie.setModel(modelEcurie);
	}
}
