package Object;

import Application.Connexion;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Phase {
    private Tournoi tournoi;
    protected List<Match> matchs;
    private int id;

    public abstract void genererMatchs();

    public abstract boolean isElim();

    public Tournoi getTournoi() {
        return tournoi;
    }

    public Phase(Tournoi tournoi) {
        this.tournoi = tournoi;
        this.matchs = new ArrayList<>();
        this.id =-1;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Match> getMatchs() {
        return matchs;
    }

    public Match getMatch(int i) {
        return matchs.get(i);
    }

    public int getId() {
        return id;
    }

    public static int getLastId() {
        Connection connex = Connexion.connexion();
        Statement st;
        ResultSet rs;
        int r = 0;

        try {

            st = connex.createStatement();
            rs = st.executeQuery("select max(id_phase) from LMN3783A.sae_phase");
            rs.next();
            r = rs.getInt(1);

            rs.close();
            st.close();

        } catch (Exception ee) {
            ee.printStackTrace();
        }
        return r;
    }

    public static int enregistrerPhase(Phase p) {
        Connection connex = Connexion.connexion();
        PreparedStatement pst;
        try {
            if (p.getId() == -1) {
                p.setId(p.getLastId()+1);
            }

            pst = connex.prepareStatement("insert into LMN3783A.sae_phase(id_phase, elim, id_tournoi) values(?,?,?)");
            pst.setInt(1, p.getId());
            pst.setInt(2, (p.isElim()) ? 1 : 0);
            pst.setInt(3, p.getTournoi().getId());
            if (pst.executeUpdate() == 0) {
                System.out.println("Phase non insere");
                pst.close();
                return -1;
            };

            p.enregistrerMatchs();

        } catch (SQLException ex) {
            ex.printStackTrace();
            return -1;
        }
        return 1;

    }

    protected void enregistrerMatchs() {
        for (Match m : matchs) {
            Match.enregistrermatch(m);
        }
    };

    //Verifie que tous les matchs à jouer ont un gagnant
    public abstract boolean matchsFinis() ;

    public void getMatchsFromID () throws  Exception {
        if (this.id < 0) {
            throw  new Exception("Objet non relié");
        }

        Map<Integer,Equipe> mapequipe = new HashMap<>();

        Connection connex = Connexion.connexion();
        PreparedStatement pst = connex.prepareStatement("SELECT ID_MATCH, DATEMATCH, ID_EQUIPE1, ID_EQUIPE2, ID_GAGNANT FROM lmn3783a.sae_match where id_phase = ?");
        pst.setInt(1,id);

        Equipe equipe1;
        Equipe equipe2;

        //Dedoublonage
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            equipe1 = Equipe.getEquipeFromId(rs.getInt("id_equipe1"));
            int idequipe1 = equipe1.getId();
            if (mapequipe.containsKey(idequipe1)) {
                 equipe1 = mapequipe.get(idequipe1);
            }
            else {
                mapequipe.put(idequipe1,equipe1);
            }

            equipe2 = Equipe.getEquipeFromId(rs.getInt("id_equipe2"));
            int idequipe2 = equipe2.getId();
            if (mapequipe.containsKey(idequipe2)) {
                equipe2 = mapequipe.get(idequipe2);
            }
            else {
                mapequipe.put(idequipe2,equipe2);
            }
            Match matchSelect = new Match(
                    rs.getDate("DATEMATCH"),
                    equipe1,
                    equipe2,
                    this);
            matchSelect.setId(rs.getInt(1));
            this.matchs.add(matchSelect);
            if (rs.getObject("id_gagnant") == null) {
               continue;
            }
            if (rs.getInt("id_gagnant")  == matchSelect.getEquipe1().getId()) {
                matchSelect.setWinner(1);
            }
            else {
                matchSelect.setWinner(2);
            }
        }

    }

    public abstract String getType ();

    @Override
    public String toString() {
        return "Phase{" +
                "elim=" + isElim() +
                ", matchs=\n" + getMatchs() +
                ", id =\n" +id +
                ", id tournoi = " + tournoi.getId() +
                '}';
    }
}
