package controleur;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;

import IHM.info.SeeInfoFrame;
import IHM.info.VueInfoTournoisFrame;
import IHM.tournois.FrameClassementTournois;
import IHM.tournois.FrameInscriptionTournois;
import Object.Ecurie;
import Object.Equipe;
import Object.Joueur;
import Object.Tournoi;

public class ControleurJList implements MouseListener {

	@Override
	public void mouseClicked(MouseEvent e) {
		JList<?> list = (JList<?>) e.getSource();
		int index = list.locationToIndex(e.getPoint());
		switch (list.getModel().getElementAt(index).getClass().toString()) {
		case "class Object.Equipe":
			System.out.println("Equipe : " + list.getModel().getElementAt(index));
			SeeInfoFrame window = new SeeInfoFrame((Equipe) (list.getModel().getElementAt(index)));
			window.setVisible(true);
			break;
		case "class Object.Ecurie":
			System.out.println(list.getModel().getElementAt(index).getClass().toString());
			SeeInfoFrame windowEcurie = new SeeInfoFrame((Ecurie) list.getModel().getElementAt(index));
			windowEcurie.setVisible(true);
			break;
		case "class Object.Tournoi":
			System.out.println(list.getModel().getElementAt(index).getClass().toString());
			Tournoi tournoiSelected = (Tournoi) list.getModel().getElementAt(index);
			try {
				tournoiSelected.getPhasesfromID();
			} catch (Exception ex) {
				throw new RuntimeException(ex);
			}
			JFrame windowTournoi = null;
			switch (tournoiSelected.getEtat())  {
				case FINI :
					System.out.println(tournoiSelected.toString());
					windowTournoi = new FrameClassementTournois(tournoiSelected);
				break;

				case INSC:
					System.out.println(tournoiSelected.toString());
					windowTournoi = new FrameInscriptionTournois(tournoiSelected);
				break;

				case ENC:
					System.out.println(tournoiSelected);
					windowTournoi = new VueInfoTournoisFrame(tournoiSelected);
				break;
			}
			windowTournoi.setVisible(true);
			break;
		case "class Object.Joueur":
			System.out.println(list.getModel().getElementAt(index).getClass().toString());
			SeeInfoFrame windowJoueur = new SeeInfoFrame((Joueur) list.getModel().getElementAt(index));
			windowJoueur.setVisible(true);
			break; 
		}

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
