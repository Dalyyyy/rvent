package org.entities;


import java.time.LocalDate;

import java.util.ArrayList;
import java.util.List;

public class User {
    private int id;
    private String name;
    private String familyName;
    private String email;
    private String password;
    private LocalDate dateBirth;
    private int phoneNumber;
    private int reservationNbr;
    private Role roleUser;
    private List<Reservation> reservations;
    private Team team;
    private List<Notification> notifications;
    private List<Reclamation> reclamations;
    private VerificationCode verificationCode;

    public User(String name, String familyName, String email, String password, LocalDate dateBirth,int phoneNumber) {
        this.name = name;
        this.familyName = familyName;
        this.email = email;
        this.password = password;
        this.dateBirth = dateBirth;
        this.phoneNumber = phoneNumber;
    }

    public User(int id, String name, String familyName, String email, String password, LocalDate dateBirth, Role roleUser, int reservationNbr, int phoneNumber) {
        this.id = id;
        this.name = name;
        this.familyName = familyName;
        this.email = email;
        this.password = password;
        this.dateBirth = dateBirth;
        this.reservationNbr = reservationNbr;
        this.roleUser = roleUser;
        this.phoneNumber = phoneNumber;
    }
    public User(int id, String name, String familyName, String email, String password, Role role, int reservationNbr, int phoneNumber) {
        this.id = id;
        this.name = name;
        this.familyName = familyName;
        this.email = email;
        this.password = password;
        this.roleUser = role;
        this.reservationNbr = reservationNbr;
        this.phoneNumber = phoneNumber;
    }

    public User() {
        this.reservations = new ArrayList<>();
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getDateBirth() {
        return dateBirth;
    }

    public void setDateBirth(LocalDate dateBirth) {
        this.dateBirth = dateBirth;
    }

    public int getReservationNbr() {
        return reservations.size();
    }

    public void setReservationNbr(int reservationNbr) {
        this.reservationNbr = reservationNbr;
    }

    public Role getRoleUser() {
        return roleUser;
    }

    public void setRoleUser(Role roleUser) {
        this.roleUser = roleUser;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public List<Notification> getNotifications() {
        return notifications;
    }

    public void setNotifications(List<Notification> notifications) {
        this.notifications = notifications;
    }

    public List<Reclamation> getReclamations() {
        return reclamations;
    }

    public void setReclamations(List<Reclamation> reclamations) {
        this.reclamations = reclamations;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public VerificationCode getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(VerificationCode verificationCode) {
        this.verificationCode = verificationCode;
    }


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", familyName='" + familyName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", birthDate=" + dateBirth +
                ", reservationNbr=" + reservationNbr +
                ", role="+roleUser+
                '}';
    }


}
