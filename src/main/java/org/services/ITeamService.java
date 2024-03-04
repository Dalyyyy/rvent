package org.services;

import org.entities.Team;

import java.sql.SQLException;
import java.util.List;

public interface ITeamService<U> {
    int addTeam(Team team) throws SQLException;


    public void deleteTeam(int teamId) throws SQLException;
    public List<Team> getAllTeams() throws SQLException;
}
