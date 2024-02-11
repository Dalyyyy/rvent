package org.example.entities;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class User {
    private int id;
    private String name;
    private String familyName;
    private String email;
    private String password;
    private LocalDate dateBirth;
    private int reservationNbr;

    public User(String name, String familyName, String email, String password, LocalDate dateBirth) {
        this.name = name;
        this.familyName = familyName;
        this.email = email;
        this.password = password;
        this.dateBirth = dateBirth;
    }

    public User(int id, String name, String familyName, String email, String password, LocalDate dateBirth, int reservationNbr) {
        this.id = id;
        this.name = name;
        this.familyName = familyName;
        this.email = email;
        this.password = password;
        this.dateBirth = dateBirth;
        this.reservationNbr = reservationNbr;
    }

    public User(String name, String familyName, String email, String password, LocalDate dateBirth, int reservationNbr) {
        this.name = name;
        this.familyName = familyName;
        this.email = email;
        this.password = password;
        this.dateBirth = dateBirth;
        this.reservationNbr = reservationNbr;
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
        return reservationNbr;
    }

    public void setReservationNbr(int reservationNbr) {
        this.reservationNbr = reservationNbr;
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
                '}';
    }

}
