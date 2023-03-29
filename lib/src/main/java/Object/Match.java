package Object;

import Application.Connexion;

import java.sql.*;

public class Match {
	private Equipe equipe1;
	private Equipe equipe2;
	private Equipe winner;
	private Phase phase;
	private int id;
	private Date date;

	public Match(Date date, Equipe equipe1, Equipe equipe2, Phase phase) {
		this.setDate(date);
		this.equipe1 = equipe1;
		this.equipe2 = equipe2;
		this.phase = phase;
		this.winner = null;
		this.id = -1;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public Equipe getEquipe1() {
		return equipe1;
	}

	public Equipe getEquipe2() {
		return equipe2;
	}

	public Phase getPhase() {
		return this.phase;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void setWinner(int num) throws IllegalArgumentException {
		if (num < 1 || num > 2) {
			throw new IllegalArgumentException();
		}
		if (num == 1 ) {
			winner = equipe1;
		}
		else {
			winner = equipe2;
		}
	}

	public Equipe getWinner() {
		return winner;
	}

	public Equipe getLoser () {
		if (winner == equipe1) {
			return equipe2;
		}
		else if (winner == equipe2) {
			return equipe1;
		}
		else  {
			return null ;
		}
	}

	public static int getLastId() {
		Connection ct = Connexion.connexion();
		Statement st;
		ResultSet rs;
		int r = 0;
		try {
			st = ct.createStatement();
			rs = st.executeQuery("Select max(id_match) as id from LMN3783A.sae_match");
			while (rs.next()) {
				r = rs.getInt("id");
			}
		} catch (SQLException ee) {
			ee.printStackTrace();
		}
		return r;
	}

	public static int enregistrermatch(Match m) {
		Connection connex = Connexion.connexion();
		PreparedStatement pst;
		PreparedStatement pst2;
		
		try {
			if (m.getId() == -1) {
				m.setId(Match.getLastId()+1);
			}
			pst= connex.prepareStatement("INSERT INTO LMN3783A.SAE_MATCH(ID_MATCH,DATEMATCH,ID_PHASE,ID_EQUIPE1,ID_EQUIPE2) VALUES (?,?,?,?,?)");
			pst.setInt(1, m.getId());
			pst.setDate(2, m.date);
			pst.setInt(3, m.phase.getId());
			pst.setInt(4, m.getEquipe1().getId());
			pst.setInt(5, m.getEquipe2().getId());
			pst.executeUpdate();
			
			pst.close();
			
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
		return 1;
	}
	
	public static int validerVainqueur(Match match) {
		Connection ct = Connexion.connexion();
		if (match.id == 0) {
			throw new IllegalArgumentException("match inexistant");
		}
		if (match.getWinner() == null) {
			return 2;
		}
		try {
			PreparedStatement ps = ct.prepareStatement("UPDATE LMN3783A.SAE_MATCH SET ID_GAGNANT = ? WHERE ID_MATCH = ?");
			ps.setInt(1, match.getWinner().getId());
			ps.setInt(2, match.id);
			ps.executeUpdate();
			return 0;
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

	@Override
	public String toString() {
		return "Match{" +
				"equipe1=" + equipe1.getNom() +
				", equipe2=" + equipe2.getNom() +
				", winner=" + winner +
				", id=" + id +
				", date=" + date +
				"}\n";
	}
}
