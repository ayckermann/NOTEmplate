package com.ayckermann.notemplate;

import static androidx.core.view.ViewCompat.setBackground;

import android.app.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;

import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;

import android.widget.CheckBox;

import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


public class templateTodo extends Activity {

    FloatingActionButton btnSave;
    ImageButton btnAdd;
    EditText edtJudul,edtContent;
    LinearLayout linearLayout;

    static ArrayList<Todo> listTodo = new ArrayList<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.template_todo);

        initComponent();
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String content = edtContent.getText().toString();

                addItem(false,content);

            }
        });



        Intent intent = getIntent();
        getIntent().getExtras();
        if (intent.hasExtra("judul")){

            int id = intent.getIntExtra("id",0);
            String judul = intent.getStringExtra("judul");
            String content = intent.getStringExtra("content");

            String template = intent.getStringExtra("template");

            edtJudul.setText(judul);
            edtContent.setText(content);

            Log.e("size", String.valueOf(listTodo.size()));
            for(int i =0; i <listTodo.size();i++){
                addItem(intent.getBooleanExtra("check"+1, false), intent.getStringExtra("text"+1));
            }

            btnSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String judul2 = edtJudul.getText().toString();
                    String content2 = edtContent.getText().toString();

                    MainActivity.transfer.set(id,new Model(judul2,content2,"Todo"));

                    startActivity(new Intent(view.getContext(), MainActivity.class));
                }
            });
        }
        else{
            btnSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String judul = edtJudul.getText().toString();
                    String content = edtContent.getText().toString();

                    MainActivity.transfer.add(new Model(judul,content,"Todo"));

                    startActivity(new Intent(view.getContext(), MainActivity.class));

                }
            });
        }
    }

    public void addItem(Boolean check, String text){
        LinearLayout layoutItem = new LinearLayout(this);
        layoutItem.setOrientation(LinearLayout.HORIZONTAL);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT

        );

        layoutItem.setLayoutParams(params);
        layoutItem.setBackground(null);



        final CheckBox checkBox =new CheckBox(this);
        checkBox.setChecked(check);
        layoutItem.addView(checkBox);

        final EditText editText = new EditText(this);
        LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT,
                1.0f
        );


        editText.setLayoutParams(param);

        editText.setInputType(InputType.TYPE_TEXT_VARIATION_PERSON_NAME);
        editText.setText(text);
        editText.setTextSize(TypedValue.COMPLEX_UNIT_PT, 8);
        editText.setTypeface(Typeface.SERIF, Typeface.BOLD);
        editText.setTextColor(Color.BLACK);
        editText.setPressed(true);
        editText.setCursorVisible(true);
        editText.setSelection(editText.length());

        editText.setBackground(null);

        layoutItem.addView(editText);

        final ImageButton delete = new ImageButton(this);


        delete.setBackground(getDrawable(R.drawable.add22));
        float pixels =  30 * this.getResources().getDisplayMetrics().density;
        LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams((int)pixels,(int) pixels);
        params1.setMargins(30,0,25,0);


        delete.setLayoutParams(params1);

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                layoutItem.setVisibility(View.GONE);
            }
        });
        layoutItem.addView(delete);

        linearLayout.addView(layoutItem);
        listTodo.add(new Todo(checkBox.isChecked() , editText.getText().toString()));
    };
    public void initComponent(){
        btnSave = (FloatingActionButton) findViewById(R.id.btnSaveTodo);
        btnAdd = (ImageButton) findViewById(R.id.btnAddTodo);
        edtJudul = (EditText) findViewById(R.id.edtJudulTodo);
        edtContent = (EditText) findViewById(R.id.edtContentTodo);


        linearLayout = findViewById(R.id.layoutItem);


    }






}
