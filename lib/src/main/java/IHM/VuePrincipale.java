package IHM;


import javax.swing.JPanel;

import controleur.ControleurRecherche;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

public class VuePrincipale extends JPanel{

	private SearchPanel searchPanel;
	private MainPanel mainPanel;
	private ButtonPanel buttonPanel;
	
	public VuePrincipale() throws Exception {
		this.setSize(new Dimension(1200, 625));
		ControleurRecherche controleurRecherche = new ControleurRecherche(this);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{1200, 0};
		gridBagLayout.rowHeights = new int[]{100, 100, 225, 0};
		gridBagLayout.columnWeights = new double[]{2.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		this.searchPanel = new SearchPanel();
		searchPanel.addListenerButton(controleurRecherche);
		
		GridBagConstraints gbc_searchPanel = new GridBagConstraints();
		gbc_searchPanel.fill = GridBagConstraints.BOTH;
		gbc_searchPanel.insets = new Insets(0, 0, 5, 0);
		gbc_searchPanel.gridx = 0;
		gbc_searchPanel.gridy = 0;
		this.add(searchPanel, gbc_searchPanel);
		
		buttonPanel = new ButtonPanel();
		GridBagConstraints gbc_buttonPanel = new GridBagConstraints();
		gbc_buttonPanel.insets = new Insets(0, 0, 5, 0);
		gbc_buttonPanel.fill = GridBagConstraints.BOTH;
		gbc_buttonPanel.gridx = 0;
		gbc_buttonPanel.gridy = 1;
		add(buttonPanel, gbc_buttonPanel);
		
		mainPanel = new MainPanel();
		mainPanel.setMaximumSize(new Dimension(1200, 525));
		GridBagConstraints gbc_mainPanel = new GridBagConstraints();
		gbc_mainPanel.fill = GridBagConstraints.BOTH;
		gbc_mainPanel.gridx = 0;
		gbc_mainPanel.gridy = 2;
		this.add(mainPanel, gbc_mainPanel);
		
	}
	
	public String getTextSearch() {
		return this.searchPanel.getTextSearch();
	}
}
