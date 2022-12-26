package com.ayckermann.notemplate;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ayckermann.notemplate.Adapter.adapterJadwal;
import com.ayckermann.notemplate.Adapter.adapterNote;

import com.ayckermann.notemplate.Adapter.adapterTodo;
import com.ayckermann.notemplate.Model.Jadwal;
import com.ayckermann.notemplate.Model.Note;
import com.ayckermann.notemplate.Model.Todo;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;
import com.google.firebase.firestore.Query;

public class MainActivity extends AppCompatActivity {

    RecyclerView rvNote;
    RecyclerView rvTodo;
    RecyclerView rvJadwal;
    adapterNote adapterNote;
    adapterJadwal adapterJadwal;
    adapterTodo adapterTodo;

    FloatingActionButton btnAdd;
    FloatingActionButton btnLogOut;
    Button btnNote, btnTodo, btnJadwal;


    FirebaseUser user;
    FirebaseAuth mAuth;
    FirebaseFirestore firestore;

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        intent = getIntent();

        initComponent();
        initFab();


    }

    protected void onResume() {
        super.onResume();

            //Note
            Query query1 = firestore.collection("Note")
                    .whereEqualTo("userId", user.getUid());

            FirestoreRecyclerOptions<Note> options1 = new FirestoreRecyclerOptions.Builder<Note>()
                    .setQuery(query1, Note.class).build();

            adapterNote = new adapterNote(options1);
            rvNote.setAdapter(adapterNote);
            adapterNote.startListening();


            //Jadwal
            Query query2 = firestore.collection("Jadwal")
                    .whereEqualTo("userId", user.getUid());

            FirestoreRecyclerOptions<Jadwal> options2 = new FirestoreRecyclerOptions.Builder<Jadwal>()
                    .setQuery(query2, Jadwal.class).build();

            adapterJadwal = new adapterJadwal(options2);
            rvJadwal.setAdapter(adapterJadwal);
            adapterJadwal.startListening();

        //Jadwal
        Query query3 = firestore.collection("Todo")
                .whereEqualTo("userId", user.getUid());

        FirestoreRecyclerOptions<Todo> options3 = new FirestoreRecyclerOptions.Builder<Todo>()
                .setQuery(query3, Todo.class).build();

        adapterTodo = new adapterTodo(options3);
        rvTodo.setAdapter(adapterTodo);
        adapterTodo.startListening();



    }

    @Override
    protected void onStop() {
        super.onStop();
//        adapterNote.stopListening();
    }

    public void initComponent(){
        btnAdd = findViewById(R.id.btnAdd);
        btnLogOut = findViewById(R.id.btnLogOut);
        btnNote = findViewById(R.id.btnNote);
        btnTodo =  findViewById(R.id.btnTodo);
        btnJadwal =  findViewById(R.id.btnJadwal);

        rvNote = findViewById(R.id.rvNote);
        rvNote.setLayoutManager(new LinearLayoutManager(this));

        rvJadwal = findViewById(R.id.rvJadwal);
        rvJadwal.setLayoutManager(new LinearLayoutManager(this));

        rvTodo = findViewById(R.id.rvTodo);
        rvTodo.setLayoutManager(new LinearLayoutManager(this));

        firestore = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();

        FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder()
                .setPersistenceEnabled(true)
                .setCacheSizeBytes(FirebaseFirestoreSettings.CACHE_SIZE_UNLIMITED)
                .build();
        firestore.setFirestoreSettings(settings);

    }

    public void initFab(){
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, selectTemplate.class));
            }
        });

        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isNetworkConnected()){
                    mAuth.signOut();
                    startActivity(new Intent(getBaseContext(), Login.class)
                            .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                            .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK));
                }
                else{
                    Toast.makeText(MainActivity.this, "Network is not connected. Can't Logout.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }

}