package com.example.mekaproj.Muistutus;
/**
 *
 * @author Kaspar Tullus
 */

public class MuistutusData {
    private String medName; // Database medicine name row text
    private String startDate; // Database date row text
    private String time; // Database time row text
    private int id; // the database row ID

    private int notifyid; // Notification id (For alarmmanager and database) it goes into data base and is taken into alarmmanager to keep track of the current alarm in progress, so it could be canceled.
    // info from the database is sent into here and returned to other classes that call the methods.
    public MuistutusData (int id,String medName, String startDate,String time,int notifyid) {
        this.medName = medName;
        this.startDate = startDate;
        this.time = time;
        this.id = id;
        this.notifyid = notifyid;
    }

    //Viewlist text for "Muistutus" list

    @Override
    public String toString() {
        return
                "Lääke: " + medName + '\n' +
                "Päivä: " + startDate + '\n' +
                "Aika: " + time + '\n' ;
    }

    // Getters And Setters
    public int getIdM() {
        return id;
    }

    public String getMedName() {
        return medName;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getTime() {
        return time;
    }

    public int getNotifyid() {

        return notifyid;
    }

    public void setMedName(String medName) {
        this.medName = medName;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setIdM(int id) {
        this.id = id;
    }
}

