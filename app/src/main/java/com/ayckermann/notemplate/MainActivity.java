package com.ayckermann.notemplate;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.service.controls.actions.FloatAction;
import android.view.View;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    listAdapter listAdapter;
    FloatingActionButton btnAdd;
    Button btnNote, btnTodo, btnJadwal;

    static  ArrayList<Model> transfer = new ArrayList<>();
    ArrayList<Model> listModel = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initComponent();

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, selectTemplate.class));
            }
        });

        for (int i =0; i< transfer.size();i++){
            listModel.add(new Model( transfer.get(i).getJudul(), transfer.get(i).getContent(), transfer.get(i).getTemplate()));
        }
        listAdapter.notifyDataSetChanged();

    }
    public void initComponent(){


        btnAdd = findViewById(R.id.btnAdd);
        btnNote = findViewById(R.id.btnNote);
        btnTodo =  findViewById(R.id.btnTodo);
        btnJadwal =  findViewById(R.id.btnJadwal);

        listAdapter =new listAdapter(listModel);
        recyclerView = findViewById(R.id.rvMenu);
        recyclerView.setAdapter(listAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }


}