package IHM.tournois;



import javax.swing.JFrame;
import Object.Match;

public class FrameArbitrageTournois extends JFrame {
	private Match match;
  
	public FrameArbitrageTournois(Match t) {
		this.match = t;
	    // définition de la fenêtre
	    setTitle(t.getEquipe1().getNom() +" VS "+ t.getEquipe2().getNom());
	    setSize(500, 275);
		setResizable(false);
	    setLocationRelativeTo(null);
	    this.setContentPane(new VueArbitrageTournois(t));
	}
  

}

