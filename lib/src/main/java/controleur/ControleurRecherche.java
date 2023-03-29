package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

import IHM.MainPanel;
import IHM.VuePrincipale;
import Object.Ecurie;
import Object.Equipe;

public class ControleurRecherche implements ActionListener{
	
	private VuePrincipale vue;
	private ModeleESporter modele;
	public static List<Ecurie> rechercheEcurie = new LinkedList<>();
	public static List<Equipe> rechercheEquipe = new LinkedList<>();

	
	public ControleurRecherche(VuePrincipale vue) {
		this.vue = vue;
		this.modele = new ModeleESporter();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton j = (JButton) e.getSource();
		ModeleESporter.resultatRechercheEquipes.clear();
		ModeleESporter.resultatRechercheEcuries.clear();
		rechercheEcurie.clear();
		rechercheEquipe.clear();
		String prompt = this.vue.getTextSearch();
		//System.out.println(prompt);
		this.modele.setLastRecherche(this.modele.getRechercheEcurie(prompt), this.modele.getRecherche(prompt));
		rechercheEcurie.addAll(ModeleESporter.resultatRechercheEcuries);
		rechercheEquipe.addAll(ModeleESporter.resultatRechercheEquipes);
		MainPanel.changeModelElementEquipe(rechercheEquipe);
		MainPanel.changeModelElementEcurie(rechercheEcurie);
	}

}
