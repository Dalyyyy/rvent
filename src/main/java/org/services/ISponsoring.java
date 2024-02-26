package org.services;
import javafx.collections.ObservableList;
import org.entities.Sponsoring;

import java.sql.SQLException;

public interface ISponsoring <Sponsoring>{
    void ajouter(Sponsoring sponsoring) throws SQLException;
    void modifier(Sponsoring sponsoring) throws SQLException;
    void supprimer(int id) throws SQLException;
    ObservableList<org.entities.Sponsoring> afficher() throws SQLException;
   // ObservableList<org.entities.Sponsoring> search(String name) throws SQLException;
    //public ObservableList<org.entities.Sponsoring> searchU(String name) throws SQLException;
    //public ObservableList<org.entities.Sponsoring> getAccountByAccountId(int AccountId) throws SQLException ;
    //public ObservableList<org.entities.Sponsoring> getAccountByRole(Enum role, int count) throws SQLException;
    //public org.entities.Sponsoring authenticate(String email, String password) throws SQLException;


}
