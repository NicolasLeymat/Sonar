package IHM.info;

import Object.Tournoi;
import controleur.ControlleurListeMatch;

import javax.swing.*;
import javax.swing.event.AncestorListener;
import java.awt.*;
import java.awt.event.ContainerAdapter;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class VueInfoTournoisFrame extends JFrame {
    private Tournoi tournoi;

    private JButton nextbutton;
    public Tournoi getTournoi() {
        return tournoi;
    }
    public void setButtonText(String s) {
    	nextbutton.setText(s);
    }

    public VueInfoTournoisFrame(Tournoi tournoi) {
        this.tournoi =tournoi;


        this.setSize(600, 450);
        this.setLayout(new BorderLayout());

        this.nextbutton  = new JButton("Phase suivante");
        ControlleurListeMatch controlleurListeMatch = new ControlleurListeMatch(this);
        VueInfoTournoisPanel  infoTournoisPanel= new VueInfoTournoisPanel(tournoi,controlleurListeMatch);

        JPanel panelBouton = new JPanel();
        panelBouton.setLayout(new BoxLayout(panelBouton,BoxLayout.LINE_AXIS));
        this.nextbutton.addActionListener(controlleurListeMatch);

        panelBouton.setBorder(BorderFactory.createEmptyBorder(0,10,10,10));
        panelBouton.add(Box.createHorizontalGlue());
        panelBouton.add(Box.createRigidArea(new Dimension(10, 0)));
        panelBouton.add(this.nextbutton);
        this.add(panelBouton);

        //Put everything together, using the content pane's BorderLayout.
        Container contentPane = getContentPane();
        contentPane.add(infoTournoisPanel, BorderLayout.CENTER);
        contentPane.add(panelBouton, BorderLayout.PAGE_END);


    }

    public void setActiveNextButton(boolean b) {
        nextbutton.setEnabled(b);
    }


}