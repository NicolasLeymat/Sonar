package Object;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import Application.Connexion;

public class Jeu {

	private int id;
	private String nom;
	private List<ModeDeJeu> modesDeJeu;
	
	/**
	 * construit un jeu a partir d'une id et d'un nom
	 * @param id
	 * @param nomJeu
	 */
	public Jeu(int id, String nomJeu) {
		this.id = id;
		this.nom = nomJeu;
		this.modesDeJeu = new ArrayList<ModeDeJeu>();
	}
	
	public int getIdJeu() {
		return this.id;
	}
	
	public void setIdJeu(int id) {
		this.id = id;
	}
	
	public String getNom() {
		return this.nom;
	}

	
	public void setNom(String nomJeu) {
		this.nom = nomJeu;
	}
	
	public List<ModeDeJeu> getModesDeJeu(){
		return this.modesDeJeu;
	}
	
	public void setModeDeJeu(List<ModeDeJeu> l) {
		this.modesDeJeu = l;
	}
	
	/**
	 * Retourne un id jeu non utilise
	 * @return dernier id de la base +1
	 */
	public int getLastId() {
		Connection ct = Connexion.connexion();
		java.sql.Statement st = null;
		ResultSet rs;
		int r = 0;
		try {
			st = ct.createStatement();
			rs = st.executeQuery("Select max(id_jeu) as id from LMN3783A.sae_jeu");
			if (rs.next()) {
				r = rs.getInt("id")+1;
			}
		} catch (SQLException ee) {
			ee.printStackTrace();
		}
		return r;
	}
	
	/**
	 * retourne l'id du jeu passé en paramètre
	 * @param jeu
	 * @return idJeu
	 */
	public static int getId(Jeu jeu) {
		Connection connex = Connexion.connexion();
		PreparedStatement pst;
		ResultSet rs;
		try {
			pst = connex.prepareStatement("select id_jeu from LMN3783A.sae_jeu where LMN3783A.sae_jeu.nom = ?");
			pst.setString(1, jeu.getNom());
			rs = pst.executeQuery();
			rs.next();
			return rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
		}
	
	/**
	 * Enregistre un jeu dans la basede donnée
	 * @return 1 si le jeu est enregistré dans la base et -1 si erreur
	 */
	public int enregistrerJeu() {
		Connection connex = Connexion.connexion();
		PreparedStatement pst;
		int lastId = this.getLastId();
		try {
			pst = connex.prepareStatement("insert into LMN3783A.sae_jeu values(?,?)");
			pst.setInt(1, lastId+1);
			pst.setString(2, nom);
			pst.executeUpdate();
		} catch (SQLException e1) {
			e1.printStackTrace();
			return -1;
		}
		return 1;
	}
	
	public static Jeu getJeuFromId(int id) {
        Connection connx = Connexion.connexion();
		Jeu jeu = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = connx.prepareStatement("SELECT nom as n FROM LMN3783A.sae_Jeu where id_jeu = ?");
            st.setInt(1, id);
            rs = st.executeQuery();
            while (rs.next()) {
                jeu = new Jeu(id, rs.getString("n"));
                jeu.setModeDeJeu(ModeDeJeu.getModesDeJeuFromIdJeu(jeu));
            }
            
            rs.close();
            st.close();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return jeu;
    }
	
	/**
	 * Retourne une liste contenant tous les jeux présent dans la base de données
	 * @return List<Jeu>
	 */
	public static List<Jeu> getAllJeux() {
		Connection connx = Connexion.connexion();
		Jeu j = null;
		List<Jeu> res = new LinkedList<>();
		PreparedStatement pst;
		try {
			pst = connx.prepareStatement("Select id_jeu, nom from LMN3783A.SAE_JEU order by nom");
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				j = new Jeu(rs.getInt(1), rs.getString(2));
				j.setModeDeJeu(ModeDeJeu.getModesDeJeuFromIdJeu(j));
				res.add(j);
			}
			
			rs.close();
			pst.close();
			
		}catch (SQLException e) {
			e.getStackTrace();
		}
		
		return res;
	}


}
