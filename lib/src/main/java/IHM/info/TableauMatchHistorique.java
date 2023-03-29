package IHM.info;
import Object.Match;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class TableauMatchHistorique extends AbstractTableModel {
    private String[] columnNames = {"Equipe 1", "Equipe 2","Date","Phase"};
    private List<Match> matchs;

    public TableauMatchHistorique(List<Match> matchs) {
        this.matchs = matchs;
    }

    @Override
    public int getRowCount() {
        return matchs.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    public String getColumnName(int col) {
        return columnNames[col];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Match match = matchs.get(rowIndex);
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
        switch (columnIndex) {
            case 0:
                return stringJ1;
            case 1:
                return stringJ2;
            case 2:
                return match.getDate();
            case 3:
                return match.getPhase().getType();
            default:
                return null;
        }
    }

    public Object getValueAt(int rowIndex) {
        return matchs.get(rowIndex);
    }
}
