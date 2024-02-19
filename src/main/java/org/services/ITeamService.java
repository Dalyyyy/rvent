package org.services;

import org.entities.Team;

import java.sql.SQLException;
import java.util.List;

public interface ITeamService<U> {
    public void addTeam(Team team, int userId) throws SQLException;
    public void updateTeam(int teamId, String newTeamName) throws SQLException;

    void addUserToTeam(int user, int teamId) throws SQLException;

    public void deleteTeam(int teamId) throws SQLException;
    public List<Team> getAllTeams() throws SQLException;
}
