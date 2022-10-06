package com.example.mekaproj.Muistutus;
/**
 *
 * @author Kaspar Tullus
 */

public class MuistutusData {
    private String medName;
    private String startDate;
    private String time;
    private int id;
    private int notifyid;
    public MuistutusData (int id,String medName, String startDate,String time,int notifyid) {
        this.medName = medName;
        this.startDate = startDate;
        this.time = time;
        this.id = id;
        this.notifyid = notifyid;
    }

    @Override
    public String toString() {
        return
                "Medicine: " + medName + '\n' +
                "Start: " + startDate + '\n' +
                "Time: " + time + '\n' ;
    }

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

