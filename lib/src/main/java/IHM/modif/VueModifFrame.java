package IHM.modif;

import java.awt.Dimension;

import javax.swing.JFrame;

import Object.Ecurie;
import Object.Equipe;
import Object.Joueur;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

public class VueModifFrame extends JFrame{

	private final int WIDHT = 500;
	private final int HEIGHT = 300;
	private static final long serialVersionUID = 1L;
	private VueModifPanel vue;

	public VueModifFrame(Equipe e) {
		this.setVisible(true);
		this.setSize(new Dimension(WIDHT, HEIGHT));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{600, 0};
		gridBagLayout.rowHeights = new int[]{1, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		vue = new VueModifPanel(e);
		GridBagConstraints gbc_vue = new GridBagConstraints();
		gbc_vue.fill = GridBagConstraints.BOTH;
		gbc_vue.gridx = 0;
		gbc_vue.gridy = 0;
		getContentPane().add(vue, gbc_vue);
	}

	/**
	 * @wbp.parser.constructor
	 */
	public VueModifFrame(Ecurie eq) {
		this.setVisible(true);
		this.setSize(new Dimension(WIDHT, HEIGHT));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{1, 0};
		gridBagLayout.rowHeights = new int[]{1, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		vue = new VueModifPanel(eq);
		GridBagConstraints gbc_vue = new GridBagConstraints();
		gbc_vue.fill = GridBagConstraints.BOTH;
		gbc_vue.gridx = 0;
		gbc_vue.gridy = 0;
		getContentPane().add(vue, gbc_vue);
	}
	
	public VueModifFrame(Joueur j) {
		this.setVisible(true);
		this.setSize(new Dimension(WIDHT, HEIGHT));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{600, 0};
		gridBagLayout.rowHeights = new int[]{1, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		vue = new VueModifPanel(j);
		GridBagConstraints gbc_vue = new GridBagConstraints();
		gbc_vue.fill = GridBagConstraints.BOTH;
		gbc_vue.gridx = 0;
		gbc_vue.gridy = 0;
		getContentPane().add(vue, gbc_vue);
	}

}
