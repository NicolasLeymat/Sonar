package IHM;

import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.event.CaretListener;

import controleur.ModeleESporter;

import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SearchPanel extends JPanel{
	private JTextField searchField;
	private JButton searchButton;
	
	public SearchPanel() {
		this.setVisible(true);
		this.setSize(1200, 100);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{99, 1000, 101, 0};
		gridBagLayout.rowHeights = new int[]{23, 50, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel lblNewLabel = new JLabel("Recherche : ");
		lblNewLabel.setFont(ModeleESporter.FONT_LARGE);
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNewLabel.anchor = GridBagConstraints.NORTH;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 0;
		add(lblNewLabel, gbc_lblNewLabel);
		searchField = new JTextField();
		searchField.setBackground(new Color(255, 255, 255));
		searchField.setHorizontalAlignment(SwingConstants.LEFT);
		searchField.setFont(ModeleESporter.FONT_LARGE);
		searchField.setColumns(50);
		searchField.setToolTipText("Rechercher");
		GridBagConstraints gbc_searchField = new GridBagConstraints();
		gbc_searchField.insets = new Insets(0, 0, 0, 5);
		gbc_searchField.fill = GridBagConstraints.BOTH;
		gbc_searchField.gridx = 1;
		gbc_searchField.gridy = 1;
		this.add(searchField, gbc_searchField);
		
		searchButton = new JButton("Rechercher");
		GridBagConstraints gbc_searchButton = new GridBagConstraints();
		gbc_searchButton.fill = GridBagConstraints.BOTH;
		gbc_searchButton.gridx = 2;
		gbc_searchButton.gridy = 1;
		searchButton.setFont(ModeleESporter.FONT_MEDIUM_LARGE);
		add(searchButton, gbc_searchButton);
	}
	
	public void changeWitdth(int newWidth) {
		this.searchField.setSize(newWidth, 50);
	}
	
	public void addListenerButton(ActionListener c) {
		this.searchButton.addActionListener(c);
	}
	
	public String getTextSearch() {
		return this.searchField.getText();
	}
}
