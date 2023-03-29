package Object;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Application.Connexion;

//Classe qui définit les fonctions d'un tournoi
public class Tournoi {

	private int id;
	private String nom;
	private Date dateTournoi;
	private int championnat;
	private int notoriete;
	private int id_organisateur;
	private ModeDeJeu id_Mode;
	private List<Equipe> listeEquipe;
	private PhaseDePoule phasePoule;
	private PhaseFinale phaseElim;

	public boolean elimmatchsfini() {
		return (this.getPhaseElim() != null && this.getPhaseElim().matchsFinis());
	}

	public enum Notoriete {
		LOCAL(1) ,NATIONAL(2), INTERNATIONAL(3);
		private int value;
		Notoriete(int i) {
			this.value = i;
		}

		public int getValue() {
			return this.value;
		}
	}

	private ETAT etat;

	public enum ETAT {
		ENC("ENC"),
		FINI("FINI"),
		INSC("INSC");
		private String value;
		ETAT(String s ) {
			this.setValue(s);
		}
		public String getValue() {
			return value;
		}
		public void setValue(String value) {
			this.value = value;
		}
	}

	//Definit les points gagnés selon la place finale
	private enum PointsClassement {
		PREMIER(100),DEUXIEME(60),TROISIEME(30),QUATRIEME(10);
		private int points;
		PointsClassement(int nb) {
			this.points = nb;
		}
	}

