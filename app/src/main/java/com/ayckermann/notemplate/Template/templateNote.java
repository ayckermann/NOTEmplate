package com.ayckermann.notemplate.Template;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;


import com.ayckermann.notemplate.MainActivity;
import com.ayckermann.notemplate.Model.Note;
import com.ayckermann.notemplate.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Calendar;


public class templateNote extends Activity {

    EditText edtJudul, edtContent;
    FloatingActionButton btnSave;
    TextView txtTanggal;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.template_note);

        initComponent();

        txtTanggal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setTanggal();
            }
        });


        Intent intent = getIntent();
        getIntent().getExtras();
        if (intent.hasExtra("judulN")){
            int id = intent.getIntExtra("idN",0);
            String judul = intent.getStringExtra("judulN");
            String content = intent.getStringExtra("contentN");
            String tanggal = intent.getStringExtra("tanggalN");


            edtJudul.setText(judul);
            edtContent.setText(content);
            txtTanggal.setText(tanggal);

            btnSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String judul2 = edtJudul.getText().toString();
                    String content2 = edtContent.getText().toString();
                    String tanggal2 = txtTanggal.getText().toString();

                    Log.e("i", String.valueOf(id));
                    Log.e("e", String.valueOf(MainActivity.transferNote.size()));

                    MainActivity.transferNote.set(id, new Note( judul2,content2, tanggal2));


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
                    String tanggal = txtTanggal.getText().toString();

                    MainActivity.transferNote.add(new Note(judul,content,tanggal));

                    startActivity(new Intent(view.getContext(), MainActivity.class));

                }
            });
        }
    }

    public void initComponent(){
        edtJudul = findViewById(R.id.edtJudulNote);
        edtContent = findViewById(R.id.edtContentNote);
        txtTanggal = findViewById(R.id.txtTanggal);
        btnSave = (FloatingActionButton) findViewById(R.id.btnSaveNote);
    }


    public void setTanggal(){
        final Calendar c = Calendar.getInstance();

        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);


        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        txtTanggal.setText( getMonthForInt(monthOfYear)+ " " + dayOfMonth + ", " + year);

                    }
                },

                year, month, day);
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