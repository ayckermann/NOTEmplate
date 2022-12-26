package com.ayckermann.notemplate.Template;

import android.app.Activity;

import android.app.AlertDialog;
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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.ayckermann.notemplate.MainActivity;
import com.ayckermann.notemplate.Model.Note;
import com.ayckermann.notemplate.Model.Todo;
import com.ayckermann.notemplate.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Map;


public class templateTodo extends Activity {

    FloatingActionButton btnSave, btnDelete;
    ImageButton btnAdd;
    EditText edtJudul,edtContent;
    LinearLayout linearLayout;

    ArrayList<EditText> editTexts = new ArrayList<EditText>();
    ArrayList<CheckBox> checkBoxes = new ArrayList<CheckBox>();
    ArrayList<Boolean> check = new ArrayList<>();
    ArrayList<String> text = new ArrayList<>();

    FirebaseUser user;
    FirebaseFirestore firestore;
    Todo todo;

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
        todo = (Todo) intent.getSerializableExtra("current_todo");

        if (todo!=null){

            for(int i=0; i< todo.listCheck.size();i++){
                addItem(todo.listCheck.get(i),todo.listText.get(i));
            }

            edtJudul.setText(todo.judul);
            btnSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String userId = user.getUid();
                    String judul = edtJudul.getText().toString();
                    getData();
//                    Todo todo1 = new Todo(userId,judul,check,text);
//                    Map map = todo1.asMap();
//
//                    firestore.collection("Todo").document(todo.uid).update(map)
//                            .addOnFailureListener(new OnFailureListener() {
//                                @Override
//                                public void onFailure(@NonNull Exception e) {
//                                    Log.e("Error edit FireStore ", e.getMessage());
//                                }
//                            });
                    Toast.makeText(templateTodo.this, "Edit todo still in development.", Toast.LENGTH_SHORT).show();

                    finish();
                    startActivity(new Intent(view.getContext(), MainActivity.class));
                }
            });

        }
        else{
            btnDelete.setVisibility(View.GONE);
            btnSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String userId = user.getUid();
                    String judul = edtJudul.getText().toString();

                    getData();
                    firestore.collection("Todo").document()
                            .set(new Todo(userId,judul,check,text))
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.e("Error add firestore ", e.getMessage());
                                }
                            });
                    finish();

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
        btnDelete = (FloatingActionButton) findViewById(R.id.btnDeleteTodo);
        edtJudul = (EditText) findViewById(R.id.edtJudulTodo);
        edtContent = (EditText) findViewById(R.id.edtContentTodo);

        linearLayout = findViewById(R.id.layoutItem);
        user = FirebaseAuth.getInstance().getCurrentUser();
        firestore = FirebaseFirestore.getInstance();
    }

    public void getData(){
        for(int i=0; i< editTexts.size();i++){
            check.add(checkBoxes.get(i).isChecked());
            text.add(editTexts.get(i).getText().toString());
        }
    }
//    public void setData(){
//        for(int i=0; i< editTexts.size();i++){
//            checkBoxes.get(i).setChecked(todo.listCheck.get(i));
//            editTexts.get(i).setText(todo.listText.get(i));
//        }
//    }
    public void deleteTodo(View v){
        AlertDialog.Builder alert = new AlertDialog.Builder(v.getContext(), android.R.style.Theme_DeviceDefault_Dialog_Alert);
        alert.setTitle("Delete Todo " + edtJudul.getText().toString() + " ?")
                .setPositiveButton("Cancel", (dialogInterface, i) -> dialogInterface.cancel())
                .setNegativeButton("Yes", (dialogInterface, i) -> {

                    firestore.collection("Todo").document(todo.uid)
                            .delete()
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.e("Error delete", e.getMessage());
                                }
                            });
                    finish();

                });
        AlertDialog dialog = alert.create();
        dialog.show();
    }



}
