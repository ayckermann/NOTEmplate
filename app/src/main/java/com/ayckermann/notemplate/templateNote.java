package com.ayckermann.notemplate;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;


import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class templateNote extends Activity {


    EditText edtJudul, edtContent;
    FloatingActionButton btnSave;

    int id =1;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.template_note);

        initComponent();
        Intent intent = getIntent();
        getIntent().getExtras();
        if (intent.hasExtra("id")){

            String judul;
            String content;
            id = Integer.parseInt(intent.getStringExtra("id"));
            judul = intent.getStringExtra("judul");
            content = intent.getStringExtra("content");

            String template = intent.getStringExtra("template");

            edtJudul.setText(judul);
            edtContent.setText(content);

            btnSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String judul2 = edtJudul.getText().toString();
                    String content2 = edtContent.getText().toString();

                    MainActivity.transfer.add(new Note(id,judul2,content2,"Note"));

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

                    MainActivity.transfer.add(new Note(id,judul,content,"Note"));
                    id++;

                    Log.e("TEST", judul);
                    Log.e("TEST", content);


                    startActivity(new Intent(view.getContext(), MainActivity.class));

                }
            });
        }
    }

    public void initComponent(){
        edtJudul = findViewById(R.id.edtJudulNote);
        edtContent = findViewById(R.id.edtContentNote);

        btnSave = (FloatingActionButton) findViewById(R.id.btnSaveNote);
    }



}
