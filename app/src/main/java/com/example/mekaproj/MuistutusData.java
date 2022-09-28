package com.example.mekaproj;
/* @Kaspar Tullus*/

public class MuistutusData {
    private String medName;
    private String startDate;
    private String endDate;
    private String time;
    private int id;

    public MuistutusData (int id,String medName, String startDate, String endDate,String time) {
        this.medName = medName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.time = time;
        this.id = id;
    }

    @Override
    public String toString() {
        return "MuistutusData{" +
                "medName='" + medName + '\n' +
                ", startDate='" + startDate + '\n' +
                ", endDate='" + endDate + '\n' +
                ", time='" + time + '\n' +
                ", id=" + id +
                '}';
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

    public String getEndDate() {
        return endDate;
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

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setIdM(int id) {
        this.id = id;
    }
}

