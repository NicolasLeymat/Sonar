package IHM.info;

import javax.swing.table.AbstractTableModel;
import Object.Tournoi;
import Object.Equipe;

import java.util.Arrays;
import java.util.Map;

public class TableauClassementTournoi extends AbstractTableModel {
    private String[] columnNames = {"#", "Equipe", "V","D"};
    private Tournoi tournoi;
    private Equipe[] equipes;

    private Map<Equipe,Integer[]> victoires;

    public TableauClassementTournoi(Tournoi tournoi) {
        try {
            equipes = tournoi.getPhaseElim().getClassement();
        }
        catch (Exception e){
            e.printStackTrace();
            return;
        }
        victoires = tournoi.getVictoires();
        this.tournoi = tournoi;
    }

    @Override
    public int getRowCount() {
        try {
            return tournoi.getPhaseElim().getClassement().length;
        }
        catch (Exception e) {
            return 0;
        }

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
        Equipe equipet = victoires.keySet().stream().filter((e) -> e.getId() == equipes[rowIndex].getId()).findFirst().orElse(null);
        switch (columnIndex) {
            case 0:
                return rowIndex +1;
            case 1:
                return equipes[rowIndex].getNom();
            case 2:
                return victoires.get(equipet)[0];
            case 3:
                return victoires.get(equipet)[1];
            default:
                return null;
        }
    }
}
