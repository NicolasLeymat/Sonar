package Object;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import Application.Connexion;

//Classe qui définit les fonctions d'un organisateur
public class Organisateur {
	
	private int id;
	private String nom;

	//Constructeur de la classe "Organisateur"
	public Organisateur(String nom) {
		this.setNom(nom);
		this.id = -1;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

    //Fonction qui permet de récuperer le nom de l'organisateur
	public String getNom() {
		return nom;
	}

    //Fonction qui permet de changer le nom de l'organisateur
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public static Organisateur getOrganisateurFromId(int id) {
		Connection connx = Connexion.connexion();
		PreparedStatement pst;
		ResultSet rs;
		Organisateur res = null;
		
		try {
			pst = connx.prepareStatement("select id_organisateur, nom from LMN3783A.SAE_ORGANISATEUR where ID_ORGANISATEUR = ?");
			pst.setInt(1, id);
			rs = pst.executeQuery();
			rs.next();
			res = new Organisateur(rs.getString(2));
			res.setId(id);
			
			rs.close();
			pst.close();
			
		}catch (SQLException e) {
			e.getStackTrace();
		}
		return res;
	}
	
	public static List<Organisateur> getAllOrganisateurs() {
		Connection connx = Connexion.connexion();
		Organisateur o = null;
		List<Organisateur> res = new LinkedList<>();
		PreparedStatement pst;
		try {
			pst = connx.prepareStatement("Select id_organisateur, nom from LMN3783A.SAE_ORGANISATEUR order by nom");
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				o = new Organisateur(rs.getString(2));
				o.setId(rs.getInt(1));
				res.add(o);
			}
			
			rs.close();
			pst.close();
			
		}catch (SQLException e) {
			e.getStackTrace();
		}
		
		return res;
	}
	
}
