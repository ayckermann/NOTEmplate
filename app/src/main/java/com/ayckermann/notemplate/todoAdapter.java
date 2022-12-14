package com.ayckermann.notemplate;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class todoAdapter extends RecyclerView.Adapter<todoAdapter.ViewHolder> {

    ArrayList<Todo> listTodo;

    public todoAdapter(ArrayList<Todo> listTodo){
        this.listTodo =listTodo;
    }

    @NonNull
    @Override
    public todoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater =LayoutInflater.from(context);
        ViewHolder holder = new ViewHolder(inflater.inflate(R.layout.item_todo, parent, false));


        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull todoAdapter.ViewHolder holder, int position) {
        Todo todo = listTodo.get(position);
        holder.edtItemTodo.setText(todo.getContent());
        holder.checkTodo.setChecked(todo.isCheck());

        holder.btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "Inputan salah", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Nullable
    @Override
    public int getItemCount() {
        return listTodo.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        CheckBox checkTodo;
        EditText edtItemTodo;
        Button btnRemove;
            public ViewHolder(@NonNull View itemView){
                super(itemView);

                checkTodo = itemView.findViewById(R.id.checkTodo);
                edtItemTodo = itemView.findViewById(R.id.edtItemTodo);
                btnRemove =itemView.findViewById(R.id.btnRemoveTodo);
        }

    }
}
