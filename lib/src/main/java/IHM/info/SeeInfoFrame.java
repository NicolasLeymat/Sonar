package IHM.info;

import java.awt.Dimension;

import javax.swing.JFrame;

import Object.Ecurie;
import Object.Equipe;
import Object.Joueur;
import Object.Tournoi;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

public class SeeInfoFrame extends JFrame{

	private final int WIDTH = 768;
	private final int HEIGHT = 450;
	private VueInfoPanel vue;
	

	/**
	 * @wbp.parser.constructor
	 */
	public SeeInfoFrame(Ecurie ec) {
		this.setVisible(true);
		this.setMinimumSize(new Dimension(WIDTH - 250, HEIGHT));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{500, 0};
		gridBagLayout.rowHeights = new int[]{450, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		vue = new VueInfoPanel(ec);
		GridBagConstraints gbc_vue = new GridBagConstraints();
		gbc_vue.fill = GridBagConstraints.BOTH;
		gbc_vue.gridx = 0;
		gbc_vue.gridy = 0;
		getContentPane().add(vue, gbc_vue);
	}

	public SeeInfoFrame(Equipe eq) {
		this.setVisible(true);
		this.setMinimumSize(new Dimension(WIDTH+150, HEIGHT+50));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{600, 0};
		gridBagLayout.rowHeights = new int[]{450, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		vue = new VueInfoPanel(eq);
		GridBagConstraints gbc_vue = new GridBagConstraints();
		gbc_vue.fill = GridBagConstraints.BOTH;
		gbc_vue.gridx = 0;
		gbc_vue.gridy = 0;
		getContentPane().add(vue, gbc_vue);
	}
	
	public SeeInfoFrame(Tournoi t) {
		this.setVisible(true);
		this.setMinimumSize(new Dimension(WIDTH, HEIGHT));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{600, 0};
		gridBagLayout.rowHeights = new int[]{450, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		vue = new VueInfoPanel(t);
		GridBagConstraints gbc_vue = new GridBagConstraints();
		gbc_vue.fill = GridBagConstraints.BOTH;
		gbc_vue.gridx = 0;
		gbc_vue.gridy = 0;
		getContentPane().add(vue, gbc_vue);
	}
	
	public SeeInfoFrame(Joueur j) {
		this.setVisible(true);
		this.setMinimumSize(new Dimension(WIDTH, HEIGHT));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{600, 0};
		gridBagLayout.rowHeights = new int[]{450, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		vue = new VueInfoPanel(j);
		GridBagConstraints gbc_vue = new GridBagConstraints();
		gbc_vue.fill = GridBagConstraints.BOTH;
		gbc_vue.gridx = 0;
		gbc_vue.gridy = 0;
		getContentPane().add(vue, gbc_vue);
		
	}
}
