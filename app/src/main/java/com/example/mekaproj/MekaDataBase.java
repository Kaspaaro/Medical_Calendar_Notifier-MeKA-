package com.example.mekaproj;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MekaDataBase extends SQLiteOpenHelper{


    public static final String PAIVAKIRJA_TABLE = "PAIVAKIRJA_TABLE";
    public static final String COLUMN_PAIVAKIRJA_OTSIKKO = "PAIVAKIRJA_OTSIKKO";
    public static final String COLUMN_PAIVAKIRJA_KIRJE = "PAIVAKIRJA_KIRJE";
    public static final String COLUMN_ID = "ID";

    public MekaDataBase(@Nullable Context context) {

        super(context, "Paivakirja.db", null, 1);
    }

    //Eka kerta databasea käytetään. Täälä pitäisi olla koodia joka luo uuden databasen.
    @Override
    public void onCreate(SQLiteDatabase db) {

        // Luo databaselle arvot SQL koodia käyttäen.
        String createTableStatement = "CREATE TABLE " + PAIVAKIRJA_TABLE + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_PAIVAKIRJA_OTSIKKO + " TEXT," + COLUMN_PAIVAKIRJA_KIRJE + " TEXT)";
        db.execSQL(createTableStatement);
    }

    //tämä kutsutaan jos databasen versio numero muutuu. se estää vanhojen käyttäjien sovelluksen crashaamisen jos databasea muutetaan/päivitetään!
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }


    public boolean addOne (PaivaKirjaData paivaKirjaData){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_PAIVAKIRJA_OTSIKKO,paivaKirjaData.getOtsikko()); // Tässä kirjoitetaan column_paivakirjaan otsikko joka saadaan getOtsikko methodilla paivakirjadatasta
        cv.put(COLUMN_PAIVAKIRJA_KIRJE,paivaKirjaData.getKirje()); // Tässä kirjoitetaan column_paivakirjaan kirje joka saadaan getKirje methodilla paivakirjadatasta
        long insert = db.insert(PAIVAKIRJA_TABLE, null, cv);

        // Jos inserti on -1 palauttaa false jos se on jotain muuta se palautta true
        if (insert == -1){
            return false;
        }else{
            return true;
        }

    }
}
