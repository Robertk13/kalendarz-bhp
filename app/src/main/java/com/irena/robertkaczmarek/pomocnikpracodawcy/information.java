package com.irena.robertkaczmarek.pomocnikpracodawcy;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

public class information extends AppCompatActivity {
    public static final String data = "data";
    SQLiteDatabase db1;
    Pracownik pracownik1;
    Cursor cursor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);

        int d = (Integer)getIntent().getExtras().get(data);

        try {
            pracownik1 = new Pracownik(this);
            db1 = pracownik1.getReadableDatabase();

            cursor = db1.query( pracownik1.TABLE_NAME, new String[]{pracownik1.NAME,
                     pracownik1.DATE_NEXT_MEDICAL,
                    pracownik1.DATE_NEXT_LERN,pracownik1.DATE_END_CONTRACT},"_id=?",new String[]{Integer.toString(d)},
            null,null,null);

            if ( cursor.moveToNext()){


                String nameAndSurn1 = cursor.getString(0);
                String dateDoc1 = cursor.getString(1);
                String dateLern1 = cursor.getString(2);
                String dateEndCont1 = cursor.getString(3);

                TextView name1 = (TextView)findViewById(R.id.nameAndSurname);
                name1.setText(nameAndSurn1);
                TextView datDoc = (TextView)findViewById(R.id.showNextDoctor);
                datDoc.setText(dateDoc1);
                TextView datNextLern = (TextView)findViewById(R.id.showNextLern);
                datNextLern.setText(dateLern1);
                TextView dateEndContr = (TextView)findViewById(R.id.showEndOfContract);
                dateEndContr.setText(dateEndCont1);

            };



        }catch (SQLiteException e){
            Toast.makeText(this, "Nie udało się pobrać danych z bazy", Toast.LENGTH_SHORT).show();
        }


    }

}
