package com.ayckermann.notemplate.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.ayckermann.notemplate.MainActivity;
import com.ayckermann.notemplate.Model.Note;
import com.ayckermann.notemplate.R;
import com.ayckermann.notemplate.Template.templateNote;

import java.util.ArrayList;

public class adapterNote extends RecyclerView.Adapter<adapterNote.ViewHolder> {

    ArrayList<Note> listNote;
    public Context context;

    public adapterNote(ArrayList<Note> listNote){
        this.listNote =listNote;
    }

    @NonNull
    @Override
    public adapterNote.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        context = parent.getContext();
        LayoutInflater inflater =LayoutInflater.from(context);
        ViewHolder holder = new ViewHolder(inflater.inflate(R.layout.item_note, parent, false));

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull adapterNote.ViewHolder holder, int position) {
        Note note = listNote.get(position);
        holder.txtJudul.setText(note.getJudul());
        holder.txtContent.setText(note.getContent());

        if(position == 0){
            holder.txtJudul.setGravity(Gravity.CENTER);
            holder.txtContent.setVisibility(View.GONE);
        }
        else{
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent();

                    intent = new Intent(view.getContext(), templateNote.class);
                    intent.putExtra("idN", holder.getAdapterPosition() -1);
                    intent.putExtra("judulN", note.getJudul());
                    intent.putExtra("contentN", note.getContent());
                    intent.putExtra("tanggalN", note.getTanggal());

                    view.getContext().startActivity(intent);
                }
            });

            holder.itemView.setOnLongClickListener(view -> {

                int p = position;
                AlertDialog.Builder alert = new AlertDialog.Builder(view.getContext());
                alert.setTitle("Delete Note " + note.getJudul() + " ?")
                        .setPositiveButton("Cancel", (dialogInterface, i) -> dialogInterface.cancel())
                        .setNegativeButton("Yes", (dialogInterface, i) -> {

                            MainActivity.transferNote.remove(p-1);
                            listNote.remove(p);
                            notifyItemRemoved(p);
                            notifyItemRangeChanged(p, listNote.size());



                        });
                AlertDialog dialog = alert.create();
                dialog.show();

                return true;
            });
        }

    }

    @Nullable
    @Override
    public int getItemCount() {
        return listNote.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtJudul, txtContent;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtJudul = itemView.findViewById(R.id.txtJudulN);
            txtContent = itemView.findViewById(R.id.txtContentN);

        }
    }


}
