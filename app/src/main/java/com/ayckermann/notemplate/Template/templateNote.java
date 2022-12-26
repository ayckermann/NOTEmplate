package com.ayckermann.notemplate.Template;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;


import androidx.annotation.NonNull;

import com.ayckermann.notemplate.MainActivity;
import com.ayckermann.notemplate.Model.Note;
import com.ayckermann.notemplate.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.lang.reflect.Field;
import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;


public class templateNote extends Activity {

    EditText edtJudul, edtContent;
    FloatingActionButton btnSave, btnDelete;
    TextView txtTanggal;

    FirebaseUser user;
    FirebaseFirestore firestore;
    Note note;

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
        note = (Note) intent.getSerializableExtra("current_note");
        if (note != null){

            edtJudul.setText(note.judul);
            edtContent.setText(note.content);
            txtTanggal.setText(note.tanggal);

            btnSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String userId = user.getUid();
                    String judul = edtJudul.getText().toString();
                    String content = edtContent.getText().toString();
                    String tanggal = txtTanggal.getText().toString();

                    Note note1 = new Note(userId, judul,content,tanggal);
                    Map map = note1.asMap();


                    firestore.collection("Note").document(note.uid).update(map)
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.e("Error edit FireStore ", e.getMessage());
                                }
                            });

                    finish();
                    startActivity(new Intent(view.getContext(), MainActivity.class));
                }
            });
        }
        else{
            btnDelete.setVisibility(View.GONE);
            btnDelete.setEnabled(false);
            btnSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String userId = user.getUid();
                    String judul = edtJudul.getText().toString();
                    String content = edtContent.getText().toString();
                    String tanggal = txtTanggal.getText().toString();

                    firestore.collection("Note").document()
                            .set(new Note(userId,judul,content,tanggal))
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.e("Error add firestore ", e.getMessage());
                                }
                            });
                    finish();
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
        btnDelete = (FloatingActionButton) findViewById(R.id.btnDeleteNote);
        user = FirebaseAuth.getInstance().getCurrentUser();
        firestore = FirebaseFirestore.getInstance();
    }
    public void deleteNote(View v){
        AlertDialog.Builder alert = new AlertDialog.Builder(v.getContext(), android.R.style.Theme_DeviceDefault_Dialog_Alert);
        alert.setTitle("Delete Note " + edtJudul.getText().toString() + " ?")
                .setPositiveButton("Cancel", (dialogInterface, i) -> dialogInterface.cancel())
                .setNegativeButton("Yes", (dialogInterface, i) -> {

                    firestore.collection("Note").document(note.uid)
                            .delete()
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.e("Error delete", e.getMessage());
                                }
                            });
                    finish();

                });
        AlertDialog dialog = alert.create();
        dialog.show();
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