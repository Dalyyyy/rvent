package org.services;

import org.entities.Team;
import org.util.RventDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TeamService implements ITeamService {
    private final Connection connection;
    public TeamService() {connection = RventDB.getInstance().getConnection;}
    @Override
    public void addTeam(Team team, int userId) throws SQLException {
        String sql = "INSERT INTO team (teamName) VALUES (?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, team.getTeamName());
            preparedStatement.executeUpdate();
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int teamId = generatedKeys.getInt(1);
                    addUserToTeam(userId, teamId);
                } else {
                    throw new SQLException("Failed to retrieve the auto-generated team ID.");
                }
            }
        }
    }
    @Override
    public void updateTeam(int teamId, String newTeamName) throws SQLException {
        String sqlUpdateTeam = "UPDATE team SET teamName = ? WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlUpdateTeam)) {
            preparedStatement.setString(1, newTeamName);
            preparedStatement.setInt(2, teamId);
            preparedStatement.executeUpdate();
        }

    }
    @Override
    public void addUserToTeam(int user, int teamId) throws SQLException {
        String checkSql = "SELECT COUNT(*) FROM user_team WHERE user_id = ?";
        try (PreparedStatement checkStatement = connection.prepareStatement(checkSql)) {
            checkStatement.setInt(1, user);
            try (ResultSet resultSet = checkStatement.executeQuery()) {
                if (resultSet.next() && resultSet.getInt(1) > 0) {
                    throw new IllegalArgumentException("User is already assigned to another team.");
                }
            }
        }
        String sql = "INSERT INTO user_team (user_id, team_id) VALUES (?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, user);
            preparedStatement.setInt(2, teamId);
            preparedStatement.executeUpdate();
        }
    }
    @Override
    public void deleteTeam(int teamId) throws SQLException {
        // Delete references from user_team table
        String sqlDeleteReferences = "DELETE FROM user_team WHERE team_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlDeleteReferences)) {
            preparedStatement.setInt(1, teamId);
            preparedStatement.executeUpdate();
        }

        String sqlDeleteTeam = "DELETE FROM team WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlDeleteTeam)) {
            preparedStatement.setInt(1, teamId);
            preparedStatement.executeUpdate();
        }
    }
    @Override
    public List<Team> getAllTeams() throws SQLException {
        List<Team> teams = new ArrayList<>();
        String sql = "SELECT * FROM team";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Team team = new Team();
                    team.setId(resultSet.getInt("id"));
                    team.setMembersNbr(resultSet.getInt("membersNbr"));
                    team.setTeamName(resultSet.getString("teamName"));
                    teams.add(team);
                }
            }
        }
        return teams;
    }
}

