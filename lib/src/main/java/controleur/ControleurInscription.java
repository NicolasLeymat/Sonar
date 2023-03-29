package controleur;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import IHM.MainPanel;
import IHM.add.AddPanel;
import IHM.info.VueInfoPanel;
import IHM.tournois.VueInscriptionTournois;
import Object.Ecurie;
import Object.Equipe;
import Object.Joueur;
import Object.Phase;
import Object.PhaseDePoule;
import Object.Tournoi;
import Object.Tournoi.ETAT;

public class ControleurInscription implements ActionListener{

	private VueInscriptionTournois vue;
	private ModeleESporter modele;
	
	public ControleurInscription(VueInscriptionTournois vue) {
		this.vue = vue;
		this.modele = new ModeleESporter();
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton btn = (JButton) e.getSource();
		switch(btn.getText()) {
			case "Ajouter":{
				Equipe obj = (Equipe) this.vue.getInfoToObject();
				Tournoi t = this.vue.getTournoi();
				this.modele.addParticipation(obj,t);
				VueInscriptionTournois.updateListEquipe(t);
				break;
			}
			case "Supprimer":{
				Equipe obj = (Equipe) this.vue.getListEquipesTournoi();
				if(obj == null) {
					
				}
				Tournoi t = this.vue.getTournoi();
				this.modele.deleteParticipation(obj,t);
				VueInscriptionTournois.updateListEquipe(t);
				break;
			}
			case "Confirmer":{
				//System.out.println(this.vue.getAllListEquipesTournoi());
				Tournoi t = this.vue.getTournoi();
				System.out.println(this.vue.getAllListEquipesTournoi().size());
				List<Equipe> equipes = this.vue.getAllListEquipesTournoi();
				if(equipes.size() == 16) {
					int resultat = JOptionPane.showConfirmDialog(this.vue, "Souhaitez-vous valider l'inscription ?","Confirmer Inscription", JOptionPane.OK_CANCEL_OPTION);
					if (resultat == JOptionPane.OK_OPTION) {
						for(Equipe equipe : equipes) {
							try {
								t.addEquipe(equipe);
							} catch (Exception e1) {
								e1.printStackTrace();
							}
						}
						try {
							System.out.println(t.getEtat());
							t.demarrer();
							Tournoi.modifierTournoi(t);
						} catch (Exception ex) {
							ex.printStackTrace();
						}
						Phase.enregistrerPhase(t.getPhasePoule());
					}
				}
				JFrame frame = this.modele.getPanelFrame(vue);
				frame.dispose();
				break;
			}
		}
	
	}
}


