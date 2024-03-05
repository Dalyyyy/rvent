package org.entities;

public class Reclamation {
    private int id ;
    private String title;
    private String description;
    private User user;
    private Event event;

    public Reclamation() {
    }

    public Reclamation(int id, String title, String description, User user, Event event) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.user = user;
        this.event = event;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    @Override
    public String toString() {
        return "Reclamation{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", from='" + user + '\'' +
                ", to='" + event + '\'' +
                '}';
    }
}
