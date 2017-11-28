package com.irena.robertkaczmarek.pomocnikpracodawcy;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.view.View;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class History extends ListActivity {



    CursorAdapter showa;
    Pracownik pracownik;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      //  setContentView(R.layout.activity_history);
      //  Intent take = getIntent();
      //  String w = take.getStringExtra(history);
        ListView listView = getListView();

        try {
            pracownik = new Pracownik(this);
            db = pracownik.getReadableDatabase();
            Cursor cursor = pracownik.takeData();
            showa = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_1,
                    cursor, new String[]{"name"}, new int[]{android.R.id.text1}, 0);

            listView.setAdapter(showa);
        }
        catch (SQLiteException e){
            Toast.makeText(this, "Nie udalo się wczytać nazwiska z bazy", Toast.LENGTH_SHORT).show();
        }

    }
    public void onListItemClick(ListView listview, View itemView, int position, long id) {

        Intent i = new Intent(History.this, information.class);
        i.putExtra(information.data, (int)id);
        startActivity(i);
    }



}
