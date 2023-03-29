package Object;

import Application.Connexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

/*
 * Travail restant :
 * - ajouter le logo
 */

public class Ecurie {
	
	/**
	 * nom de l'ecurie
	 */
	private String nom;
	/**
	 * liste contenant les equipes d'une ecurie
	 */
	private List<Equipe> listeEquipes;
	/**
	 * id d'une ecurie
	 */
	private int id;
	/**
	 * logo d'une ecurie
	 */
	private ImageIcon logo;
	/**
	 * Nationalité de l'écurie
	 */
	private Nationalite nat;
	/**
	 * construit une ecurie a partir d'un nom
	 * @param nom
	 * 		nom d'une ecurie
	 */
	public Ecurie(String nom) {
		this.id = -1;
		this.nom = nom;
		this.listeEquipes = new ArrayList<Equipe>();
	}
	/**
	 * retourne l'id d'une ecurie
	 * @return id de l'ecurie
	 */
	public int getId() {
		return this.id;
	}
	/**
	 * affecte une id a une ecurie
	 * @param id
	 * 		id d'une ecurie
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * retourne le nom d'une ecurie
	 * @return nom
	 */
	public String getNom() {
		return this.nom;
	}
	/**
	 * change le nom d'une ecurie
	 * @param nom
	 * 		nouveau nom de l'ecurie
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}
	/**
	 * retourne l'adresse du logo d'une ecurie
	 * @return adresse logo
	 */
	public ImageIcon getLogo() {
		return this.logo;
	}
	/**
	 * ajoute ou modifie l'adresse du logo d'une ecurie
	 * @param logo
	 * 		adresse du logo d'une ecurie
	 */
	public void setLogo(ImageIcon logo) {
		this.logo = logo;
	}
	
	/**
	 * ajoute une equipe a une ecurie
	 * @param equipe
	 */
	public void addEquipe(Equipe equipe) {
		equipe.setIdEcurie(this.id);
		this.listeEquipes.add(equipe);
	}
	/**
	 * retourne une equipe appartenant a une ecurie a partir de son nom
	 * @param nom
	 * 		nom de l'ecurie
	 * @return une ecurie ou null
	 */
	public Equipe getEquipe(String nom) {
		for (Equipe equipe : this.listeEquipes) {
			if (equipe.getNom() == nom) {
				return equipe;
			}
		}
		return null;	
	}
	/**
	 * supprime une equipe d'une ecurie
	 * @param equipe
	 * 		equipe a supprimer
	 */
	public void removeEquipe(Equipe equipe) {
		this.listeEquipes.remove(equipe);
	}
	/**
	 * retourne toutes les equipes d'une ecurie
	 * @return equipes
	 */
	public List<Equipe> getEquipes(){
		return this.listeEquipes;
	}
	/**
	 * ajoute une liste d'equipes dans une ecurie en supprimant les equipes deja 
	 * existante si elles existent
	 * @param liste 
	 * 		liset d'equipes
	 */
	public void addEquipes(List<Equipe> liste) {
		this.listeEquipes = liste;
	}


