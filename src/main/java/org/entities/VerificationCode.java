package org.entities;

import java.time.LocalDate;

public class VerificationCode {
    private int id;
    private LocalDate creationDate;
    private String code;
    private boolean isValid;
    private User user;

    public VerificationCode(int id, LocalDate creationDate, String code, boolean isValid) {
        this.id = id;
        this.creationDate = creationDate;
        this.code = code;
        this.isValid = isValid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }
    public void setCode(String code){
        this.code = code ;
    }

    public String getCode(){
        return code;
    }

    public boolean isValid() {
        return isValid;
    }

    public void setValid(boolean valid) {
        isValid = valid;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
