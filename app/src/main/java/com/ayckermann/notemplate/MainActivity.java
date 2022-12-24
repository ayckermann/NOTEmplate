package com.ayckermann.notemplate;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.ayckermann.notemplate.Adapter.adapterNote;
import com.ayckermann.notemplate.Adapter.adapterTodo;
import com.ayckermann.notemplate.Model.HeadTodo;
import com.ayckermann.notemplate.Model.Note;
import com.ayckermann.notemplate.Model.Todo;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView rvNote;
    RecyclerView rvTodo;
    static com.ayckermann.notemplate.Adapter.adapterNote adapterNote;
    public static com.ayckermann.notemplate.Adapter.adapterTodo adapterTodo;
    FloatingActionButton btnAdd;
    Button btnNote, btnTodo, btnJadwal;

    public static ArrayList<Note> transferNote = new ArrayList<>();
    ArrayList<Note> listNote = new ArrayList<>();

    public static ArrayList<Todo> transferTodo = new ArrayList<>();

    public static ArrayList<HeadTodo> transferHeadTodo = new ArrayList<>();
    ArrayList<HeadTodo> listHeadTodo = new ArrayList<>();

    public static int idT =0;

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

        Log.e("count", String.valueOf(adapterNote.getItemCount()));

        listNote.add(new Note("NOTE","","" ));
        for (int i =0; i< transferNote.size();i++){
            listNote.add(new Note(transferNote.get(i).getJudul(), transferNote.get(i).getContent(), transferNote.get(i).getTanggal()));
        }
        adapterNote.notifyDataSetChanged();

        listHeadTodo.add(new HeadTodo(0,"TODO"));
        for (int i =0; i< transferHeadTodo.size();i++){

            listHeadTodo.add(new HeadTodo(transferHeadTodo.get(i).getId() ,transferHeadTodo.get(i).getJudul()));

        }
        adapterTodo.notifyDataSetChanged();


    }
    public void initComponent(){
        btnAdd = findViewById(R.id.btnAdd);
        btnNote = findViewById(R.id.btnNote);
        btnTodo =  findViewById(R.id.btnTodo);
        btnJadwal =  findViewById(R.id.btnJadwal);

        adapterNote =new adapterNote(listNote);
        rvNote = findViewById(R.id.rvNote);
        rvNote.setAdapter(adapterNote);
        rvNote.setLayoutManager(new LinearLayoutManager(this));

        adapterTodo =new adapterTodo(listHeadTodo);
        rvTodo = findViewById(R.id.rvTodo);
        rvTodo.setAdapter(adapterTodo);
        rvTodo.setLayoutManager(new LinearLayoutManager(this));

    }


}