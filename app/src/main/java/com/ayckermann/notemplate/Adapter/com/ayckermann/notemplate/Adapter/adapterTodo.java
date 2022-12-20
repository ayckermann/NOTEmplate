package com.ayckermann.notemplate.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.text.InputType;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ayckermann.notemplate.MainActivity;
import com.ayckermann.notemplate.Model.HeadTodo;
import com.ayckermann.notemplate.Model.Note;
import com.ayckermann.notemplate.Model.Todo;
import com.ayckermann.notemplate.R;
import com.ayckermann.notemplate.Template.templateNote;

import java.util.ArrayList;

public class adapterTodo extends RecyclerView.Adapter<adapterTodo.ViewHolder> {

    ArrayList<HeadTodo> listHeadTodo = new ArrayList<>();

    public adapterTodo(ArrayList listHeadTodo){
        this.listHeadTodo = listHeadTodo;
    }

    @NonNull
    @Override
    public adapterTodo.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater =LayoutInflater.from(context);
        adapterTodo.ViewHolder holder = new ViewHolder(inflater.inflate(R.layout.item_todo, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull adapterTodo.ViewHolder holder, int position) {
        HeadTodo headTodo = listHeadTodo.get(position);
        holder.judul.setText(headTodo.getJudul());

        Log.e("POSISI", String.valueOf(position));

        for(int i =0; i < MainActivity.transferTodo.size(); i++){
            if(holder.getAdapterPosition()-1 == MainActivity.transferTodo.get(i).getId()){
                holder.dynamicView(holder.itemView, MainActivity.transferTodo.get(i).isCheck(), MainActivity.transferTodo.get(i).getText());
            }
        }

        if(position == 0){
            holder.judul.setGravity(Gravity.CENTER);
        }
        else{
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent();

                    intent = new Intent(view.getContext(), adapterTodo.class);
                    intent.putExtra("idT", holder.getAdapterPosition() -1);
                    intent.putExtra("judulT", headTodo.getJudul());



                    view.getContext().startActivity(intent);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return listHeadTodo.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView judul;
        LinearLayout listItemTodo;
        public ViewHolder(@NonNull View itemView){
            super(itemView);
            judul = itemView.findViewById(R.id.txtJudulT);

            listItemTodo = itemView.findViewById(R.id.listItemTodo);

        }
        public void dynamicView(View view, Boolean check, String text ){
            LinearLayout layoutItem = new LinearLayout(view.getContext());
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT

            );
            layoutItem.setLayoutParams(params);
            layoutItem.setBackground(null);

            final CheckBox checkBox =new CheckBox(view.getContext());
            checkBox.setEnabled(false);
            checkBox.setChecked(check);
            layoutItem.addView(checkBox);

            final TextView textView = new TextView(view.getContext());
            textView.setText(text);
            layoutItem.addView(textView);

            listItemTodo.addView(layoutItem);

        }
    }



}
