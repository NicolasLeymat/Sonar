package controleur;



import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import IHM.tournois.VueArbitrageTournois;
import Object.Match;

public class ControleurArbitrageTournoi implements ActionListener {
    private VueArbitrageTournois vue;
    private Match match;


    public ControleurArbitrageTournoi(VueArbitrageTournois vue) {
        this.vue = vue;
        this.match = vue.getMatch();
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(e.getSource().getClass());
        if (e.getSource() instanceof JButton) {
            JButton bouton = (JButton) e.getSource();
            if (bouton == vue.getButtonWin1()) {
                bouton.setEnabled(false);
                vue.getButtonWin2().setEnabled(true);
                match.setWinner(1);
            }
            else {
                bouton.setEnabled(false);
                vue.getButtonWin1().setEnabled(true);
                match.setWinner(2);
            }

        }
    }
}
