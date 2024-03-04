package org.entities;


import java.util.List;
public class Enterprise {
    private int id;
    private String fullName;
    private String description;
    private String email;
    private String password;
    private int phoneNumber;
    private List<Event> events;
    private List<sponsoringRequest> sponsoringRequests;

    public Enterprise() {

    }

    public Enterprise(int id, String fullName, String description, String email, String password, List<Event> events, List<sponsoringRequest> sponsoringRequests) {
        this.id = id;
        this.fullName = fullName;
        this.description = description;
        this.email = email;
        this.password = password;
        this.events = events;
        this.sponsoringRequests = sponsoringRequests;
    }

    public Enterprise( String fullName, String description, String email, String password) {
        this.fullName = fullName;
        this.description = description;
        this.email = email;
        this.password = password;
    }

    public Enterprise(int id, String fullName ,String description, String email, String password) {
        this.id = id;
        this.fullName = fullName;
        this.description = description;
        this.email = email;
        this.password = password;
    }

    public Enterprise(String enterpriseName, String enterpriseEmail, String enterprisePassword) {
        this.fullName=enterpriseName;
        this.email=enterpriseEmail;
        this.password=enterprisePassword;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEmail() {
        return email;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
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

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    public List<sponsoringRequest> getSponsoringRequests() {
        return sponsoringRequests;
    }

    public void setSponsoringRequests(List<sponsoringRequest> sponsoringRequests) {
        this.sponsoringRequests = sponsoringRequests;
    }

    @Override
    public String toString() {
        return "Enterprise{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", description='" + description + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", events=" + events +
                ", sponsoringRequests=" + sponsoringRequests +
                '}';
    }
}
