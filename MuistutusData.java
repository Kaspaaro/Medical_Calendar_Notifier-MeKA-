package com.example.mekaproj;
/**
 *
 * @author Kaspar Tullus
 */

public class MuistutusData {
    private String medName;
    private String startDate;
    private String time;
    private int id;

    public MuistutusData (int id,String medName, String startDate,String time) {
        this.medName = medName;
        this.startDate = startDate;
        this.time = time;
        this.id = id;
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

