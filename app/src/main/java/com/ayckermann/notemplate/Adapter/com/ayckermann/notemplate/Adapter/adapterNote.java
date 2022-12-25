package com.ayckermann.notemplate.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.ayckermann.notemplate.Model.Note;
import com.ayckermann.notemplate.R;
import com.ayckermann.notemplate.Template.templateNote;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class adapterNote extends FirestoreRecyclerAdapter<Note, adapterNote.ViewHolder> {
    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirestoreRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public adapterNote(@NonNull FirestoreRecyclerOptions<Note> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull Note model) {
        holder.txtJudul.setText(model.judul);
        holder.txtContent.setText(model.content);

        String uid = getSnapshots().getSnapshot(position).getId();
        model.uid = uid;


        holder.itemLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = holder.itemLayout.getContext();
                Intent intent = new Intent(context, templateNote.class);
                intent.putExtra("current_note", (Note) model);

                context.startActivity(intent);
            }
        });
    }


    @NonNull
    @Override
    public adapterNote.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        ViewHolder holder = new ViewHolder(inflater.inflate(R.layout.item_note,parent, false));
        return holder;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtJudul, txtContent;
        LinearLayout itemLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtJudul = itemView.findViewById(R.id.txtJudulN);
            txtContent = itemView.findViewById(R.id.txtContentN);
            itemLayout = itemView.findViewById(R.id.itemNote);

        }
    }
}
