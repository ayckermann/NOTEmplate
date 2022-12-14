package com.ayckermann.notemplate;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class listAdapter extends RecyclerView.Adapter<listAdapter.ViewHolder> {


    ArrayList<Model> listModel;

    public listAdapter(ArrayList<Model> listModel){

        this.listModel =listModel;
    }

    @NonNull
    @Override
    public listAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater =LayoutInflater.from(context);
        ViewHolder holder = new ViewHolder(inflater.inflate(R.layout.item, parent, false));

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull listAdapter.ViewHolder holder, int position) {
        Model model = listModel.get(position);
        holder.txtJudul.setText(model.getJudul());
        holder.txtContent.setText(model.getContent());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                if(model.template.equals("Note")){
                    intent = new Intent(view.getContext(), templateNote.class);
                    intent.putExtra("id", model.getId());
                    intent.putExtra("judul", model.getJudul());
                    intent.putExtra("content", model.getContent());
                    intent.putExtra("template", model.getTemplate());
                }
                view.getContext().startActivity(intent);
            }
        });

    }

    @Nullable
    @Override
    public int getItemCount() {
        return listModel.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView txtJudul, txtContent;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtJudul = itemView.findViewById(R.id.txtJudul);
            txtContent = itemView.findViewById(R.id.txtContent);

        }
    }


}