	/**
	 * enregistre une ecurie dans la base de donnees
	 * si elle n'existe pas deja
	 * ainsi que toutes les equipes et les joueurs associes
	 * @param ecurie 
	 * 		ecurie
	 * @return 1 si la fonction a réussi ou -1 si la fonction a echoue
	 */
	public static int enregistrerEcurie(Ecurie ecurie) {
		Connection connex = Connexion.connexion();
		PreparedStatement pst;
		int existe;
		
		try {
			
			existe = verifierPresenceEcurie(ecurie,1);
			if (existe != 0) {
				return -1;
			}

			if (ecurie.getId() == -1) {
				ecurie.setId(Ecurie.getLastId()+1);
			}
			
			pst = connex.prepareStatement("insert into LMN3783A.sae_ecurie(id_ecurie, nom) values(?,?)");
			pst.setInt(1, ecurie.getId());
			pst.setString(2, ecurie.getNom());
			pst.executeUpdate();
			
			for (Equipe eq : ecurie.getEquipes()) {
				eq.setIdEcurie(ecurie.getId());
				Equipe.enregistrerEquipe(eq);
			}
			
			pst.close();
			
		} catch (Exception ex) {
			ex.printStackTrace();
			return -1;
		}
		return 1;
	}
	/**
	 * modifie une equipe dans la base de donnees a partir de son id
	 * si l'id n'existe pas dans la base de donnees, il n'y a pas de modifications
	 * @param ecurie
	 * 		ecurie a modifier
	 * @return 1 si la modification a eu lieu, -1 sinon 
	 */
	public static int modifierEcurie(Ecurie ecurie) {
		Connection connex = Connexion.connexion();
		PreparedStatement pst;
		int existe;
		
		try {
			
			existe = verifierPresenceEcurie(ecurie,0);
			if (existe == 0) {
				return -1;
			}
			
			pst = connex.prepareStatement("update LMN3783A.sae_ecurie set nom = ? where id_ecurie = ?" );
			pst.setString(1, ecurie.getNom());
			pst.setInt(2, ecurie.getId());
			pst.executeUpdate();
			
			pst.close();
			
		} catch (Exception ex) {
			ex.printStackTrace();
			return -1;
		}
		return 1;
	}
	/**
	 * supprimer une ecurie de la base de donnees si elle existe
	 * @param ecurie 
	 * 		ecurie
	 * @return 1 si la fonction a réussi ou -1 si la fonction a echoue
	 */
	public static int supprimerEcurie(Ecurie ecurie) {
		Connection connex = Connexion.connexion();
		PreparedStatement pst;
		int existe;
		
		try {
			
			existe = verifierPresenceEcurie(ecurie,1);
			if (existe == 0) {
				return -1;
			}
			
			for (Equipe eq : ecurie.getEquipes()) {
				Equipe.supprimerEquipe(eq);
			}
			
			pst = connex.prepareStatement("delete from LMN3783A.sae_ecurie where nom = ?" );
			pst.setString(1, ecurie.getNom());
			pst.executeUpdate();
			
			pst.close();
			
		} catch (Exception ex) {
			ex.printStackTrace();
			return -1; 
		}
		return 1;
	}
	
