package org.services;
import javafx.collections.ObservableList;
import org.entities.Activity;

import java.sql.SQLException;

public interface IActivityService <A> {
    public void ajouter(Activity activity) throws SQLException;
    public void modifier(Activity activity) throws SQLException;
    public void supprimer(int activityId) throws SQLException;
    public ObservableList<Activity> getAllActivities() throws SQLException;



}
