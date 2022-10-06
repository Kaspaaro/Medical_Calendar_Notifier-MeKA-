package com.example.mekaproj.PaivaKirja;
/**
 *
 * @author Kaspar Tullus
 *
 */
public class PaivaKirjaData {
    private int id;
    private String otsikko;
    private String kirje;
    private String paiva;
    private boolean isActive; //Varmuuden vuoksi boolean tänne jotta voi checkata onko true tai false.

    public PaivaKirjaData (int id,String otsikko, String kirje,String Date) {
        this.otsikko = otsikko;
        this.kirje = kirje;
        this.id = id;
        this.paiva = Date;
    }

    @Override

    //Tallenetut Rivit Viewlist Tostring.
    public String toString() {
        return "     " +
                otsikko + '\n' +
                paiva + '\n'
                ;
    }

    // Getterit ja Setterit!
    public Integer getID(){

        return id;
    }
    public String getOtsikko() {

        return otsikko;
    }

    public String getKirje() {

        return kirje;
    }

    public String getPaiva() {
        return paiva;
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
    ////////
}
