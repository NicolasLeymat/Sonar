package IHM;

import javax.swing.JPanel;
import javax.swing.JButton;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.SwingConstants;

import controleur.ControleurAdd;
import controleur.ControleurChamp;
import controleur.ModeleESporter;

import java.awt.GridLayout;
import javax.swing.JLabel;

public class ButtonPanel extends JPanel{

	
	public ButtonPanel() {
		ControleurAdd c = new ControleurAdd(this, null);
		ControleurChamp champ = new ControleurChamp(this);
		setSize(1200, 100);
		Dimension btnSize = new Dimension(200,75);
		setLayout(new GridLayout(0, 3, 0, 0));
		
		JPanel panel = new JPanel();
		add(panel);
		panel.setLayout(null);
		
		JLabel lblTeam = new JLabel("Listes des équipes : ");
		lblTeam.setHorizontalAlignment(SwingConstants.CENTER);
		lblTeam.setFont(ModeleESporter.FONT_MEDIUM);
		lblTeam.setBounds(120, 10, 150, 30);
		panel.add(lblTeam);
		
		//
		JButton seeChamp = new JButton("Championnat du monde");
		seeChamp.setLocation(100, 35);
		panel.add(seeChamp);
		seeChamp.setFont(ModeleESporter.FONT_MEDIUM);
		seeChamp.setSize(new Dimension(200, 50));
		seeChamp.addActionListener(champ);
		//

		JPanel panel_1 = new JPanel();
		add(panel_1);
		panel_1.setLayout(null);
		
		JButton addSupervisor = new JButton("Ajouter une écurie");
		addSupervisor.setLocation(100, 35);
		panel_1.add(addSupervisor);
		addSupervisor.setFont(ModeleESporter.FONT_MEDIUM);
		addSupervisor.setSize(new Dimension(200, 50));
		addSupervisor.addActionListener(c);
		
		JLabel lblEcuries = new JLabel("Listes des écuries : ");
		lblEcuries.setHorizontalAlignment(SwingConstants.CENTER);
		lblEcuries.setFont(ModeleESporter.FONT_MEDIUM);
		lblEcuries.setBounds(120, 10, 150, 30);
		panel_1.add(lblEcuries);
		
		JPanel panel_2 = new JPanel();
		add(panel_2);
		panel_2.setLayout(null);
		
		JButton NewTournament = new JButton("Ajouter un tournoi");
		NewTournament.setLocation(100, 35);
		panel_2.add(NewTournament);
		NewTournament.setFont(ModeleESporter.FONT_MEDIUM);
		NewTournament.setSize(new Dimension(200, 50));
		NewTournament.addActionListener(c);
		
		JLabel lblTournois = new JLabel("Listes des tournois : ");
		lblTournois.setHorizontalAlignment(SwingConstants.CENTER);
		lblTournois.setFont(ModeleESporter.FONT_MEDIUM);
		lblTournois.setBounds(120, 10, 150, 30);
		panel_2.add(lblTournois);
	}
}