	/**
	 * retourne l'ecurie associee a un nom si elle existe
	 * @param nom 
	 * 		nom de l'ecurie
	 * @return l'ecurie si elle existe, null sinon
	 */
	public static Ecurie getEcurieFromNom(String nom) {
		Connection connex = Connexion.connexion();
		PreparedStatement pst;
		ResultSet rs;
		Ecurie e = null;
		
		try {
			
			pst = connex.prepareStatement("select id_ecurie, logo from LMN3783A.sae_ecurie where nom = ?");
			pst.setString(1, nom);
			rs = pst.executeQuery();
			while(rs.next()) {
				e = new Ecurie(nom);
				e.setId(rs.getInt(1));
				e.setLogo(new ImageIcon(Ecurie.class.getResource("/Images/"+rs.getString(2))));
				e.listeEquipes = Equipe.getEquipesFromEcurie(rs.getInt(1));
			}
			
			rs.close();
			pst.close();
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return e;
	}
	/**
	 * retourne l'ecurie associee a une id si elle existe
	 * @param id 
	 * 		id de l'ecurie
	 * @return l'ecurie si elle existe, null sinon
	 */
	public static Ecurie getEcurieFromId(int id) {
		Connection connex = Connexion.connexion();
		PreparedStatement pst;
		ResultSet rs;
		Ecurie e = null;
		
		try {
			
			pst = connex.prepareStatement("select nom from LMN3783A.sae_ecurie where id_ecurie = ?");
			pst.setInt(1, id);
			rs = pst.executeQuery();
			while (rs.next()) {
				e = new Ecurie(rs.getString(1));
				e.setId(id);
				//e.listeEquipes = Equipe.getEquipesFromEcurie(id);
			}
			
			rs.close();
			pst.close();
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return e;
	}
	/**
	 * retourne le nom de l'ecurie associee a une id
	 * @param id
	 * 		id de l'ecurie
	 * @return le nom de l'ecurie ou null si elle n'existe pas
	 */
	public static String getNomEcurieFromId(int id) {
		Connection connex = Connexion.connexion();
		PreparedStatement pst;
		ResultSet rs;
		String s = null;
		
		try {
			
			pst = connex.prepareStatement("select nom from LMN3783A.sae_ecurie where id_ecurie = ?");
			pst.setInt(1, id);
			rs = pst.executeQuery();
			while (rs.next()) {
				s = rs.getString(1);
			}
			
			rs.close();
			pst.close();
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return s;
	}
	/**
	 * retourne toutes les ecuries dont le nom commence par le parametre nom
	 * @param nom 
	 * 		debut du nom des ecuries a chercher
	 * @return la liste des ecuries dont le nom commence par le parametre nom
	 */
	public static List<Ecurie> getEcurieFromNomAll(String nom) {
		Connection connex = Connexion.connexion();
		PreparedStatement pst;
		ResultSet rs;
		List<Ecurie> l = new ArrayList<>();
		Ecurie e = null;
		
		try {
			
			pst = connex.prepareStatement("select id_ecurie, nom from LMN3783A.sae_ecurie where nom LIKE ? order by nom");
			pst.setString(1, nom+"%");
			rs = pst.executeQuery();
			while (rs.next()) {
				e = new Ecurie(rs.getString(2));
				e.setId(rs.getInt(1));
				e.listeEquipes = Equipe.getEquipesFromEcurie(rs.getInt(1));
				l.add(e);
			}
			
			rs.close();
			pst.close();
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return l;
	}
	/**
	 * retourne toutes les ecuries de la base de donnees
	 * @return les ecuries de la base de donnees
	 */
	public static List<Ecurie> getAllEcuries() {
		Connection connex = Connexion.connexion();
		Statement st;
		ResultSet rs;
		List<Ecurie> ecuries = new ArrayList<>();
		Ecurie e = null;
		
		try {
			
			st = connex.createStatement();
			rs = st.executeQuery("select id_ecurie, nom from LMN3783A.sae_ecurie order by nom");
			while (rs.next()) {
				e = new Ecurie(rs.getString(2));
				e.setId(rs.getInt(1));
				e.listeEquipes = Equipe.getEquipesFromEcurie(rs.getInt(1));
				ecuries.add(e);
			}
			
			rs.close();
			st.close();
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return ecuries;
	}
	
	/**
	 * retourne l'id la plus grande de la base de donnees
	 * @return id
	 */
	public static int getLastId() {
		Connection connex = Connexion.connexion();
		Statement st;
		ResultSet rs;
		int r = 0;
		
		try {
			
			st = connex.createStatement();
			rs = st.executeQuery("select max(id_ecurie) from LMN3783A.sae_ecurie");
			rs.next();
			r = rs.getInt(1);
			
			rs.close();
			st.close();
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return r;
	}
	/**
	 * retourne 1 si l'ecurie e est deja presente dans la BD
	 * @param e
	 * 		ecurie a tester
	 * @param v
	 * 		si v est egal a 0, on verifie si l'ecurie existe a partir de son id sinon on verifie si l'ecurie existe a partir de son nom
	 * @return 1 si l'ecurie existe, 0 sinon
	 */
	private static int verifierPresenceEcurie(Ecurie e, int v) {
		Connection connex = Connexion.connexion();
		PreparedStatement pst;
		ResultSet rs;
		int res = 0;
		
		try {
			if (v == 0) {
				pst = connex.prepareStatement("select count(1) from LMN3783A.sae_ecurie where id_ecurie = ?" );
				pst.setInt(1, e.getId());
			}
			else {
				pst = connex.prepareStatement("select count(1) from LMN3783A.sae_ecurie where nom = ?" );
				pst.setString(1, e.getNom());
			}
			
			rs = pst.executeQuery();
			rs.next();
			res = rs.getInt(1);
			rs.close();
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return res;
	}

	public static List<Ecurie> getEcurieFromNat(Nationalite nat){
		Connection connex = Connexion.connexion();
		PreparedStatement pst;
		ResultSet rs;
		List<Ecurie> l = new ArrayList<>();
		Ecurie e = null;
		
		try {
			
			pst = connex.prepareStatement("select id_ecurie, nom, nat from LMN3783A.sae_ecurie where nat LIKE ? order by nom");
			pst.setString(1, nat.getCode());
			rs = pst.executeQuery();
			while (rs.next()) {
				e = new Ecurie(rs.getString(2));
				e.setId(rs.getInt(1));
				e.setNat(Nationalite.getByCode(rs.getString(3)));
				e.listeEquipes = Equipe.getEquipesFromEcurie(rs.getInt(1));
				l.add(e);
			}
			
			rs.close();
			pst.close();
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return l;
		
	}
	
	
	@Override
	public String toString() {
		return this.nom;
	}


	public Nationalite getNat() {
		return nat;
	}


	public void setNat(Nationalite nat) {
		this.nat = nat;
	}

}
