package IHM.tournois;

import javax.swing.JFrame;
import Object.Tournoi;

import java.awt.Dimension;
import java.awt.GridBagLayout;


public class FrameInscriptionTournois extends JFrame {

	private VueInscriptionTournois contentPane;

	/**
	 * Create the frame.
	 */
	public FrameInscriptionTournois(Tournoi t) {
		this.setMinimumSize(new Dimension(550, 450));
		setBounds(100, 100, 600, 450);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0};
		gridBagLayout.rowHeights = new int[]{0};
		gridBagLayout.columnWeights = new double[]{Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		contentPane = new VueInscriptionTournois(t);
		//System.out.println("Id Tournoi : " + t.getId());
		this.getContentPane().add(contentPane);
		
		//this.setContentPane(contentPane);
	}

}
