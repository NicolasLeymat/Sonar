package controleur;

import IHM.info.VueInfoTournoisFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Object.Phase;
import Object.Tournoi;

public class ControlleurListeMatch implements ActionListener {
    private enum ETAT {
        ENCOURS,FINIPOULE,FINIELIM,FINI
    }

    private ETAT etat;
    private VueInfoTournoisFrame vue;

    public ControlleurListeMatch(VueInfoTournoisFrame vue) {
        this.vue = vue;
        setBoutonSuivant();
    }

    public void setBoutonSuivant() {
        if (vue.getTournoi().elimmatchsfini()) {
            System.out.println("ELIM");
            if (!vue.getTournoi().getPhaseElim().estFinie()) {
                this.etat = ETAT.FINIELIM;
                vue.setActiveNextButton(true);
            } else {
                this.etat = ETAT.FINI;
                vue.setActiveNextButton(true);
            }
        } else if (vue.getTournoi().getPhaseElim() == null && vue.getTournoi().getPhasePoule().matchsFinis()) {
            this.etat = ETAT.FINIPOULE;
            vue.setActiveNextButton(true);
        } else {
            this.etat = ETAT.ENCOURS;
            vue.setActiveNextButton(false);
        }
    }




    @Override
    public void actionPerformed(ActionEvent e) {
        JButton bouton = (JButton) e.getSource();
        switch (etat) {
            case FINIPOULE -> {
                etat = ETAT.ENCOURS;
                vue.getTournoi().genererPhaseFinale();
                Phase.enregistrerPhase(vue.getTournoi().getPhaseElim());
                bouton.setEnabled(false);
                vue.dispose();
            }
            case FINIELIM -> {
                etat = ETAT.ENCOURS;
                vue.getTournoi().getPhaseElim().genererMatchs();
                vue.getTournoi().getPhaseElim().enregistrerMatchs();
                bouton.setEnabled(false);
                vue.dispose();
            }
            case FINI -> {
                vue.setButtonText("Terminer le tournoi");
            	try {
                    vue.getTournoi().ajouterPoints();
                    vue.getTournoi().setEtat(Tournoi.ETAT.FINI);
                    Tournoi.modifierTournoi(vue.getTournoi());
                    vue.dispose();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}
