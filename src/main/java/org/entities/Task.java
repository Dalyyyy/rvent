package org.entities;

import java.util.List;

public class Task {
    private int id;
    private String nametask;
    private String description;
    private boolean status;
    private Activity activity;
    private User oc;


    public Task() {}

    public Task(int id, String nametask, String description, boolean status, Activity activity,User oc) {
        this.id = id;
        this.nametask = nametask;
        this.description = description;
        this.status = status;
        this.activity = activity;
        this.oc = oc;
    }

    public Task(int taskId, String taskName, String description, boolean status, String activity, String oc) {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNametask() {
        return nametask;
    }

    public void setNametask(String nametask) {
        this.nametask = nametask;
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

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public User getOc() {
        return oc;
    }
    public void setOc(User oc)  {
        this.oc = oc;
    }
    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", task name='" + nametask + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                ", activity=" + activity +
                ", oc=" + oc +
                '}';
    }
}
