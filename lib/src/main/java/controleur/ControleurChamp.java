package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import IHM.add.AddFrame;
import IHM.add.AddPanel;
import IHM.champ.ChampFrame;

public class ControleurChamp implements ActionListener{
	
	private JPanel vue;
	private ModeleESporter modele;
	private ChampFrame frame;
	
	public ControleurChamp(JPanel v) {
		this.vue = v;
		this.modele = new ModeleESporter();
		this.frame = null;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton btn = (JButton) e.getSource();
		switch(btn.getText()) {
			case "Championnat du monde":{
				this.frame = new ChampFrame();
				this.frame.setVisible(true);
				break;
		}
		
		
	}

}
}