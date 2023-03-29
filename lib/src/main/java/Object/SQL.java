package Object;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQL {

	// Retourne la dernière id de la table d'un objet
	// Pas testé
	public static int getLastId(Connection connex, Object o) {
		Statement st;
		ResultSet rs;
		int res = 0;
		try {
			// On peut faire de la concatenation de String car le nom de la table vient du
			// code et pas de l'utilisateur
			st = connex.createStatement();
			rs = st.executeQuery("select max(id_" + o.getClass().getSimpleName() + ") as id from LMN3783A.sae_"
					+ o.getClass().getSimpleName());
			rs.next();
			res = rs.getInt("id");

		} catch (SQLException ee) {
			ee.printStackTrace();
		}
		return res;
	}

}
