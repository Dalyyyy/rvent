package org.services;

import org.entities.Team;
import org.util.RventDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TeamService implements ITeamService {
    private final Connection connection;
    public TeamService() {connection = RventDB.getInstance().getConnection;}

    public void createTeamAndAddUser(Team team, int userId) {
        try {
            int teamId = addTeam(team); // This should get the ID from addTeam method
            if(teamId > 0) {
                assignUserToTeam(userId, teamId);
            } else {
                // Handle the case where teamId is not correctly generated
                throw new SQLException("Team ID was not generated.");
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Make sure to log or handle the exception
        }
    }



    public int addTeam(Team team) throws SQLException {
        String sql = "INSERT INTO team (teamName) VALUES (?)";
        int teamId = 0;

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, team.getTeamName());
            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating team failed, no rows affected.");
            }

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    teamId = generatedKeys.getInt(1); // This is the critical part
                } else {
                    throw new SQLException("Creating team failed, no ID obtained.");
                }
            }
        }
        return teamId; // Make sure this line is present to return the generated ID
    }


    public void assignUserToTeam(int userId, int teamId) throws SQLException {
        String sql = "UPDATE user SET team_id = ? WHERE id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, teamId);
            preparedStatement.setInt(2, userId);

            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Assigning user to team failed, no rows affected.");
            }
        }
    }


    @Override
    public void deleteTeam(int teamId) throws SQLException {
        // First, remove the team association from all users in this team
        String sqlUpdateUsers = "UPDATE user SET team_id = NULL WHERE team_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlUpdateUsers)) {
            preparedStatement.setInt(1, teamId);
            preparedStatement.executeUpdate();
        }

        // Then, delete the team
        String sqlDeleteTeam = "DELETE FROM team WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlDeleteTeam)) {
            preparedStatement.setInt(1, teamId);
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected == 0) {
                throw new SQLException("No team found with the specified ID, or deletion was unsuccessful.");
            }
        }
    }

    public void updateTeam(int teamId, String newTeamName) throws SQLException {
        String sqlUpdateTeam = "UPDATE team SET teamName = ? WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlUpdateTeam)) {
            preparedStatement.setString(1, newTeamName);
            preparedStatement.setInt(2, teamId);
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public List<Team> getAllTeams() throws SQLException {
        List<Team> teams = new ArrayList<>();
        // This SQL combines team information with a count of associated users
        String sql = "SELECT t.id, t.teamName, COUNT(m.id) AS membersNbr " +
                "FROM team t LEFT JOIN user m ON t.id = m.team_id " +
                "GROUP BY t.id, t.teamName";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Team team = new Team();
                    team.setId(resultSet.getInt("id"));
                    team.setTeamName(resultSet.getString("teamName"));
                    team.setMembersNbr(resultSet.getInt("membersNbr")); // Assuming Team has a setMembersNbr() method
                    teams.add(team);

                    // Optionally, you can print out each team's information here
                    System.out.println("Team ID: " + team.getId() + ", Team Name: " + team.getTeamName() + ", Members Count: " + team.getMembersNbr());
                }
            }
        }
        return teams;
    }

    public static void main(String[] args) throws SQLException {
       TeamService teamService = new TeamService();
//           teamService.addTeam(new Team("eeee"));
//      teamService.updateTeam(1,"rrr");
//        teamService.createTeamAndAddUser(new Team("my team"),3);
//        teamService.getAllTeams();


    }

}

