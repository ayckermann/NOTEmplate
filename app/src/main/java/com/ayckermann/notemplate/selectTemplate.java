package com.ayckermann.notemplate;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import androidx.annotation.Nullable;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class selectTemplate extends Activity {


    Button btnNote, btnTodo, btnJadwal;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        btnNote = findViewById(R.id.btnNote);
        btnTodo = findViewById(R.id.btnTodo);
        btnJadwal = findViewById(R.id.btnJadwal);

        setContentView(R.layout.add);


        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*0.8),(int)(height*0.4));
        getWindow().setBackgroundDrawableResource(R.drawable.rounded_item);

    }

    public void templateNote(View view) {
        startActivity(new Intent(view.getContext(), templateNote.class));
    }
    public void templateTodo(View view) {
        startActivity(new Intent(view.getContext(), templateTodo.class));
    }
    public void templateJadwal(View view) {
        startActivity(new Intent(view.getContext(), templateJadwal.class));
    }




}