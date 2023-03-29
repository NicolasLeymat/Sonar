package controleur;

import java.awt.Font;
import java.awt.Window;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import Application.Connexion;
import Object.Ecurie;
import Object.Equipe;
import Object.Jeu;
import Object.Joueur;
import Object.ModeDeJeu;
import Object.Nationalite;
import Object.Tournoi;

public class ModeleESporter {

	//Taille de la police de 10
	public static final int FONT_SIZE_SMALL = 10;
	//Taille de la police de 15
	public static final int FONT_SIZE_MEDIUM = 15;
	//Taille de la police de 17
	private static final int FONT_SIZE_MEDIUM_LARGE = 17;
	//Taille de la police de 20
	public static final int FONT_SIZE_LARGE = 20;
	
	//Font Large
	public static final Font FONT_LARGE = new Font("Berlin Sans FB", Font.PLAIN, FONT_SIZE_LARGE);
	//Font Medium
	public static final Font FONT_MEDIUM = new Font("Berlin Sans FB", Font.PLAIN, FONT_SIZE_MEDIUM);
	//Font Medium-Large
	public static final Font FONT_MEDIUM_LARGE = new Font("Berlin Sans FB", Font.PLAIN, FONT_SIZE_MEDIUM_LARGE);
	//Font Small
	public static final Font FONT_SMALL = new Font("Berlin Sans FB", Font.PLAIN, FONT_SIZE_SMALL);
	
	//Parametre utile à la classe
	public static List<Equipe> resultatRechercheEquipes;
	public static List<Ecurie> resultatRechercheEcuries;
	public static List<Equipe> allEquipe;
	public static List<Ecurie> allEcurie;
	public static List<Tournoi> allTournoi;
	private static Connection connx;
	
	public ModeleESporter() {
		resultatRechercheEcuries = new LinkedList<>();
		resultatRechercheEquipes = new LinkedList<>();
		connx = Connexion.connexion();
	}
	
	public JFrame getPanelFrame(JPanel vue) {
		JFrame f2 = (JFrame) SwingUtilities.getWindowAncestor(vue);
		return f2;
	}
	
	public static JFrame getPanelFrameStatic(JPanel vue) {
		JFrame f2 = (JFrame) SwingUtilities.getWindowAncestor(vue);
		return f2;
	}
	
	public void addPlayer(Joueur j) {
		Joueur.enregistrerJoueur(j);
	}
	
	public void addTeam(Equipe e) {
		try {
			Equipe.enregistrerEquipe(e);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public void addOrga(Ecurie e) {
		Ecurie.enregistrerEcurie(e);
	}
	
	public void addTournament(Tournoi t) {
		try {
			Tournoi.enregistrerTournoi(t);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static String[] getAllNat(){
		String[] n = Nationalite.getAllNationalites();
		return n;
	}
	
	public static String[] getAllModeName(){
		List<ModeDeJeu> l = ModeDeJeu.getAllModeDeJeu();
		int size = ModeDeJeu.getAllModeDeJeu().size();
		String[] res = new String[size];
		for(int i = 0; i< size; i++) {
			res[i] = l.get(i).getNom();
		}
		return res;
	}
	
	public static void getAll() {
		allEcurie = Ecurie.getAllEcuries();
		allEquipe = Equipe.getAllEquipes();
		allTournoi = Tournoi.getAllTournois();
		try {
			connx.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<Object> recherche(String prompt){
		for(Equipe e : allEquipe) {
			
		}
		return null;
	}
	
	public List<Equipe> getRecherche(String prompt){
		return Equipe.getEquipeFromNomAll(prompt);
	}
	
	public List<Ecurie> getRechercheEcurie(String prompt){
		return Ecurie.getEcurieFromNomAll(prompt);
	}
	
	public static List<Equipe> getAllEquipe(){
		return Equipe.getAllEquipes();
	}
	
	public static List<Ecurie> getAllEcurie(){
		return Ecurie.getAllEcuries();
	}
	
	public static List<Tournoi> getAllTournoi(){
		return Tournoi.getAllTournois();
	}

	public void setLastRecherche(List<Ecurie> lec, List<Equipe> leq) {
		resultatRechercheEcuries = lec;
		resultatRechercheEquipes=  leq;	
	}
	
	public void modifierEquipe(Equipe eq) {
		Equipe.modifierEquipe(eq);
	}
	
	public boolean estNomEquipeDispo(String nom) {
		List<Equipe> l = allEquipe;
		List<String> l2 = new LinkedList<>();
		int nbOccurence = 0;
		for(Equipe e : l) {
			l2.add(e.getNom());
		}
		
		for(String s : l2) {
			if(s.equals(nom)) {
				nbOccurence += 1;
			}
		}
		if(nbOccurence == 0) {
			return true;
		}else {			
			return false;
		}
	}

	public boolean estNomeEcurieDispo(String nom) {
		List<Ecurie> l = allEcurie;
		List<String> l2 = new LinkedList<>();
		int nbOccurence = 0;
		for(Ecurie e : l) {
			l2.add(e.getNom());
		}
		
		for(String s : l2) {
			if(s.equals(nom)) {
				nbOccurence += 1;
			}
		}
		if(nbOccurence == 0) {
			return true;
		}else {			
			return false;
		}
	}
	
	public void modifierEcurie(Ecurie ec) {
		Ecurie.modifierEcurie(ec);
	}

	public void modifierJoueur(Joueur jNew) {
		Joueur.modifierJoueur(jNew);
	}
	
	public void supprimerEquipe(Equipe e) {
		Equipe.supprimerEquipe(e);
	}
	
	public void supprimerEcurie(Ecurie e) {
		Ecurie.supprimerEcurie(e);
	}
	
	public void supprimerJoueur(Joueur j) { 
		Joueur.supprimerJoueur(j);
	}
	
	public void supprimerTournoi(Tournoi t) {
		Tournoi.supprimerTournoi(t);
	}

	public void addParticipation(Equipe obj, Tournoi t) {
		Equipe.inscrireEquipeTournoi(obj, t);
	}
	
	public void deleteParticipation(Equipe obj, Tournoi t) {
		Equipe.supprimerEquipeTournoi(obj, t);
	}
}
