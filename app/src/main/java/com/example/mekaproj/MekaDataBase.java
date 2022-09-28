package com.example.mekaproj;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;
/* @Kaspar Tullus*/
public class MekaDataBase extends SQLiteOpenHelper{

    // Paivakirja database finals
    static final String DB_NAME = "DATABASE.db"; // NAME OF DataBase FILE
    public static final String PAIVAKIRJA_TABLE = "PAIVAKIRJA_TABLE"; // DATABASE TABLE (WHERE COLUMNS GO INTO)
    public static final String COLUMN_PAIVAKIRJA_OTSIKKO = "PAIVAKIRJA_OTSIKKO"; // TITLE FOR PAIVAKIRJA
    public static final String COLUMN_PAIVAKIRJA_KIRJE = "PAIVAKIRJA_KIRJE"; // TEXT/STORY FOR PAIVAKIRJA
    public static final String COLUMN_ID = "ID"; //COLUMN ID

    // Muistutus database finals

    public static final String MUISTUTUS_TABLE = "MUISTUTUS_TABLE"; // DATABASE TABLE (WHERE COLUMNS GO INTO)
    public static final String COLUMN_MUISTUTUS_SPAIVA = "MUISTUTUS_SPAIVA"; // START DAY
    public static final String COLUMN_MUISTUTUS_EPAIVA = "MUISTUTUS_EPAIVA"; // END DAY
    public static final String COLUMN_MUISTUTUS_AIKA = "MUISTUTUS_AIKA"; // TIME
    public static final String COLUMN_MUISTUTUS_NIMI = "MUISTUTUS_NIMI"; // NAME
    public static final String COLUMN_ID_MUISTUTUS = "ID"; //COLUMN ID

/////////////////////////////////////////////////////////////////////////////


    public MekaDataBase(@Nullable Context context) {

        super(context, DB_NAME, null, 1);
    }

    //First time Muistutus database is created.
    //First time paivakirja database is created.
    @Override
    public void onCreate(SQLiteDatabase db) {
        // Creates values and database with sql code. for MUISTUTUS
        String createTableStatementMuistutus = "CREATE TABLE " + MUISTUTUS_TABLE + " (" + COLUMN_ID_MUISTUTUS + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_MUISTUTUS_SPAIVA + " DATE," + COLUMN_MUISTUTUS_EPAIVA + " DATE," + COLUMN_MUISTUTUS_AIKA + " TIME," + COLUMN_MUISTUTUS_NIMI + " TEXT)";
        db.execSQL(createTableStatementMuistutus);

        // Creates values and database with sql code. for PAIVAKIRJA
        String createTableStatementPaivakirja = "CREATE TABLE " + PAIVAKIRJA_TABLE + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_PAIVAKIRJA_OTSIKKO + " TEXT," + COLUMN_PAIVAKIRJA_KIRJE + " TEXT)";
        db.execSQL(createTableStatementPaivakirja);
    }

    //this is called if the database version is updated so old users dont lose data.
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public boolean addOneMUIS (PaivaKirjaData paivaKirjaData){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_PAIVAKIRJA_OTSIKKO,paivaKirjaData.getOtsikko()); // Tässä kirjoitetaan column_paivakirjaan otsikko joka saadaan getOtsikko methodilla paivakirjadatasta
        cv.put(COLUMN_PAIVAKIRJA_KIRJE,paivaKirjaData.getKirje()); // Tässä kirjoitetaan column_paivakirjaan kirje joka saadaan getKirje methodilla paivakirjadatasta

        long insert = db.insert(PAIVAKIRJA_TABLE, null, cv);

        // if insert is -1 it returns a false if its something else it returns true.

        if (insert == -1)
        {return false;}

        else

        { return true; }

    }



    // Adds written data into the database
    public boolean addOnePK(PaivaKirjaData paivaKirjaData){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_PAIVAKIRJA_OTSIKKO,paivaKirjaData.getOtsikko()); // Tässä kirjoitetaan column_paivakirjaan otsikko joka saadaan getOtsikko methodilla paivakirjadatasta
        cv.put(COLUMN_PAIVAKIRJA_KIRJE,paivaKirjaData.getKirje()); // Tässä kirjoitetaan column_paivakirjaan kirje joka saadaan getKirje methodilla paivakirjadatasta

        long insert = db.insert(PAIVAKIRJA_TABLE, null, cv);

        // if insert is -1 it returns a false if its something else it returns true.

        if (insert == -1)
        {return false;}

        else

        { return true; }

    }

    //Deletes an data that was selected by a user.
    public boolean deleteOne (PaivaKirjaData paivaKirjaData){
        SQLiteDatabase db = getWritableDatabase();
        String queryString = "DELETE FROM " + PAIVAKIRJA_TABLE + " WHERE " + COLUMN_ID + " = " + paivaKirjaData.getID();
        Cursor cursor = db.rawQuery(queryString, null);
        if(cursor.moveToFirst()){
            return true;
        }else{
            return false;
        }
    }


    //Geteverything returns everything from the database.
    public List<PaivaKirjaData> getEverything() {

        List<PaivaKirjaData> returnList = new ArrayList<>();

        // Get data from database
        String queryString = "SELECT * FROM " + PAIVAKIRJA_TABLE;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()){
            do {

                Integer paivakirjaid = cursor.getInt(0);
                String paivakirjaOtsikko = cursor.getString(1);

                String paivakirjaKirje = cursor.getString(2);

                PaivaKirjaData paivaKirjaData = new PaivaKirjaData(paivakirjaid,paivakirjaOtsikko,paivakirjaKirje);

                returnList.add(paivaKirjaData);

            }while (cursor.moveToNext());


        }else {
            //fail dont add anything
        }

        // close the database and cursor. then return everything from the database.
        cursor.close();

        db.close();

        return returnList;
    }

}
