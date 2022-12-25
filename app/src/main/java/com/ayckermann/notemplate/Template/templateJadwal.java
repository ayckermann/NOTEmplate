package com.ayckermann.notemplate.Template;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.ayckermann.notemplate.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

public class templateJadwal extends Activity {
    EditText edtJudul;
    FloatingActionButton btnSave, btnDelete;

    FirebaseUser user;
    FirebaseFirestore firestore;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.template_jadwal);
    }
    public void initComponent(){
        edtJudul = findViewById(R.id.edtJudulJadwl);

        btnSave = (FloatingActionButton) findViewById(R.id.btnSaveJadwal);
        btnDelete = (FloatingActionButton) findViewById(R.id.btnDeleteJadwal);
        user = FirebaseAuth.getInstance().getCurrentUser();
        firestore = FirebaseFirestore.getInstance();
    }

}
