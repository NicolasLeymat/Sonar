package IHM.info;

import javax.swing.*;
import Object.Joueur;

import java.util.List;


public class ListeJoueur<T> extends AbstractListModel {
    private List<T> joueurs;

    public ListeJoueur(List<T> joueurs) {
        this.joueurs = joueurs;
    }

    @Override
    public int getSize() {
        return joueurs.size();
    }

    @Override
    public T getElementAt(int index) {
        return (T) joueurs.get(index);
    }


}
