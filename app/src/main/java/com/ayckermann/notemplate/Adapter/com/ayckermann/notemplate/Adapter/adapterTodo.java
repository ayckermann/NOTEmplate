package com.ayckermann.notemplate.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.ayckermann.notemplate.Model.Todo;
import com.ayckermann.notemplate.R;

import com.ayckermann.notemplate.Template.templateTodo;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class adapterTodo extends FirestoreRecyclerAdapter<Todo, adapterTodo.ViewHolder> {
    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirestoreRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public adapterTodo(@NonNull FirestoreRecyclerOptions<Todo> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull adapterTodo.ViewHolder holder, int position, @NonNull Todo model) {
        holder.judul.setText(model.judul);

        for(int i =0 ; i< model.listCheck.size();i++){
            holder.dynamicView(holder.itemView, model.listCheck.get(i), model.listText.get(i));
        }
        String uid = getSnapshots().getSnapshot(position).getId();
        model.uid = uid;

        holder.listItemTodo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = holder.listItemTodo.getContext();
                Intent intent = new Intent(context, templateTodo.class);
                intent.putExtra("current_todo", (Todo) model);

                context.startActivity(intent);
            }
        });
    }

    @NonNull
    @Override
    public adapterTodo.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater =LayoutInflater.from(context);
        ViewHolder holder = new ViewHolder(inflater.inflate(R.layout.item_todo, parent, false));
        return holder;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
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
            checkBox.setHighlightColor(Color.BLACK);
            checkBox.setChecked(check);
            layoutItem.addView(checkBox);

            final TextView textView = new TextView(view.getContext());
            textView.setText(text);
            textView.setTextColor(Color.BLACK);
            layoutItem.addView(textView);

            listItemTodo.addView(layoutItem);

        }
    }
}
