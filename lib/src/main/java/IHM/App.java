package IHM;

import java.awt.Dimension;

import javax.swing.JFrame;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

public class App extends JFrame{

	public static final int WIDTH = 1300;
	public static final int HEIGHT = 625;
	
	public App() throws Exception {
		this.setVisible(true);
		this.setMinimumSize(new Dimension(WIDTH, HEIGHT));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{1186, 0};
		gridBagLayout.rowHeights = new int[]{588, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		VuePrincipale vuePrincipale = new VuePrincipale();
		GridBagConstraints gbc_vuePrincipale = new GridBagConstraints();
		gbc_vuePrincipale.fill = GridBagConstraints.BOTH;
		gbc_vuePrincipale.gridx = 0;
		gbc_vuePrincipale.gridy = 0;
		getContentPane().add(vuePrincipale, gbc_vuePrincipale);
	}
	
}
