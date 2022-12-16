package com.ayckermann.notemplate;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;


import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Calendar;


public class templateNote extends Activity {


    EditText edtJudul, edtContent;
    FloatingActionButton btnSave;
    EditText edtTanggal;

    static ArrayList<Note> listNote = new ArrayList<>();
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.template_note);

        initComponent();

        edtTanggal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setTanggal();
            }
        });


        Intent intent = getIntent();
        getIntent().getExtras();
        if (intent.hasExtra("judul")){
            int id = intent.getIntExtra("id",0);
            String judul = intent.getStringExtra("judul");
            String content = intent.getStringExtra("content");
            String tanggal = intent.getStringExtra("tanggal");

            String template = intent.getStringExtra("template");

            edtJudul.setText(judul);
            edtContent.setText(content);
            edtTanggal.setText(tanggal);

            btnSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String judul2 = edtJudul.getText().toString();
                    String content2 = edtContent.getText().toString();
                    String tanggal2 = edtTanggal.getText().toString();

                    MainActivity.transfer.set(id, new Model(judul2,content2,template));
                    listNote.set(id,new Note(judul2,content2,tanggal2));

                    startActivity(new Intent(view.getContext(), MainActivity.class));
                }
            });
        }
        else{
            btnSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String judul = edtJudul.getText().toString();
                    String content = edtContent.getText().toString();
                    String tanggal = edtTanggal.getText().toString();

                    MainActivity.transfer.add(new Model(judul,content,"Note"));

                    listNote.add(new Note(judul,content,tanggal));

                    startActivity(new Intent(view.getContext(), MainActivity.class));

                }
            });
        }
    }

    public void initComponent(){
        edtJudul = findViewById(R.id.edtJudulNote);
        edtContent = findViewById(R.id.edtContentNote);
        edtTanggal = (EditText) findViewById(R.id.edtTanggal);
        edtTanggal.setInputType(InputType.TYPE_NULL);

        btnSave = (FloatingActionButton) findViewById(R.id.btnSaveNote);
    }


    public void setTanggal(){

        // on below line we are getting
        // the instance of our calendar.
        final Calendar c = Calendar.getInstance();

        // on below line we are getting
        // our day, month and year.
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        // on below line we are creating a variable for date picker dialog.
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                // on below line we are passing context.
                this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        // on below line we are setting date to our edit text.
                        edtTanggal.setText( getMonthForInt(monthOfYear)+ " " + dayOfMonth + ", " + year);

                    }
                },
                // on below line we are passing year,
                // month and day for selected date in our date picker.
                year, month, day);
        // at last we are calling show to
        // display our date picker dialog.
        datePickerDialog.show();
    }
    String getMonthForInt(int num) {
        String month = "wrong";
        DateFormatSymbols dfs = new DateFormatSymbols();
        String[] months = dfs.getMonths();
        if (num >= 0 && num <= 11) {
            month = months[num];
        }
        return month;
    }
}