	//Constructeur de la classe "Tournoi"
	public Tournoi(String nom, Date dateTournoi, int championnat,int notoriete, int id_organisateur, ModeDeJeu id_Mode, ETAT etat) throws Exception {
		this.setEtat(etat);

		if (notoriete > 3 || notoriete < 1) {
			throw new Exception();
		}
		if (etat == ETAT.INSC && dateInvalide(dateTournoi)) {
			throw new Exception();
		}
		
		this.nom = nom;
		this.dateTournoi = dateTournoi;
		this.championnat = championnat;
		this.notoriete = notoriete;
		this.id_organisateur = id_organisateur;
		this.listeEquipe = new ArrayList<Equipe>();
		this.phasePoule = new PhaseDePoule(this);
		//this.phaseElim = new PhaseFinale(this,phasePoule);
		this.id_Mode = id_Mode;
		this.id = -1;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

    //Fonction qui permet de récuperer le nom d'un tournoi
	public String getNom() {
		return nom;
	}
	
	//Fonction qui permet de récuperer une équipe d'un tournoi
	public Equipe getEquipe (String nom) {
		for (Equipe e : this.listeEquipe) {
			if (e.getNom() == nom) {
				return e;
			}
		}
		return null;
	}

	public void demarrer() throws Exception {
		if (this.etat != ETAT.INSC) {
			throw  new IllegalArgumentException("Le tournoi est déja lancé");
		}
		if (this.listeEquipe.size() < 16 ) {
			throw  new IllegalArgumentException("Le tournoi n'est pas plein, il faut 16 equipes, il y en a "+listeEquipe.size() );
		}
		this.getPhasePoule().genererMatchs();
		PhaseDePoule.enregistrerPhase(this.getPhasePoule());
		this.etat = ETAT.ENC;
	}

	public PhaseDePoule getPhasePoule() {
		return phasePoule;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	//Fonction qui permet de récuperer la date d'un tournoi
	public Date getDateTournoi() {
		return dateTournoi;
	}
	
	//Fonction qui permet de changer la date d'un tournoi
	public void setDateTournoi(Date dateTournoi) {
		this.dateTournoi = dateTournoi;
	}

	//Fonction qui permet de récuperer la notoriété d'un tournoi
	public int getNotoriete() {
		return notoriete;
	}
	
	//Fonction qui permet de récuperer le championnat d'un tournoi
		public int getChampionnat() {
			return this.championnat;
		}

	//Fonction qui permet de changer la notoriété
	public void setNotoriete(int notoriete) {
		this.notoriete = notoriete;
	}
	
	//Fonction qui permet de récuperer l'identifiant de l'organisateur d'un tournoi
	public int getId_Organisateur() {
		return id_organisateur;
	}

	//Fonction qui permet de récuperer l'identifiant du mode de jeu d'un tournoi
	public ModeDeJeu getId_Mode() {
		return id_Mode;
	}

	//Fonction qui permet d'ajouter une équipe à un tournoi
	public void addEquipe(Equipe equipe) throws Exception{
		if (this.listeEquipe.size()>=16) {
			throw new Exception("Tournoi plein");
		}
		if (dateInvalide(this.dateTournoi)==true) {
			throw new Exception("le tournoi est déjà fini");
		}
		this.listeEquipe.add(equipe);
	}
	
	//Fonction qui permet de retourner une réponse positive ou négative si une date est invalide
	private static boolean dateInvalide(Date date) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
		LocalDateTime now = LocalDateTime.now();
		String temp = dtf.format(now);
		Date today = Date.valueOf(temp);
		boolean res = date.before(today);
		return res;
		
	}

	public void genererPhaseFinale() {
		if (phasePoule.matchsFinis()) {
			phaseElim = new PhaseFinale(this,this.phasePoule);
			phaseElim.genererMatchs();
		}
	}

	public List<Equipe> getListeEquipe() {
		return listeEquipe;
	}
	
	//Fonction qui permet de récuperer une équipe d'un tournoi
	public Equipe getEquipeTournoi(int i) {
		return this.listeEquipe.get(i);
	}

	public PhaseFinale getPhaseElim() {
		return phaseElim;
	}
	
	private static boolean verifierPresenceTournoi( Tournoi tournoi) throws SQLException {
		PreparedStatement pst;
		ResultSet rs;
		Connection connex = Connexion.connexion();
		pst = connex.prepareStatement("select count(1) from LMN3783A.sae_Tournoi where nom = ? and datetournoi= ?" );
		pst.setString(1, tournoi.getNom());
		pst.setDate(2, tournoi.getDateTournoi());
		rs = pst.executeQuery();
		rs.next();
		return rs.getInt(1) >=1;
	}
	
	private static boolean verifierPresenceTournoi( int id) throws SQLException {
		PreparedStatement pst;
		ResultSet rs;
		Connection connex = Connexion.connexion();
		pst = connex.prepareStatement("select count(1) from LMN3783A.sae_tournoi where id_tournoi = ?" );
		pst.setInt(1, id);
		rs = pst.executeQuery();
		rs.next();
		boolean res = rs.getInt(1) >= 1;
		rs.close();
		return res;
	}
	
	
	public static int getLastId() {
		Connection connex = Connexion.connexion();
		Statement st;
		ResultSet rs;
		int r = 0;
		
		try {
			
			st = connex.createStatement();
			rs = st.executeQuery("select max(id_tournoi) from LMN3783A.sae_tournoi");
			rs.next();
			r = rs.getInt(1);
			
			rs.close();
			st.close();
			
		} catch (Exception ee) {
			ee.printStackTrace(); 
		}
		return r;
	}
	//Fonction qui permet d'enregistrer un tournoi dans la base de données
		public static int enregistrerTournoi(Tournoi tournoi) throws Exception {
			Connection connex = Connexion.connexion();
			PreparedStatement pst;
			try {


				if (tournoi.getId() == -1) {
					tournoi.setId(Tournoi.getLastId() + 1);
				}

				pst = connex.prepareStatement("insert into LMN3783A.sae_tournoi(id_tournoi, nom, datetournoi, championnat, notoriete, id_organisateur,id_mode, etat) values(?,?,?,?,?,?,?,?)");
				pst.setInt(1, tournoi.getId());
				pst.setString(2, tournoi.getNom());
				pst.setDate(3, tournoi.getDateTournoi());
				pst.setInt(4, tournoi.getChampionnat());
				pst.setInt(5, tournoi.getNotoriete());
				pst.setInt(6, tournoi.getId_Organisateur());
				pst.setInt(7, tournoi.getId_Mode().getIdMode());
				pst.setString(8, tournoi.getEtat().getValue());
				pst.executeUpdate();
				pst.close();

			} catch (SQLException ex) {
				ex.printStackTrace();
				return -1;
			}
			return 1;
		}

		public static int modifierTournoi(Tournoi t) {
			Connection connex = Connexion.connexion();
			PreparedStatement pst;
			try {


				if (!verifierPresenceTournoi(t)) {
					return -1;
				}
				pst = connex.prepareStatement("update LMN3783A.sae_tournoi set nom = ?, datetournoi = ?, championnat = ?, notoriete = ?, id_organisateur= ?,id_mode = ?, etat=? where id_tournoi = ?" );
				pst.setString(1, t.getNom());
				pst.setDate(2, t.getDateTournoi());
				pst.setInt(3, t.getChampionnat());
				pst.setInt(4,t.getNotoriete());
				pst.setInt(5,t.getId_Organisateur());
				pst.setInt(6, t.getId_Mode().getIdMode());
				pst.setString(7, t.getEtat().getValue());
				pst.setInt(8, t.getId());
				pst.executeUpdate();

				pst.close();
				
			} catch (SQLException ex) {
				ex.printStackTrace();
				return -1;
			}
			return 1;
		}

		public int actualiserEtat() {
			Connection connex = Connexion.connexion();

			try {
				if (!verifierPresenceTournoi(this)) {
					return -1;
				}

				PreparedStatement pst = connex.prepareStatement("UPDATE LMN3783A.sae_Tournoi set ETAT = ? where id_tournoi = ?");
				pst.setString(1,this.getEtat().getValue());
				pst.setInt(2,this.getId());
				return pst.executeUpdate();

			}
			catch (Exception e) {
				e.printStackTrace();
				return -1;
			}

		}

		public static int supprimerTournoi(Tournoi t) {
			Connection connex = Connexion.connexion();
			PreparedStatement pst;
			ResultSet rs;
			
			try {

				if (verifierPresenceTournoi(t)) {
					return -1;
				}
				
				pst = connex.prepareStatement("delete from LMN3783A.sae_tournoi where nom = ? and datetournoi= ?" );
				pst.setString(1, t.getNom());
				pst.setDate(2, t.getDateTournoi());
				pst.executeUpdate();
				pst.close();
				
			} catch (SQLException ex) {
				ex.printStackTrace(); 
				return -1; 
			}
			return 1;
		}

	@Override
	public String toString() {
		String res = "";
		
		res += this.nom;
		switch(this.etat) {
			case INSC:{
				res += ", tournoi en phase d'inscription";
				break;
			}
			case ENC:{
				res+= ", tournoi en cours";
				break;
			}
			case FINI:{
				res+= ", tournoi fini"; 
				break;
			}
		}
		
		return res;
	}

	//Ajoute les points à la fin d'un tournoi
	public void ajouterPoints () throws  Exception {
		if (!phaseElim.estFinie()) {
			throw new Exception("le tournoi n'est pas fini");
		}
		System.out.println("AJOUT DE POINTS");
		Equipe[] classement = phaseElim.getClassement();
		classement[0].addPoints(PointsClassement.PREMIER.points*notoriete);
		classement[1].addPoints(PointsClassement.DEUXIEME.points*notoriete);
		classement[2].addPoints(PointsClassement.TROISIEME.points*notoriete);
		classement[3].addPoints(PointsClassement.QUATRIEME.points*notoriete);
		for (Match m :
				phaseElim.getMatchs()) {
			m.getWinner().addPoints(5);
		}
	}

	private Map<Equipe,Integer[]> getVictoiresPhase (Phase phase) {
		Map<Equipe,Integer[]> res = new HashMap<>();
		if (phase.getMatchs() != null) {
			for (Match m : phase.getMatchs()) {
				Equipe gagnant = m.getWinner();
				Equipe perdant = m.getLoser();
				if (gagnant == null) {
					continue;
				}
				if (!res.containsKey(gagnant) ) {
					res.put(gagnant, new Integer[]{0,0});
				}
				if (!res.containsKey(perdant) ) {
					res.put(perdant, new Integer[]{0,0});
				}
					res.get(gagnant)[0]++;
					res.get(perdant)[1]++;

			}
		}
			return res;
	}

	public Map<Equipe,Integer[]> getVictoires() {
		Map<Equipe,Integer[]> welim = getVictoiresPhase(this.phaseElim);
		Map<Equipe,Integer[]> res = getVictoiresPhase(this.phasePoule);
		for (Equipe equipe:
			 welim.keySet()) {
			Equipe equipetest = res.keySet().stream().filter((e) -> e.getId() == equipe.getId()).findFirst().orElse(null);
			if (equipetest == null) {
				continue;
			}
			System.out.println("e test " + equipetest);
			System.out.println("e en cours " + equipe);
			System.out.println(equipetest.getNom()+" Poules " + res.get(equipetest)[0] + "ELIM "+ welim.get(equipe)[0]);
			res.get(equipetest)[0] += welim.get(equipe)[0];
			System.out.print("Victoires totales " + equipe.getNom() + " "+ res.get(equipetest)[0]);
			res.get(equipetest)[1] += welim.get(equipe)[1];
			System.out.println("Defaites " + res.get(equipetest)[1]);
		}
		return res;
	}
	
	public static List<Tournoi> getAllTournois() {
		Connection connex = Connexion.connexion();
		Statement st;
		ResultSet rs;
		List<Tournoi> tournois = new ArrayList<>();
		Tournoi t = null;
		
		try {
			
			st = connex.createStatement();
			rs = st.executeQuery("select id_tournoi,etat, nom, datetournoi, championnat, notoriete, id_organisateur, id_mode from LMN3783A.sae_tournoi order by nom");
			while (rs.next()) {
				ETAT etat = ETAT.valueOf(rs.getString("etat"));
				t = new Tournoi(rs.getString("nom"), rs.getDate("datetournoi"), rs.getInt("championnat"), rs.getInt("notoriete"), rs.getInt("id_organisateur"), ModeDeJeu.getModeDeJeuFromId(rs.getInt("id_mode")),etat);
				t.setId(rs.getInt(1));
				tournois.add(t);
			}
			
			rs.close();
			st.close();
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return tournois;
	}

	public void getPhasesfromID() throws Exception {

		Connection connex = Connexion.connexion();
		PreparedStatement st;
		ResultSet rs;

		if (id <0) {
			throw new Exception("Objet non relié");
		}

		if (etat == ETAT.INSC) {
			return;
		}

		try {
			st = connex.prepareStatement("SELECT id_phase,elim from lmn3783a.sae_phase where id_tournoi = ? and elim = ?");
			st.setInt(1,this.id);
			st.setInt(2,0);
			rs = st.executeQuery();
			if (rs.next()) {
				PhaseDePoule phaseDePoule = new PhaseDePoule(this);
				phaseDePoule.setId(rs.getInt(1));
				this.phasePoule =  phaseDePoule;
				phaseDePoule.getMatchsFromID();
			}
			st.setInt(2,1);
			rs = st.executeQuery();
			if ( this.phasePoule != null && rs.next() ) {
				System.out.println("Coucou 2 :::" + this.phasePoule);
				PhaseFinale phaseFinale = new PhaseFinale(this,this.phasePoule);
				phaseFinale.setId(rs.getInt("id_phase"));
				phaseFinale.getMatchsFromID();
				phaseFinale.setFinalefromMatchs();
				this.phaseElim = phaseFinale;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public static List<Tournoi> getTournoiWithFilter(ETAT e){
		Connection connex = Connexion.connexion();
		PreparedStatement pst;
		ResultSet rs;
		List<Tournoi> tournois = new ArrayList<>();
		Tournoi t = null;
		
		try {
			
			pst = connex.prepareStatement("select id_tournoi,etat, nom, datetournoi, championnat, notoriete, id_organisateur, id_mode from LMN3783A.sae_tournoi where etat = ? order by nom");
			pst.setString(1, e.getValue());
			rs = pst.executeQuery();
			while (rs.next()) {
				ETAT etat = ETAT.valueOf(rs.getString("etat"));
				t = new Tournoi(rs.getString("nom"), rs.getDate("datetournoi"), rs.getInt("championnat"), rs.getInt("notoriete"), rs.getInt("id_organisateur"), ModeDeJeu.getModeDeJeuFromId(rs.getInt("id_mode")),etat);
				t.setId(rs.getInt(1));
				tournois.add(t);
			}
			
			rs.close();
			pst.close();
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return tournois;
	}

	public ETAT getEtat() {
		return etat;
	}

	public void setEtat(ETAT etat) {
		this.etat = etat;
	}
	
}
