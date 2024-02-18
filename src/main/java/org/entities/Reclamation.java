package org.entities;

public class Reclamation {
    private int id ;
    private String title;
    private String description;
    private String from;
    private String to;
    private User user;
    private Event event;

    public Reclamation() {
    }

    public Reclamation(int id, String title, String description, String from, String to, User user, Event event) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.from = from;
        this.to = to;
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

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
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
                ", from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", user=" + user +
                ", event=" + event +
                '}';
    }
}
