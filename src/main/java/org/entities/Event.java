package org.entities;

import java.util.List;

public class Event {
    private int id;
    private boolean full;
    private String eventName;
    private String description;
    private boolean status;
    private String enterpriseName;

    private int maxParticipantNbr;
    private List<Reclamation> reclamations;
    private List<Reservation> reservations;
    private Planning planning;
    private MediaProfile mediaProfile;
    private List<Room> rooms;
    private CheckListRSE checkListRSE;
    private Enterprise enterprise;
    private List<Sponsoring> sponsoringRequests;



    public Event(int id, boolean full, String eventName, String description, boolean status, String enterpriseName, int maxParticipantNbr) {
        this.id = id;
        this.full = full;
        this.eventName = eventName;
        this.description = description;
        this.status = status;
        this.enterpriseName = enterpriseName;
        this.maxParticipantNbr = maxParticipantNbr;
    }

    public Event() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void addSponsoringRequest(Sponsoring request) {
        sponsoringRequests.add(request);
    }


    public boolean isFull() {
        return full;
    }

    public void setFull(boolean full) {
        this.full = full;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
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

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }

    public int getMaxParticipantNbr() {
        return maxParticipantNbr;
    }

    public void setMaxParticipantNbr(int maxParticipantNbr) {
        this.maxParticipantNbr = maxParticipantNbr;
    }
}
