package com.irena.robertkaczmarek.pomocnikpracodawcy;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

/**
 * Created by robertkaczmarek on 02.09.2017.
 */

public class Pracownik extends SQLiteOpenHelper implements BaseColumns {

    public static final String BASE_NAME = "pracownicy";
    public static final String TABLE_NAME = "lista_pracownikow";
    public static final String NAME = "name";
    public static final String SURNAME = "surname";
    public static final String POST = "post";
    public static final String DATE_LERN = "date_lern";
    public static final String DATE_NEXT_MEDICAL = "date_next_medical";
    public static final String DATE_END_CONTRACT = "date_end_contract";
    public static final String DATE_NEXT_LERN = "date_next_lern";
    public static final int VERSION = 2;

    public Pracownik(Context context) {
        super(context, BASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String CREATE_TABLE_WORKERS = " CREATE TABLE " + TABLE_NAME + "( " + _ID + " INTEGER PRIMARY KEY, " +
                NAME + " TEXT, " + SURNAME + " TEXT, " + POST + " TEXT, " + DATE_LERN + " TEXT, " + DATE_NEXT_MEDICAL +
                " TEXT, " + DATE_NEXT_LERN + " TEXT, " + DATE_END_CONTRACT + " TEXT )";
        db.execSQL(CREATE_TABLE_WORKERS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }
    public boolean addWorker(String name, String surmane, String post, String dateLern,
                          String dateMedicin, String dateEndContract, String nextLern) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        String nameEndSurname = name + " "+ surmane;
        cv.put(Pracownik.NAME, nameEndSurname);
        cv.put(Pracownik.SURNAME, surmane);
        cv.put(Pracownik.POST, post);
        cv.put(Pracownik.DATE_LERN, dateLern);
        cv.put(Pracownik.DATE_NEXT_MEDICAL, dateMedicin);
        cv.put(Pracownik.DATE_END_CONTRACT, dateEndContract);
        cv.put(Pracownik.DATE_NEXT_LERN, nextLern);

      //  long id = db.insert(TABLE_NAME, null, cv);
      // Log.d("saveActivity ", "Inserted row: " + id);

        return db.insert(TABLE_NAME, null, cv) != -1;

    }

    public Cursor takeData() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cu = db.query(TABLE_NAME, new String[]{_ID, NAME, SURNAME, POST,
                        DATE_NEXT_MEDICAL, DATE_NEXT_LERN, DATE_END_CONTRACT},
                null, null, null, null, null);
        return cu;
    }

}
