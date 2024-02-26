package org.entities;

import jakarta.persistence.*;

import java.sql.Time;
import java.util.Date;
import java.util.List;
public class Event {
    private int id;
    private String eventName;
    private String description;
    private boolean status;
    private Date date;
    private Time time;
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


    public Event() {
    }

    public Event(int id, String eventName, String description, boolean status, Date date, Time time, String enterpriseName, int maxParticipantNbr, List<Reclamation> reclamations, List<Reservation> reservations, Planning planning, MediaProfile mediaProfile, List<Room> rooms, CheckListRSE checkListRSE, Enterprise enterprise) {
        this.id = id;
        this.eventName = eventName;
        this.description = description;
        this.status = status;
        this.date = date;
        this.time = time;
        this.enterpriseName = enterpriseName;
        this.maxParticipantNbr = maxParticipantNbr;
        this.reclamations = reclamations;
        this.reservations = reservations;
        this.planning = planning;
        this.mediaProfile = mediaProfile;
        this.rooms = rooms;
        this.checkListRSE = checkListRSE;
        this.enterprise = enterprise;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
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

    public List<Reclamation> getReclamations() {
        return reclamations;
    }

    public void setReclamations(List<Reclamation> reclamations) {
        this.reclamations = reclamations;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    public Planning getPlanning() {
        return planning;
    }

    public void setPlanning(Planning planning) {
        this.planning = planning;
    }

    public MediaProfile getMediaProfile() {
        return mediaProfile;
    }

    public void setMediaProfile(MediaProfile mediaProfile) {
        this.mediaProfile = mediaProfile;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    public CheckListRSE getCheckListRSE() {
        return checkListRSE;
    }

    public void setCheckListRSE(CheckListRSE checkListRSE) {
        this.checkListRSE = checkListRSE;
    }

    public Enterprise getEnterprise() {
        return enterprise;
    }

    public void setEnterprise(Enterprise enterprise) {
        this.enterprise = enterprise;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", eventName='" + eventName + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                ", date=" + date +
                ", time=" + time +
                ", enterpriseName='" + enterpriseName + '\'' +
                ", maxParticipantNbr=" + maxParticipantNbr +
                ", reclamations=" + reclamations +
                ", reservations=" + reservations +
                ", planning=" + planning +
                ", mediaProfile=" + mediaProfile +
                ", rooms=" + rooms +
                ", checkListRSE=" + checkListRSE +
                ", enterprise=" + enterprise +
                '}';
    }
}
