package com.irena.robertkaczmarek.pomocnikpracodawcy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class chooseActivity extends AppCompatActivity implements View.OnClickListener {

    TextView save;
    TextView showName;
    public Pracownik pracownikDbHelper1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);
        pracownikDbHelper1 = new Pracownik(this);
        save = (TextView) findViewById(R.id.save);
        showName = (TextView) findViewById(R.id.showName);
        save.setOnClickListener(this);
        showName.setOnClickListener(this);

    }

    @Override
    public void onClick(View w) {
        if (w == save) {

            Intent a = new Intent(chooseActivity.this, saveData.class);
            startActivity(a);
        } else if (w == showName) {
            Intent hisRes = new Intent(chooseActivity.this, History.class);
            startActivity(hisRes);
            }
        }

    }
    /**
     * Created by robertkaczmarek on 02.09.2017.
     */

