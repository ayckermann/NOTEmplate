package com.ayckermann.notemplate.Template;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.ayckermann.notemplate.MainActivity;
import com.ayckermann.notemplate.Model.Jadwal;

import com.ayckermann.notemplate.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Map;

public class templateJadwal extends Activity {
    EditText edtJudul;
    EditText edtMonday, edtTuesday, edtWednesday, edtThursday, edtFriday, edtSaturday, edtSunday;

    FloatingActionButton btnSave, btnDelete;

    FirebaseUser user;
    FirebaseFirestore firestore;
    Jadwal jadwal;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.template_jadwal);
        initComponent();

        Intent intent = getIntent();
        jadwal = (Jadwal) intent.getSerializableExtra("current_jadwal");
        if(jadwal != null) {

            edtJudul.setText(jadwal.judul);
            edtMonday.setText(jadwal.monday);
            edtTuesday.setText(jadwal.tuesday);
            edtWednesday.setText(jadwal.wednesday);
            edtThursday.setText(jadwal.thursday);
            edtFriday.setText(jadwal.friday);
            edtSaturday.setText(jadwal.saturday);
            edtSunday.setText(jadwal.sunday);

            btnSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    String userId = user.getUid();
                    String judul = edtJudul.getText().toString();
                    String monday = edtMonday.getText().toString();
                    String tuesday = edtTuesday.getText().toString();
                    String wednesday = edtWednesday.getText().toString();
                    String thursday = edtThursday.getText().toString();
                    String friday =edtFriday.getText().toString();
                    String saturday =edtSaturday.getText().toString();
                    String sunday =edtSunday.getText().toString();
                    if( TextUtils.isEmpty(edtJudul.getText())){
                        edtJudul.setError("Input title here!");
                    }
                    else {
                        Jadwal jadwal1 = new Jadwal(userId,judul, monday, tuesday, wednesday, thursday, friday, saturday, sunday);
                        Map map = jadwal1.asMap();

                        firestore.collection("Jadwal").document(jadwal.uid).update(map)
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Log.e("Error edit firestore ", e.getMessage());
                                    }
                                });
                        finish();
                        startActivity(new Intent(view.getContext(), MainActivity.class));
                    }
                }
            });
        }
        else{
            btnDelete.setVisibility(View.GONE);
            btnSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String userId = user.getUid();
                    String judul = edtJudul.getText().toString();
                    String monday = edtMonday.getText().toString();
                    String tuesday = edtTuesday.getText().toString();
                    String wednesday = edtWednesday.getText().toString();
                    String thursday = edtThursday.getText().toString();
                    String friday =edtFriday.getText().toString();
                    String saturday =edtSaturday.getText().toString();
                    String sunday =edtSunday.getText().toString();

                    if( TextUtils.isEmpty(edtJudul.getText())){
                        edtJudul.setError("Input title here!");
                    }
                    else {
                        firestore.collection("Jadwal").document()
                                .set(new Jadwal(userId,judul, monday, tuesday, wednesday, thursday, friday, saturday, sunday))
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Log.e("Error add firestore ", e.getMessage());
                                    }
                                });
                        finish();
                        startActivity(new Intent(view.getContext(), MainActivity.class));
                    }
                }
            });
        }
    }
    public void initComponent(){
        edtJudul = findViewById(R.id.edtJudulJadwl);
        edtMonday = findViewById(R.id.txtMonday);
        edtTuesday = findViewById(R.id.txtTuesday);
        edtWednesday = findViewById(R.id.txtWednesday);
        edtThursday =findViewById(R.id.txtThursday);
        edtFriday =findViewById(R.id.txtFriday);
        edtSaturday=findViewById(R.id.txtSaturday);
        edtSunday= findViewById(R.id.txtSunday);

        btnSave = (FloatingActionButton) findViewById(R.id.btnSaveJadwal);
        btnDelete = (FloatingActionButton) findViewById(R.id.btnDeleteJadwal);
        user = FirebaseAuth.getInstance().getCurrentUser();
        firestore = FirebaseFirestore.getInstance();
    }
    public void deleteJadwal(View v){

        AlertDialog.Builder alert = new AlertDialog.Builder(v.getContext(), android.R.style.Theme_DeviceDefault_Dialog_Alert);
        alert.setTitle("Delete Jadwal " + edtJudul.getText().toString() + " ?")
                .setPositiveButton("Cancel", (dialogInterface, i) -> dialogInterface.cancel())
                .setNegativeButton("Yes", (dialogInterface, i) -> {

                    firestore.collection("Jadwal").document(jadwal.uid)
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

}
