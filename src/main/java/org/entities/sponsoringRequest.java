package org.entities;


import java.util.Date;
public class sponsoringRequest {
    private int id;
    private int budget;
    private String description;
    private boolean status;
    private Date date;
    private Enterprise enterprise;

    public sponsoringRequest(int id, int budget, String description, boolean status, Date date, Enterprise enterprise) {
        this.id = id;
        this.budget = budget;
        this.description = description;
        this.status = status;
        this.date = date;
        this.enterprise = enterprise;
    }

    public sponsoringRequest() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBudget() {
        return budget;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Enterprise getEnterprise() {
        return enterprise;
    }

    public void setEnterprise(Enterprise enterprise) {
        this.enterprise = enterprise;
    }

    @Override
    public String toString() {
        return "sponsoringRequest{" +
                "id=" + id +
                ", budget=" + budget +
                ", description='" + description + '\'' +
                ", status=" + status +
                ", date=" + date +
                ", enterprise=" + enterprise +
                '}';
    }
}
