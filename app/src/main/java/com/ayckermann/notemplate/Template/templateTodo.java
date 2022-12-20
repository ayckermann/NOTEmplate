package com.ayckermann.notemplate.Template;

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

import com.ayckermann.notemplate.MainActivity;
import com.ayckermann.notemplate.Model.HeadTodo;
import com.ayckermann.notemplate.R;
import com.ayckermann.notemplate.Model.Todo;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


public class templateTodo extends Activity {

    FloatingActionButton btnSave;
    ImageButton btnAdd;
    EditText edtJudul,edtContent;
    LinearLayout linearLayout;

    ArrayList<EditText> editTexts = new ArrayList<EditText>();
    ArrayList<CheckBox> checkBoxes = new ArrayList<CheckBox>();
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
        if (intent.hasExtra("judulT")){

            int id2 = intent.getIntExtra("idT",0);
            String judul = intent.getStringExtra("judulT");

            edtJudul.setText(judul);

            for(int i = 0; i <MainActivity.transferHeadTodo.size();i++){
                if(MainActivity.transferTodo.get(i).getId() == id2){
                    addItem(intent.getBooleanExtra("checkT"+i, false), intent.getStringExtra("textT"+i));
                }

            }

            btnSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String judul2 = edtJudul.getText().toString();

                    MainActivity.transferHeadTodo.set(id2, new HeadTodo(id2, judul2));
                    setData(id2);
                    startActivity(new Intent(view.getContext(), MainActivity.class));
                }
            });
        }
        else{
            btnSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String judul = edtJudul.getText().toString();

                    getData(MainActivity.idT);
                    MainActivity.transferHeadTodo.add(new HeadTodo(MainActivity.idT, judul ));
                    MainActivity.idT++;

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
        checkBoxes.add(checkBox);
        checkBox.setChecked(check);
        layoutItem.addView(checkBox);

        final EditText editText = new EditText(this);
        editTexts.add(editText);
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
    }
    public void initComponent(){
        btnSave = (FloatingActionButton) findViewById(R.id.btnSaveTodo);
        btnAdd = (ImageButton) findViewById(R.id.btnAddTodo);
        edtJudul = (EditText) findViewById(R.id.edtJudulTodo);
        edtContent = (EditText) findViewById(R.id.edtContentTodo);


        linearLayout = findViewById(R.id.layoutItem);
    }

    public void getData(int id3){
        for(int i=0; i< editTexts.size();i++){
            MainActivity.transferTodo.add (new Todo(id3,checkBoxes.get(i).isChecked(),editTexts.get(i).getText().toString()));
            Log.e("ID3", Integer.toString(id3));
        }
    }
    public void setData(int id3){
        for(int i=0; i< editTexts.size();i++){
            if(MainActivity.transferTodo.get(i).getId() == id3){
                MainActivity.transferTodo.set(i,new Todo(id3, checkBoxes.get(i).isChecked(), editTexts.get(i).getText().toString()));
            }
        }
    }



}
