package org.entities;


import java.util.List;

public class Team {
    private int id;
    private int membersNbr;
    private String teamName;
    private List<User> users;
    private Activity activities;

    public Team(int id) {
        this.id = id;
    }

    public Team(int id, int membersNbr, String teamName, List<User> users, Activity activities) {
        this.id = id;
        this.membersNbr = membersNbr;
        this.teamName = teamName;
        this.users = users;
        this.activities = activities;
    }

    public Team() {

    }

    public Team(String teamName) {
        this.teamName = teamName;
    }

    public Team(String teamName, int id) {
        this.teamName = teamName;
        this.id=id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMembersNbr() {
        return membersNbr;
    }

    public void setMembersNbr(int membersNbr) {
        this.membersNbr = membersNbr;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public Activity getActivities() {
        return activities;
    }

    public void setActivities(Activity activities) {
        this.activities = activities;
    }

    @Override
    public String toString() {
        return "Team{" +
                "id=" + id +
                ", membersNbr=" + membersNbr +
                ", teamName='" + teamName + '\'' +
                ", users=" + users +
                ", activities=" + activities +
                '}';
    }
}

