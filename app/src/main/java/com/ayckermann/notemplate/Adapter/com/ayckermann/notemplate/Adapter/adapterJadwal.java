package com.ayckermann.notemplate.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ayckermann.notemplate.Model.Jadwal;

import com.ayckermann.notemplate.R;
import com.ayckermann.notemplate.Template.templateJadwal;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class adapterJadwal extends FirestoreRecyclerAdapter<Jadwal, adapterJadwal.ViewHolder> {
    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirestoreRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public adapterJadwal(@NonNull FirestoreRecyclerOptions<Jadwal> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull adapterJadwal.ViewHolder holder, int position, @NonNull Jadwal model) {
        holder.txtJudul.setText(model.judul);

        String uid = getSnapshots().getSnapshot(position).getId();
        model.uid = uid;

        holder.itemLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = holder.itemLayout.getContext();
                Intent intent = new Intent(context, templateJadwal.class);
                intent.putExtra("current_jadwal", (Jadwal) model);

                context.startActivity(intent);
            }
        });
    }


    @NonNull
    @Override
    public adapterJadwal.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        adapterJadwal.ViewHolder holder = new ViewHolder(inflater.inflate(R.layout.item_jadwal,parent, false));
        return holder;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtJudul;
        LinearLayout itemLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtJudul = itemView.findViewById(R.id.txtJudulJ);

            itemLayout = itemView.findViewById(R.id.itemJadwal);

        }
    }
}
