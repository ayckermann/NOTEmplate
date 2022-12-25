package com.ayckermann.notemplate;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import androidx.annotation.Nullable;

import com.ayckermann.notemplate.Template.templateJadwal;
import com.ayckermann.notemplate.Template.templateNote;
import com.ayckermann.notemplate.Template.templateTodo;

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

        WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
        layoutParams.dimAmount = 0.6f;

        int width = dm.widthPixels;
        int height = dm.heightPixels;


        getWindow().setLayout((int)(width*0.8),(int)(height*0.4));

        getWindow().setBackgroundDrawableResource(R.drawable.rounded_item);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        getWindow().setAttributes(layoutParams);


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
