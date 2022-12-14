package com.ayckermann.notemplate;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class templateTodo extends Activity {

    RecyclerView recyclerView;
    todoAdapter todoAdapter;

    FloatingActionButton btnSave;
    ImageButton btnAdd;
    EditText edtJudul,edtContent;

    ArrayList<Todo> listTodo = new ArrayList<>();
    static ArrayList<Todo> transferTodo = new ArrayList<>();
    int id =1;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.template_todo);

        for(int i =0; i<listTodo.size();i++){
            transferTodo.add(new Todo(listTodo.get(i).getId(),listTodo.get(i).getJudul(), listTodo.get(i).getContent(),listTodo.get(i).getTemplate(),listTodo.get(i).isCheck()));
        }
        initComponent();
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String judul = edtJudul.getText().toString();
                String content = edtContent.getText().toString();

                listTodo.add(new Todo(id,judul,content, "Todo", false ));
                id++;
                todoAdapter.notifyDataSetChanged();
            }
        });
    }

    public void initComponent(){
        btnSave = (FloatingActionButton) findViewById(R.id.btnSaveTodo);
        btnAdd = (ImageButton) findViewById(R.id.btnAddTodo);
        edtJudul = (EditText) findViewById(R.id.edtJudulTodo);
        edtContent = (EditText) findViewById(R.id.edtContentTodo);

        recyclerView = (RecyclerView) findViewById(R.id.rvTodo);
        todoAdapter = new todoAdapter(listTodo);
        recyclerView.setAdapter(todoAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);
    }
}
