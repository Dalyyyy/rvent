package org.services;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.entities.Sponsoring;
import org.util.RventDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SponService implements ISponsoring <Sponsoring>{
    private final Connection connection;
    public SponService() {connection = RventDB.getInstance().getConnection;
    }
    @Override
    public void ajouter(Sponsoring sponsoring) throws SQLException {
        String query = "INSERT INTO sponsoring (nom, prenom, domaine, adresse, email ,tetab, numero ,description,nom_etab) VALUES (?, ?, ?, ?, ? ,? ,? ,? ,?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, sponsoring.getNom());
            preparedStatement.setString(2,sponsoring.getPrenom());
            preparedStatement.setString(3, sponsoring.getDomaine());
            preparedStatement.setString(4, sponsoring.getAdresse());
            preparedStatement.setString(5, sponsoring.getEmail());
            preparedStatement.setString(6, String.valueOf(sponsoring.getTetab()));
            preparedStatement.setInt(7, sponsoring.getNumero());
            preparedStatement.setString(8, sponsoring.getDescription());
            preparedStatement.setString(9, sponsoring.getNom_etab());



            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Error adding account: " + e.getMessage());
        }

    }

    @Override
    public void modifier(Sponsoring sponsoring) throws SQLException {
        String query = "UPDATE sponsoring SET nom=?, prenom=?, domaine=?, adresse=?, email=?, tetab=?,numero=?,description=?,nom_etab=?  WHERE id=?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, sponsoring.getNom());
            preparedStatement.setString(2,sponsoring.getPrenom());
            preparedStatement.setString(3, sponsoring.getDomaine());
            preparedStatement.setString(4, sponsoring.getAdresse());
            preparedStatement.setString(5, sponsoring.getEmail());
            preparedStatement.setString(6, String.valueOf(sponsoring.getTetab()));
            preparedStatement.setInt(7, sponsoring.getNumero());
            preparedStatement.setString(8, sponsoring.getDescription());
            preparedStatement.setString(9, sponsoring.getNom_etab());
            preparedStatement.setInt(10, sponsoring.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Error editing sponsor: " + e.getMessage());
        }
    }

    @Override
    public void supprimer(int id) throws SQLException {
        String query = "DELETE FROM sponsoring WHERE id=?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Error deleting SponsoringRequest: " + e.getMessage());
        }
    }

    @Override
    public ObservableList<Sponsoring> afficher() throws SQLException {
        ObservableList<Sponsoring> sponsoringList = FXCollections.observableArrayList();
        String query = "SELECT * FROM sponsoring"; // Assurez-vous que cette requête SQL est correcte selon votre schéma de base de données

        try (PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                Sponsoring sponsoring = new Sponsoring();
                sponsoring.setId(resultSet.getInt("id"));
                sponsoring.setNom(resultSet.getString("nom"));
                sponsoring.setNom_etab(resultSet.getString("nom_etab"));
                sponsoring.setPrenom(resultSet.getString("prenom"));
                sponsoring.setDomaine(resultSet.getString("domaine"));
                sponsoring.setAdresse(resultSet.getString("adresse"));
                sponsoring.setEmail(resultSet.getString("email"));
                //sponsoring.setTetab(Sponsoring.Tetab.fromString(resultSet.getString("tetab")));
                sponsoring.setNumero(resultSet.getInt("numero"));
                sponsoring.setDescription(resultSet.getString("description"));
                sponsoringList.add(sponsoring);
            }
        }
        return sponsoringList;    }

}
