package Object;

import Application.Connexion;
import oracle.jdbc.proxy.annotation.Pre;

import java.awt.Image;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Equipe implements Comparable<Equipe> {

	/**
	 * id de l'ecurie
	 */
	private int id;
	/**
	 * nom de l'equipe
	 */
	private String nom;
	/**
	 * points de l'equipe
	 */
	private int points;
	/**
	 * id de l'ecurie associee a l'equipe
	 */
	private int idEcurie;
	/**
	 * id du mode de jeu joue par l'equipe
	 */
	private int idModeDeJeu;
	/**
	 * liste des joueurs de l'equipe
	 */
	private List<Joueur> listeJoueurs;
	/**
	 * Logo de l'équipe
	 */
	private ImageIcon logo;
	private String stringLogo;
	/**
	 * construit une equipe a partir de son nom
	 * @param nom
	 * 		nom d'une equipe
	 */
	public Equipe(String nom) {
		this.id = -1;
		this.nom = nom;
		this.listeJoueurs = new ArrayList<>();
	}
	/**
	 * retourne l'id d'une equipe
	 * @return id de l'equipe
	 */
	public int getId() {
		return id;
	}
	/**
	 * affecte une id a une equipe
	 * @param id
	 * 		id d'une equipe
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * retourne le nom d'une equipe
	 * @return nom de l'equipe
	 */
	public String getNom() {
		return nom;
	}
	/**
	 * change le nom d'une equipe
	 * @param nom
	 * 		nouveau nom de l'equipe
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}
	/**
	 * retourne les points d'une equipe
	 * @return points de l'equipe
	 */
	public int getPoints() {
		return points;
	}
	/**
	 * change le nombre de points d'une equipe
	 * @param points
	 * 		nouveau points de l'equipe
	 */
	private void setPoints(int points) {
		this.points = points;
	}
	public void setStringLogo(String logo) {
		this.stringLogo = logo;
	}
	/**
	 * ajoute des points a une equipe
	 * @param points
	 * 		points d'une equipe
	 * @throws Exception
	 */
	public void addPoints(int points) throws Exception {
		if (points <0) {throw new IllegalArgumentException("nombre de points a jouter doit etre positif");}
		this.setPoints(this.points+points);

		try  {
			Connection connection = Connexion.connexion();

			if (this.getId() <0) {
				System.out.println("Equipe non liée");
				return;
			}

			PreparedStatement ps = connection.prepareStatement("UPDATE lmn3783a.sae_EQUIPE SET points = ? where id_equipe = ?");
			ps.setInt(1,this.getPoints());
			ps.setInt(2,this.getId());
			System.out.println("POINTS "+this.getNom()+" = "+ this.points);
			ps.executeUpdate();
		}
		catch (SQLException e) {
			System.out.println("Erreur SQL " + e.getMessage());
		}
	}
	/**
	 * retourne l'id de l'ecurie associee a l'equipe
	 * @return id de l'ecurie
	 */
	public int getIdEcurie() {
		return this.idEcurie;
	}
	/**
	 * affecte l'id de l'ecurie associee a l'equipe
	 * @param id
	 * 		id de l'ecurie
	 */
	public void setIdEcurie(int id) {
		this.idEcurie = id;
	}
	/**
	 * retourne l'id du mode de jeu associe a une equipe
	 * @return id du mode de jeu
	 */
	public int getIdModeDeJeu() {
		return idModeDeJeu;
	}
	/**
	 * affecte l'id d'un mode de jeu a une equipe
	 * @param id
	 * 		id du mode de jeu
	 */
	public void setIdModeDeJeu(int id) {
		this.idModeDeJeu = id;
	}
	/**
	 * retourne la liste des joueurs d'une equipe
	 * @return la liste des joueurs d'une equipe
	 */
	public List<Joueur> getJoueurs(){
		return this.listeJoueurs;
	}
	/**
	 * retourne l'ecurie associee a une equipe
	 * @return l'ecurie d'une equipe
	 */
	public Ecurie getEcurie() {
		return Ecurie.getEcurieFromId(this.idEcurie);
	}
	/**
	 * retourne le mode de jeu associe a une equipe
	 * @return mode de jeu d'une equipe
	 */
	public ModeDeJeu getModeDeJeu() {
		return ModeDeJeu.getModeDeJeuFromId(this.idModeDeJeu);
	}
	/**
	 * ajoute un joueur a une equipe
	 * @param joueur
	 * 		joueur a ajouter
	 */
	public void addJoueur(Joueur joueur) {
		this.listeJoueurs.add(joueur);
	}
	
	/**
	 * enregistre une equipe dans la BD
	 * @param equipe
	 * 		equipe a enregistrer
	 * @return 1 si l'equipe a ete enregistree, -1 sinon
	 * @throws Exception
	 */
	public static int enregistrerEquipe(Equipe equipe) {
		Connection connex = Connexion.connexion();
		PreparedStatement pst;
		int existe;
		
		try {
			
			// A remplacer par un trigger
			existe = verifierPresenceEquipe(equipe,1);
			if (existe != 0) {
				return -1;
			}
			
			if (equipe.getId() == -1) {
				equipe.setId(Equipe.getLastId()+1);
			}
			
			pst = connex.prepareStatement("insert into LMN3783A.sae_equipe(id_equipe, nom, points, logo, id_ecurie, id_mode) values(?,?,?,?,?,?)");
			pst.setInt(1, equipe.getId());
			pst.setString(2, equipe.getNom());
			pst.setInt(3, equipe.getPoints());
			pst.setString(4, equipe.stringLogo);
			pst.setInt(5,equipe.getIdEcurie());
			pst.setInt(6,equipe.getIdModeDeJeu());
			pst.executeUpdate();
			
			for (Joueur j : equipe.getJoueurs()) {
				j.setIdEquipe(equipe.getId());
				Joueur.enregistrerJoueur(j);
			}
			
			pst.close();
			
		} catch (SQLException ex) {
			ex.printStackTrace();
			return -1;
		}
		return 1;
	}
	/**
	 * modifie une equipe dans la BD
	 * @param equipe
	 * 		equipe a modifier
	 * @return 1 si l'equipe a ete modifiee, -1 sinon
	 */
	public static int modifierEquipe(Equipe equipe) {
		Connection connex = Connexion.connexion();
		PreparedStatement pst;
		int existe;
		
		try {
			
			existe = verifierPresenceEquipe(equipe,0);
			if (existe == 0) {
				return -1;
			}
			
			pst = connex.prepareStatement("update LMN3783A.sae_equipe set nom = ?, id_ecurie = ?, id_mode = ? where id_equipe = ?" );
			pst.setString(1, equipe.getNom());
			pst.setInt(2, equipe.getIdEcurie());
			pst.setInt(3, equipe.getIdModeDeJeu());
			pst.setInt(4, equipe.getId());
			pst.executeUpdate();
			
			pst.close();
			
		} catch (SQLException ex) {
			ex.printStackTrace();
			return -1;
		}
		return 1;
	}
	/**
	 * supprime une equipe dans la BD
	 * @param equipe
	 * 		equipe a supprimer
	 * @return
	 */
	public static int supprimerEquipe(Equipe equipe) {
		Connection connex = Connexion.connexion();
		PreparedStatement pst;
		int existe;
		
		try {
			
			existe = verifierPresenceEquipe(equipe,1);
			if (existe == 0) {
				return -1;
			}
			
			for (Joueur j : equipe.getJoueurs()) {
				Joueur.supprimerJoueur(j);
			}
			
			pst = connex.prepareStatement("delete from LMN3783A.sae_equipe where nom = ?" );
			pst.setString(1, equipe.getNom());
			pst.executeUpdate();
			
			pst.close();
			
		} catch (SQLException ex) {
			ex.printStackTrace();
			return -1; 
		}
		return 1;
	}
	
	public static int inscrireEquipeTournoi(Equipe equipe, Tournoi tournoi) {
    	Connection connex = Connexion.connexion();
    	PreparedStatement pst;
    	
		try {
			pst = connex.
					prepareStatement("insert into LMN3783A.sae_participer(id_tournoi, id_equipe) values(?,?)");
			pst.setInt(1, tournoi.getId());
			pst.setInt(2, equipe.getId());
			pst.executeUpdate();
			
			pst.close();
			
		} catch (Exception ex) {
			ex.printStackTrace();
			return -1;
		}
		return 1;
    }
	
	/**
	 * retourne toutes les equipes de la BD
	 * @return les equipes de la base de donnees
	 */
	public static List<Equipe> getAllEquipes() {
		List<Equipe> equipes = new ArrayList<Equipe>();
		Connection connex = Connexion.connexion();
		java.sql.Statement st = null;
		ResultSet rs;
		Equipe e = null;
		try {
			st = connex.createStatement();
			rs = st.executeQuery("select id_equipe, nom, points, id_ecurie, id_mode, logo from LMN3783A.sae_equipe order by nom");
			while (rs.next()) {
				e = createEquipeFromRs(rs);
				equipes.add(e);
			}
			
			rs.close();
			st.close();
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return equipes;
	}
	/**
	 * retourne la liste des equipes associees a un mode de jeu
	 * la liste est ordonnee par ordre croissant de points
	 * @param idMode
	 * 		le mode de jeu dont on veut avoir le classement
	 * @return la liste des equipes d'un mode de jeu ordonnee par le classement
	 */
	public static List<Equipe> getClassementByGame(int idMode) {
		Connection connex = Connexion.connexion();
		PreparedStatement ps;
		ResultSet rs;
		List<Equipe> equipes = new ArrayList<Equipe>();
		Equipe e = null;
		
		try {
			
			ps = connex.prepareStatement("select id_equipe, nom, points, id_ecurie,id_mode, logo from LMN3783A.sae_equipe where id_mode = ? ORDER BY points desc");
			ps.setInt(1,idMode);
			rs = ps.executeQuery();
			while (rs.next()) {
				e = createEquipeFromRs(rs);
				equipes.add(e);
			}
			
			rs.close();
			ps.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return equipes;
	}
	/**
	 * retourne toutes les equipes associees a une ecurie
	 * @param id
	 * 		id d'une ecurie
	 * @return les equipes d'une ecurie
	 */
	public static List<Equipe> getEquipesFromEcurie(int id) {
		Connection connex = Connexion.connexion();
		PreparedStatement pst = null;
		ResultSet rs;
		Equipe e = null;
		List<Equipe> r = new ArrayList<Equipe>();
		try {
			pst = connex.prepareStatement("Select id_equipe, nom, points, id_ecurie, id_mode, logo from LMN3783A.sae_equipe where id_ecurie= ?");
			pst.setInt(1, id);
			rs = pst.executeQuery();
			while (rs.next()) {
				e = createEquipeFromRs(rs);
				r.add(e);
			}
			
			rs.close();
			pst.close();
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		//System.out.println(r);
		return r;
	}
	/**
	 * retourne toutes les equipes dont le nom commence par le parametre nom
	 * @param nom
	 * 		debut du nom des equipes que l'on recherche
	 * @return toutes les equipes dont le nom commence par le parametre
	 */
	public static List<Equipe> getEquipeFromNomAll(String nom) {
		Connection connex = Connexion.connexion();
		PreparedStatement pst = null;
		ResultSet rs;
		List<Equipe> l = new LinkedList<>();
		Equipe e = null;
		
		try {
			
			pst = connex.prepareStatement("Select id_equipe, nom, points, id_ecurie, id_mode, logo from LMN3783A.sae_equipe where lower(nom) LIKE lower(?)");
			pst.setString(1, "%"+nom+"%");
			rs = pst.executeQuery();
			while (rs.next()) {
				e = createEquipeFromRs(rs);
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
	 * retourne l'equipe associee a une id
	 * @param id
	 * 		id d'une equipe
	 * @return equipe possedant l'id en parametre
	 */
	public static Equipe getEquipeFromId(int id) {
		Connection connex = Connexion.connexion();
		PreparedStatement pst;
		ResultSet rs;
		Equipe e = null;
		
		try {
			
			pst = connex.prepareStatement("select id_equipe, nom, points, id_ecurie, id_mode, logo from LMN3783A.sae_equipe where id_equipe = ?");
			pst.setInt(1, id);
			rs = pst.executeQuery();
			while (rs.next()) {
				e = createEquipeFromRs(rs);
			}
			
			rs.close();
			pst.close();
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return e;
	}
	/**
	 * retourne l'equipe dont le nom est le parametre nom
	 * @param nom
	 * 		nom d'une equipe
	 * @return l'equipe portant le parametre nom
	 */
	public static Equipe getEquipeFromNom(String nom) {
		Connection connex = Connexion.connexion();

		PreparedStatement pst = null;
		ResultSet rs;
		Equipe e = null;
		
		try {
			
			pst = connex.prepareStatement("Select id_equipe, nom, points, id_ecurie, id_mode, logo from LMN3783A.sae_equipe where nom = ?");
			pst.setString(1, nom);
			rs = pst.executeQuery();
			while (rs.next()) {
				e = createEquipeFromRs(rs);
			}
			rs.close();
			pst.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return e;
	}
	
	/**
	 * retourne l'id la plus grande de la base de donnees
	 * @return id
	 * 		id la plus grande
	 */
	public static int getLastId() {
		Connection connex = Connexion.connexion();
		Statement st;
		ResultSet rs;
		int r = 0;
		
		try {
			
			st = connex.createStatement();
			rs = st.executeQuery("select max(id_equipe) from LMN3783A.sae_equipe");
			rs.next();
			r = rs.getInt(1);
			
			rs.close();
			st.close();
			
		} catch (Exception ee) {
			ee.printStackTrace(); 
		}
		return r;
	}
	
	/**
	 * retourne le nom d'une equipe a partir de son id
	 * @param id
	 * 		id de l'equipe a retourner
	 * @return equipe possedant l'id en parametre
	 */
	public static String getNomEquipeFromId(int id) {
		Connection connex = Connexion.connexion();
		PreparedStatement pst;
		ResultSet rs;
		String s = null;
		
		try {
			
			pst = connex.prepareStatement("select nom from LMN3783A.sae_equipe where id_equipe = ?");
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
	
	public static List<Equipe> getEquipeFromMode(int id){
		Connection connex = Connexion.connexion();
		PreparedStatement pst;
		ResultSet rs;
		Equipe e = null;
		String s = null;
		List<Equipe> l = new LinkedList<>();
		
		try {
			
			pst = connex.prepareStatement("select id_equipe, nom, points, id_ecurie, id_mode, logo from LMN3783A.sae_equipe where id_mode = ?");
			pst.setInt(1, id);
			rs = pst.executeQuery();
			while (rs.next()) {
				e = createEquipeFromRs(rs);
				l.add(e);
			}
			
			rs.close();
			pst.close();
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return l;
	}
	
	
	private static int verifierPresenceEquipe(Equipe e, int v) {
		Connection connex = Connexion.connexion();
		PreparedStatement pst;
		ResultSet rs;
		int res = 0;
		
		try {
			if (v == 0) {
				pst = connex.prepareStatement("select count(1) from LMN3783A.sae_equipe where id_equipe = ?" );
				pst.setInt(1, e.getId());
			}
			else {
				pst = connex.prepareStatement("select count(1) from LMN3783A.sae_equipe where nom = ?" );
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
	
	private static Equipe createEquipeFromRs(ResultSet rs) throws SQLException {
		Equipe e;
		e = new Equipe(rs.getString(2));
		e.setId(rs.getInt(1));
		e.setPoints(rs.getInt(3));
		e.setIdEcurie(rs.getInt(4));
		e.setIdModeDeJeu(rs.getInt(5));
		Image image = null;
		String link = rs.getString(6);
		//System.out.println("Link : "+link);
		try {
			URL url = new URL(link);
			//System.out.println("URL : "+url.openStream());
			image = ImageIO.read(url);
			e.setLogo(new ImageIcon(image));
		} catch (IOException e1) {
			e.setLogo(null);
		}
		e.listeJoueurs = Joueur.getJoueursFromEquipe(e.getId());
		return e;
	}

	@Override
	public String toString() {
		return nom+", Points : " + points;
	}
	
	public ImageIcon getLogo() {
		return logo;
	}
	
	public void setLogo(ImageIcon logo) {
		this.logo = logo;
	}

	@Override
	public int compareTo(Equipe o) {
		 return o.points - this.points;
	}
	public static List<Equipe> getAllEquipesFromTournoi(Tournoi t) {
		List<Equipe> equipes = new ArrayList<Equipe>();
		Connection connex = Connexion.connexion();
		PreparedStatement pst;
		ResultSet rs;
		Equipe e = null;
		try {
			pst = connex.prepareStatement("select e.id_equipe, e.nom, e.points, e.id_ecurie, e.id_mode, e.logo from LMN3783A.sae_equipe e, LMN3783A.sae_tournoi t, "
					+ "LMN3783A.sae_participer p where e.id_equipe = p.id_equipe and p.id_tournoi = t.id_tournoi and t.id_tournoi = ?"
					+ "order by nom");
			pst.setInt(1,t.getId());
			rs = pst.executeQuery();
			while (rs.next()) {
				e = createEquipeFromRs(rs);
				equipes.add(e);
			}
			
			rs.close();
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return equipes;
	}
	public static List<Equipe> getAllEquipesFromModeDeJeu(int m) {
		List<Equipe> equipes = new ArrayList<Equipe>();
		Connection connex = Connexion.connexion();
		PreparedStatement pst = null;
		ResultSet rs;
		Equipe e = null;
		try {
			pst = connex.prepareStatement("select id_equipe, nom, points, id_ecurie, id_mode, logo from LMN3783A.sae_equipe where id_mode = ? order by nom");
			pst.setInt(1, m);
			rs = pst.executeQuery();
			while (rs.next()) {
				e = createEquipeFromRs(rs);
				equipes.add(e);
			}
				
			rs.close();
				
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return equipes;
	}
	
	public static int supprimerEquipeTournoi(Equipe obj, Tournoi t) {
		Connection connex = Connexion.connexion();
    	PreparedStatement pst;

		try {
			pst = connex.
					prepareStatement("delete from LMN3783A.sae_participer where id_equipe = ? and id_tournoi = ?");
			pst.setInt(1, obj.getId());
			pst.setInt(2, t.getId());
			pst.executeUpdate();
			
			pst.close();
			
		} catch (Exception ex) {
			ex.printStackTrace();
			return -1;
		}
		return 1;
	}
	
}
