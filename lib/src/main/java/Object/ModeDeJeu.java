package Object;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import Application.Connexion;

public class ModeDeJeu {
	
	/**
	 * Travail restant : 
	 * - Ajouter des methodes utiles
	 */
	
	private int idMode;
	private String nom;
	private int nbMinJoueur;
	private Jeu jeu;
	
	private ModeDeJeu(int id, String nom, int nbJoueur, Jeu jeu) {
		this.idMode = id;
		this.nom = nom;
		this.nbMinJoueur = nbJoueur;
		this.jeu = jeu;
	}
	
	public int getIdMode() {
		return idMode;
	}
	
	
	public void setIdMode(int id_Mode) {
		this.idMode = id_Mode;
	}
	
	
	public String getNom() {
		return nom;
	}
	
	
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	
	public int getNbMinJoueur() {
		return nbMinJoueur;
	}
	
	
	public void setNbMinJoueur(int nbMinJoueur) {
		this.nbMinJoueur = nbMinJoueur;
	}
	
	
	public Jeu getJeu() {
		return jeu;
	}
	
	
	public void setJeu(Jeu jeu) {
		this.jeu = jeu;
	}
	
	public static ModeDeJeu getModeDeJeuFromId(int id) {
		Connection connx = Connexion.connexion();
		PreparedStatement pst;
		ResultSet rs;
		ModeDeJeu res = null;
		
		try {
			pst = connx.prepareStatement("Select id_mode, nom, nb_joueur, id_jeu from LMN3783A.SAE_MODE_DE_JEU where id_Mode = ?");
			pst.setInt(1, id);
			rs = pst.executeQuery();
			while(rs.next()) {
				res = new ModeDeJeu(rs.getInt(1), rs.getString(2), rs.getInt(3),Jeu.getJeuFromId(rs.getInt(4)));
				
			}
			
			rs.close();
			pst.close();
			
		}catch (SQLException e) {
			e.getStackTrace();
		}
		return res;
	}
	
	public static ModeDeJeu getModeDeJeuFromNom(String nom) {
		Connection connex = Connexion.connexion();
		PreparedStatement pst;
		ResultSet rs;
		ModeDeJeu m = null;
		
		try {
			
			//rajouter verification
			
			pst = connex.prepareStatement("select id_mode, nom, nb_joueur, id_jeu from LMN3783A.sae_mode_de_jeu where nom = ?");
			pst.setString(1, nom);
			rs = pst.executeQuery();
			rs.next();
			m = new ModeDeJeu(rs.getInt(1), rs.getString(2), rs.getInt(3),Jeu.getJeuFromId(rs.getInt(4)));
			
			rs.close();
			pst.close();
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return m;
	}
	
	public static List<ModeDeJeu> getModeDeJeuFromNomAll(String nom) {
		Connection connex = Connexion.connexion();
		PreparedStatement pst;
		ResultSet rs;
		List<ModeDeJeu> m = new ArrayList<>();
		
		try {
			
			//rajouter verification
			
			pst = connex.prepareStatement("select id_mode, nom, nb_joueur, id_jeu from LMN3783A.sae_mode_de_jeu where nom = ?");
			pst.setString(1, nom);
			rs = pst.executeQuery();
			while (rs.next()) {
				m.add(new ModeDeJeu(rs.getInt(1), rs.getString(2), rs.getInt(3),Jeu.getJeuFromId(rs.getInt(4))));
			}
			rs.close();
			pst.close();
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return m;
	}
	
	public static List<ModeDeJeu> getAllModeDeJeu() {
		Connection connx = Connexion.connexion();
		ModeDeJeu m = null;
		List<ModeDeJeu> res = new LinkedList<>();
		PreparedStatement pst;
		try {
			pst = connx.prepareStatement("Select id_mode, nom, nb_joueur, id_jeu from LMN3783A.SAE_MODE_DE_JEU");
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				m = new ModeDeJeu(rs.getInt(1), rs.getString(2), rs.getInt(3),Jeu.getJeuFromId(rs.getInt(4)));
				res.add(m);
			}
			
			rs.close();
			pst.close();
			
		}catch (SQLException e) {
			e.getStackTrace();
			return  res;
		}
		
		return res;
	}
	
	public static List<ModeDeJeu> getModesDeJeuFromIdJeu(Jeu j) {
		Connection connx = Connexion.connexion();
		ModeDeJeu m = null;
		List<ModeDeJeu> res = new LinkedList<>();
		PreparedStatement pst;
		
		try {
			pst = connx.prepareStatement("Select id_mode, nom, nb_joueur, id_jeu from LMN3783A.SAE_MODE_DE_JEU where id_jeu = ? order by nom");
			pst.setInt(1, j.getIdJeu());
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				m = new ModeDeJeu(rs.getInt(1), rs.getString(2), rs.getInt(3),j);
				res.add(m);
			}
			
			rs.close();
			pst.close();
			
		}catch (SQLException e) {
			e.getStackTrace();
		}
		
		return res;
	}




	@Override
	public String toString() {
		return "ModeDeJeu [id_Mode=" + idMode + ", nom=" + nom + ", nbMinJoueur=" + nbMinJoueur + ", jeu=" + jeu + "]";
	}
	
	
	
	
}