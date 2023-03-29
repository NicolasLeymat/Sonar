package Application;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Connexion {
    private static Connection connexion;
    
    private Connexion() {
    	
    }

    public static synchronized Connection connexion() {
        if (connexion == null) {
            return creerconnexion();
        }
        return connexion;
    }

    private static Connection creerconnexion() {
    String fichierconfig = "./src/Application/config.properties";
    String url = null;
    String mdp = null;
    String identifiant = null;
    try
    {
        FileInputStream identifiants = new FileInputStream(fichierconfig);
        Properties props = new Properties();
        props.load(identifiants);
        identifiants.close();
          identifiant = props.getProperty("DB_USER");
          mdp = props.getProperty("DB_PASS");
          url = props.getProperty("DB_URL");

    }
    catch (Exception e) {
        e.printStackTrace();
    }
    try {
        DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
        connexion =DriverManager.getConnection(url,identifiant,mdp);
        return connexion;
    }
    catch (SQLException e) {
        e.printStackTrace();
    }



    return null;
    }

    public static void closeConnexion() throws SQLException {
        if (connexion != null) {
            connexion.close();
            connexion = null;
        }
    }
}
