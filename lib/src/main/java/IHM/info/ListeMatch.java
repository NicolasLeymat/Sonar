package IHM.info;

import java.util.List;
import Object.Match;
import Object.Equipe;

import javax.swing.*;

public class ListeMatch extends AbstractListModel {
    private List<Match> matchs;
    private static final int TAILLESEP = 25;
    public ListeMatch(List<Match> matchs) {
        this.matchs = matchs;
    }

    @Override
    public int getSize() {
        return matchs.size();
    }

    @Override
    public Object getElementAt(int index) {
        Match match = matchs.get(index);
        String stringJ1="";
        String stringJ2="";
        if (match.getWinner() == match.getEquipe1()) {
            stringJ1 +="👑 ";
        }
        if (match.getWinner() == match.getEquipe2()) {
            stringJ2 +="👑 ";
        }
        stringJ1 += match.getEquipe1().getNom();
        stringJ2 += match.getEquipe2().getNom();
        String res =stringJ1;
        int separateur = TAILLESEP -stringJ1.length();
        for (int i = 0; i < separateur; i++) {
            res+=" ";
        }
        res+="VS";

        for (int i = 0; i < 15; i++) {
            res+=" ";
        }
        res+=stringJ2;
        return res;
    }

    public Object getValueAt(int ligne) {
        return matchs.get(ligne);
    }
}
