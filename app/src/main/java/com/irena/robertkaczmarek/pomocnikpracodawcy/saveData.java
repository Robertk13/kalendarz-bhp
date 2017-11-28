package com.irena.robertkaczmarek.pomocnikpracodawcy;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

public class saveData extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    EditText name, surname, dateLern, dateDoc, dateContr;
    Button save, clear, show, show1;
    Spinner post;
    // Calendar cal;
    Calendar newDate;
    //EditText dataszkolenia;
    DatePickerDialog dpd, dpd1, dpd2, dpd4;
    android.icu.text.SimpleDateFormat dateFormatter;
    int days;
    ArrayList<Pracownik> lista;
    int i = 0;
    EditText dataNastSzkol;
    public Pracownik pracownikDbHelper;
    String imie1;
    String nazwisko1;
    String stanowisko1;
    String data_szkolenia1;
    String nast_badanie1;
    String koniec_um1;
    String dataNastSzk1;
    Double termLern;
    String urA;
    int item;
    int yy, mm, dd;
    int year1;
    int monthOfYear1, dayOfMonth2;
    String data1;
    RadioGroup lern;
    RadioButton firstLern;
    RadioButton periotLern;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_data);
        pracownikDbHelper = new Pracownik(this);
        dataNastSzkol = (EditText) findViewById(R.id.dataNextLearn);

        // dataszkolenia = (Te) findViewById(R.id.dataszkolenia);
        name = (EditText) findViewById(R.id.name);
        surname = (EditText) findViewById(R.id.surname);
        //  post = (Spinner) findViewById(R.id.stanowisko);
        dateLern = (EditText) findViewById(R.id.dataLearning);
        dateLern.setInputType(InputType.TYPE_NULL);
        // dateLern.requestFocus();
        dateDoc = (EditText) findViewById(R.id.dataDoctor);
        dateDoc.setInputType(InputType.TYPE_NULL);
        dateContr = (EditText) findViewById(R.id.dataEndOfContract);
        dateContr.setInputType(InputType.TYPE_NULL);
        save = (Button) findViewById(R.id.save);
        clear = (Button) findViewById(R.id.clear);
        show1 = (Button) findViewById(R.id.show1);
        dateLern.setOnClickListener(this);
        dateDoc.setOnClickListener(this);
        dateContr.setOnClickListener(this);
        save.setOnClickListener(this);
        show1.setOnClickListener(this);
        clear.setOnClickListener(this);

        post = (Spinner) findViewById(R.id.stanowisko);
        post.setOnItemSelectedListener(this);
         lern = (RadioGroup) findViewById(R.id.kindOfLern);
       // int numberRadioButton = lern.getCheckedRadioButtonId();
        firstLern = (RadioButton) findViewById(R.id.firstLern);
        periotLern = (RadioButton) findViewById(R.id.periodLern);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.post1,
                android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        post.setAdapter(adapter);
        post.setVisibility(View.INVISIBLE);
        periotLern.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(isChecked){
                    post.setVisibility(View.VISIBLE);
                    post.setClickable(true);

                }
                else {post.setVisibility(View.INVISIBLE);
                    post.setClickable(false);
                    post.setSelection(0);

                }
            }
        });

        //   cal = Calendar.getInstance();
        newDate = Calendar.getInstance();
        yy = newDate.get(Calendar.YEAR);
        mm = newDate.get(Calendar.MONTH);
        dd = newDate.get(Calendar.DAY_OF_MONTH);
        dpd = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                //  newDate.set(year, monthOfYear+1, dayOfMonth);
                Toast.makeText(saveData.this, year + "/" + String.format("%02d", monthOfYear + 1) + "/"
                        + dayOfMonth, Toast.LENGTH_LONG).show();
                year1 = year;
                monthOfYear1 = monthOfYear;
                dayOfMonth2 = dayOfMonth;
                dateLern.setText(String.format("%02d", dayOfMonth) + "." + String.format("%02d", monthOfYear + 1)
                        + "." + year);
                dataNastSzkol.setText(String.format("%02d", dayOfMonth) + "." + String.format("%02d", monthOfYear + 1)
                        + "." + (year + days));
                //  Toast.makeText(saveData.this, yy+"/"+mm+"/"+dayOfMonth, Toast.LENGTH_LONG).show();
                // dateLern.setText(year+"/"+monthOfYear+"/"+dayOfMonth);
            }

        }, yy, mm, dd);
        dpd1 = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                //  newDate.set(year, monthOfYear+1, dayOfMonth);
                Toast.makeText(saveData.this, year + "/" + String.format("%02d", monthOfYear + 1) + "/" + dayOfMonth, Toast.LENGTH_LONG).show();
                dateDoc.setText(String.format("%02d", dayOfMonth) + "." + String.format("%02d", monthOfYear + 1) + "." + year);
                //  Toast.makeText(saveData.this, yy+"/"+mm+"/"+dayOfMonth, Toast.LENGTH_LONG).show();
                // dateLern.setText(year+"/"+monthOfYear+"/"+dayOfMonth);
            }

        }, yy, mm, dd);
        dpd2 = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                //  newDate.set(year, monthOfYear+1, dayOfMonth);
                Toast.makeText(saveData.this, year + "/" + String.format("%02d", monthOfYear + 1)
                        + "/" + dayOfMonth, Toast.LENGTH_LONG).show();
                dateContr.setText(String.format("%02d", dayOfMonth) + "." +
                        String.format("%02d", monthOfYear + 1) + "." + year);
                //  Toast.makeText(saveData.this, yy+"/"+mm+"/"+dayOfMonth, Toast.LENGTH_LONG).show();
                // dateLern.setText(year+"/"+monthOfYear+"/"+dayOfMonth);
            }

        }, yy, mm, dd);


    }


    //@RequiresApi(api = Build.VERSION_CODES.N)
    //  @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onClick(View v) {
        if (v == dateLern) {
            dpd.show();
            // dateLern.setText(sdf.format(newDate.getTime()));
        } else if (v == dateDoc) {
            dpd1.show();
        } else if (v == dateContr) {
            dpd2.show();
        } else if (v == save) {


            imie1 = name.getText().toString();
            nazwisko1 = surname.getText().toString();
            stanowisko1 = post.getSelectedItem().toString();
            data_szkolenia1 = dateLern.getText().toString();
            nast_badanie1 = dateDoc.getText().toString();
            koniec_um1 = dateContr.getText().toString();
            dataNastSzk1 = dataNastSzkol.getText().toString();
            boolean udalo;
            udalo = pracownikDbHelper.addWorker(imie1, nazwisko1, stanowisko1,
                    data_szkolenia1, nast_badanie1, koniec_um1, dataNastSzk1);
            if (udalo) {
                Toast.makeText(saveData.this, "Podrałeś dane", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(saveData.this, "Nie udało siępobrać danych", Toast.LENGTH_LONG).show();
            }
        } else if (v == clear) {

            AlertDialog.Builder alert = new AlertDialog.Builder(saveData.this);
            alert.setMessage(" Czy na pewno chcesz wyczyścić zawartośc pól ?").setPositiveButton("Tak", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    name.setText("");
                    surname.setText("");
                    dateLern.setText("");
                    dateDoc.setText("");
                    dateContr.setText("");
                    dataNastSzkol.setText("");
                }
            }).setNegativeButton("Nie", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {


                }
            });
            alert.create();
            alert.show();


        } else if (v == show1) {

            Intent hisRes = new Intent(saveData.this, History.class);
            startActivity(hisRes);
        }
    }


        @Override
        public void onItemSelected (AdapterView < ? > parent, View view,int position, long id) {
            String text1 = dateLern.getText().toString().trim();
            item = parent.getSelectedItemPosition();


                switch (item) {
                    case 0:
                        days = 1;
                        dataNastSzkol.setText(String.format("%02d", dayOfMonth2) + "." +
                                String.format("%02d", monthOfYear1 + 1)
                                + "." + (year1 + days));
                        Toast.makeText(saveData.this, year1 + "/" + monthOfYear1 + "/" + dayOfMonth2, Toast.LENGTH_LONG).show();
                        break;
                    case 1:
                        days = 3;
                        if (text1.isEmpty() || text1 == null || text1.equals("") || text1.length() == 0) {
                            dataNastSzkol.setText("30.07.1970");
                        } else
                            dataNastSzkol.setText(String.format("%02d", dayOfMonth2) + "." +
                                    String.format("%02d", monthOfYear1 + 1)
                                    + "." + (year1 + days));
                        Toast.makeText(saveData.this, year1 + "/" + monthOfYear1 + "/" + dayOfMonth2, Toast.LENGTH_LONG).show();

                        break;
                    case 2:
                        days = 1;
                        if (text1.isEmpty() || text1 == null || text1.equals("") || text1.length() == 0) {
                            dataNastSzkol.setText("30.07.1970");
                        } else

                            dataNastSzkol.setText(String.format("%02d", dayOfMonth2) + "." +
                                    String.format("%02d", monthOfYear1 + 1)
                                    + "." + (year1 + days));
                        Toast.makeText(saveData.this, year1 + "/" + monthOfYear1 + "/" + dayOfMonth2, Toast.LENGTH_LONG).show();
                        break;
                    case 3:
                        days = 6;
                        if (text1.isEmpty() || text1 == null || text1.equals("") || text1.length() == 0) {
                            dataNastSzkol.setText("30.07.1970");
                        } else

                            dataNastSzkol.setText(String.format("%02d", dayOfMonth2) + "." +
                                    String.format("%02d", monthOfYear1 + 1)
                                    + "." + (year1 + days));
                        Toast.makeText(saveData.this, year1 + "/" + monthOfYear1 + "/" + dayOfMonth2, Toast.LENGTH_LONG).show();
                        break;
                    case 4:
                        days = 5;
                        if (text1.isEmpty() || text1 == null || text1.equals("") || text1.length() == 0) {
                            dataNastSzkol.setText("30.07.1970");
                        } else
                            dataNastSzkol.setText(String.format("%02d", dayOfMonth2) + "." +
                                    String.format("%02d", monthOfYear1 + 1)
                                    + "." + (year1 + days));
                        Toast.makeText(saveData.this, year1 + "/" + monthOfYear1 + "/" + dayOfMonth2, Toast.LENGTH_LONG).show();
                        break;
                    case 5:
                        days = 5;
                        if (text1.isEmpty() || text1 == null || text1.equals("") || text1.length() == 0) {
                            dataNastSzkol.setText("30.07.1970");
                        } else
                            dataNastSzkol.setText(String.format("%02d", dayOfMonth2) + "." +
                                    String.format("%02d", monthOfYear1 + 1)
                                    + "." + (year1 + days));
                        Toast.makeText(saveData.this, year1 + "/" + monthOfYear1 + "/" + dayOfMonth2, Toast.LENGTH_LONG).show();
                        break;
                    default:
                }

            }


    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }





  /*  public void oblicz(View view) {

        dataNastSzkol.setText(data1);


    } */


 /*   public void oblicz(View view) {


    }*/



}

