package com.example.mekaproj.PaivaKirja;
/**
 *
 * @author Kaspar Tullus
 *
 */
public class PaivaKirjaData {

    private int id; // Database row id
    private String otsikko; // Database Header text
    private String kirje; // Database "kirje" text
    private String paiva; // Database date text

    // Info from the database is sent into here and returned to other classes that call the methods.
    public PaivaKirjaData (int id,String otsikko, String kirje,String Date) {
        this.otsikko = otsikko;
        this.kirje = kirje;
        this.id = id;
        this.paiva = Date;
    }

    @Override

    //Saved viewlist rows text.
    public String toString() {
        return "     " +
                otsikko + '\n' +
                paiva + '\n'
                ;
    }

    // Getters and Setters
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


    public void setOtsikko(String otsikko) {
        this.otsikko = otsikko;
    }

    public void setKirje(String kirje) {
        this.kirje = kirje;
    }

    ////////
}
