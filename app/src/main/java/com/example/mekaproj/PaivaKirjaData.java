package com.example.mekaproj;

public class PaivaKirjaData {
    private String otsikko;
    private String kirje;
    private boolean isActive; //Varmuuden vuoksi boolean tänne jotta voi checkata onko true tai false.

    public PaivaKirjaData (String otsikko, String kirje) {
        this.otsikko = otsikko;
        this.kirje = kirje;
    }
    // Tostring


    @Override
    public String toString() {
        return "PaivaKirjaData{" +
                "otsikko='" + otsikko + '\'' +
                ", kirje='" + kirje + '\'' +
                '}';
    }

    // Getterit ja Setterit!
    public String getOtsikko() {
        return otsikko;
    }

    public String getKirje() {
        return kirje;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setOtsikko(String otsikko) {
        this.otsikko = otsikko;
    }

    public void setKirje(String kirje) {
        this.kirje = kirje;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
    ////////////////////////////////////////
}
