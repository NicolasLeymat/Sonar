package IHM.tournois;

import IHM.info.TableauMatchHistorique;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.JScrollPane;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.UIManager;

import Object.Tournoi;
import Object.Match;
import controleur.ModeleESporter;


public class FrameClassementTournois extends JFrame {

	private VueClassementTournois contentPane;
	private JTable table;
	private JTable table_1;


	private Tournoi tournoi;
	
	/**
	 * Create the frame.
	 */
	public FrameClassementTournois(Tournoi t) {
		this.tournoi = t;
		setBounds(100, 100, 1000, 600);
		contentPane = new VueClassementTournois(t);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
	}

}
