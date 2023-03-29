
package Object;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Application.Connexion;

//Classe qui définit les fonctions d'un joueur
public class Joueur {
	
	private int id;
	private String nom;
	private String prenom;
	private String pseudo;
	private String dateNaissance;
	private Nationalite nationalite;
	private int idEquipe;

	//Contructeur de la classe "Joueur"
	public Joueur(String nom, String prenom, String pseudo, String dateNaissance, Nationalite nationalite) {
	    this.nom = nom;
	    this.prenom = prenom;
	    this.pseudo = pseudo;
	    this.dateNaissance = dateNaissance;
	    this.nationalite = nationalite;
	    this.id = -1;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
	//Fonction qui permet de récuperer le nom d'un joueur
    public String getNom() {
        return nom;
    }
     
    //Fonction qui permet de changer le nom d'un joueur
    public void setNom(String nom) {
		this.nom = nom;
	}
    
    //Fonction qui permet de récuperer le prénom d'un joueur
    public String getPrenom() {
    	return prenom;
    }
    
  //Fonction qui permet de récuperer le pseudo d'un joueur
    public String getPseudo() {
        return pseudo;
    }
    
    //Fonction qui permet de changer le prénom d'un joueur
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	//Fonction qui permet de changer le pseudo d'un joueur
	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	public String getDateNaissance() {
		return dateNaissance;
	}

	public void setDateNaissance(String dateNaissance) {
		this.dateNaissance = dateNaissance;
	}
	
	//Fonction qui permet de récuperer la nationalité d'un joueur
	public Nationalite getNationalite() {
		return nationalite;
	}
	
	//Fonction qui permet de changer la nationalité d'un joueur
	public void setNationalite(Nationalite nationalite) {
		this.nationalite = nationalite;
	}

	public int getIdEquipe() {
		return idEquipe;
	}

	public void setIdEquipe(int idEquipe) {
		this.idEquipe = idEquipe;
	}

    
    public static int enregistrerJoueur(Joueur joueur) {
    	Connection connex = Connexion.connexion();
    	PreparedStatement pst;
    	int existe;

		try {
			
			existe = verifierPresenceJoueur(joueur, 1);
			if (existe == 1) {
				return -1;
			}
			
			if (joueur.getId() == -1) {
				joueur.setId(Joueur.getLastId()+1);
			}
			
			pst = connex.
					prepareStatement("insert into LMN3783A.sae_joueur(id_joueur, nom, prenom, pseudonyme, datedenaissance, nationalites, id_equipe) values(?,?,?,?,?,?,?)");
			pst.setInt(1, joueur.getId());
			pst.setString(2, joueur.getNom());
			pst.setString(3, joueur.getPrenom());
			pst.setString(4, joueur.getPseudo());
			pst.setString(5,joueur.getDateNaissance());
			pst.setString(6,joueur.getNationalite().getCode());
			pst.setInt(7,joueur.getIdEquipe());
			pst.executeUpdate();
			
			pst.close();
			
		} catch (Exception ex) {
			ex.printStackTrace();
			return -1;
		}
		return 1;
    }
    
    
    
    public static int modifierJoueur(Joueur j) {
    	Connection connex = Connexion.connexion();
		PreparedStatement pst;
		int existe;
		
		try {
			
			existe = verifierPresenceJoueur(j, 0);
			if (existe == 0) {
				return -1;
			}
			
			pst = connex
					.prepareStatement("update LMN3783A.sae_joueur set prenom = ?, nom = ?, pseudonyme = ?, datedenaissance = ?, nationalites = ?, id_equipe = ? where id_joueur = ?" );
			pst.setString(1, j.getPrenom());
			pst.setString(2, j.getNom());
			pst.setString(3, j.getPseudo());
			pst.setString(4, j.getDateNaissance());
			pst.setString(5, j.getNationalite().getCode());
			pst.setInt(6, j.getIdEquipe());
			pst.setInt(7, j.getId());
			pst.executeUpdate();

			pst.close();
			
		} catch (SQLException ex) {
			ex.printStackTrace();
			return -1;
		}
		return 1;
	}
    
    public static int supprimerJoueur(Joueur j) {
    	Connection connex = Connexion.connexion();
		PreparedStatement pst;
		int existe;
		
		try {
			
			existe = verifierPresenceJoueur(j,1);
			if (existe == 0) {
				return -1;
			}
			
			pst = connex.prepareStatement("delete from LMN3783A.sae_joueur where nom = ? and prenom = ? and pseudonyme = ?" );
			pst.setString(1, j.getNom());
			pst.setString(2, j.getPrenom());
			pst.setString(3, j.getPseudo());
			pst.executeUpdate();
			
			pst.close();
			
		} catch (Exception ex) {
			ex.printStackTrace();
			return -1; 
		}
		return 1;
	}
    
    public static List<Joueur> getJoueursFromEquipe(int id) {
    	Connection connex = Connexion.connexion();
		PreparedStatement pst = null;
		ResultSet rs;
		Joueur e = null;
		List<Joueur> r = new ArrayList<Joueur>();
		
		try {
			
			pst = connex.
					prepareStatement("Select j.id_joueur ,j.nom, j.prenom, j.pseudonyme, j.datedenaissance, j.nationalites, j.id_equipe from LMN3783A.sae_joueur j, LMN3783A.SAE_Equipe e where e.id_equipe = j.id_equipe and e.id_equipe = ? order by 3");
			pst.setInt(1, id);
			rs = pst.executeQuery();
			while (rs.next()) {
				e = new Joueur(rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5), Nationalite.valueOf(rs.getString(6)));
				e.setIdEquipe(rs.getInt(7));
				e.setId(rs.getInt(1));
				r.add(e);
			}
			
			rs.close();
			pst.close();
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return r;
	}
    
    public static int getLastId() {
		Connection connex = Connexion.connexion();
		Statement st;
		ResultSet rs;
		int r = 0;
		
		try {
			
			st = connex.createStatement();
			rs = st.executeQuery("select max(id_joueur) from LMN3783A.sae_joueur");
			rs.next();
			r = rs.getInt(1);
			
			rs.close();
			st.close();
			
		} catch (Exception ee) {
			ee.printStackTrace();
		}
		return r;
	}
    
    public static String getNomJoueurFromId(int id) {
		Connection connex = Connexion.connexion();
		PreparedStatement pst;
		ResultSet rs;
		String s = null;
		
		try {
			
			pst = connex.prepareStatement("select nom from LMN3783A.sae_joueur where id_joueur = ?");
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
    
    private static int verifierPresenceJoueur(Joueur j, int v) {
    	Connection connex = Connexion.connexion();
		PreparedStatement pst;
		ResultSet rs;
		int res = 0;
		
		try {
			if (v == 0) {
				pst = connex.prepareStatement("select count(1) from LMN3783A.sae_joueur where id_joueur = ?" );
				pst.setInt(1, j.getId());
			}
			else {
				pst = connex.prepareStatement("select count(1) from LMN3783A.sae_joueur where nom = ? and prenom = ? and pseudonyme = ?" );
				pst.setString(1, j.getNom());
				pst.setString(2, j.getPrenom());
				pst.setString(3, j.getPseudo());
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

	@Override
	public String toString() {
		return this.pseudo;
	}

	

}
