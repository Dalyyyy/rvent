package org.services;
import javafx.collections.ObservableList;
import org.entities.Sponsoring;

import java.sql.SQLException;

public interface ISponsoring <Sponsoring>{
    void ajouter(Sponsoring sponsoring) throws SQLException;
    void modifier(Sponsoring sponsoring) throws SQLException;
    void supprimer(int id) throws SQLException;
    ObservableList<org.entities.Sponsoring> afficher() throws SQLException;



}
