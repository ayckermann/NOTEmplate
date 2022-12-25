package com.ayckermann.notemplate;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Register extends AppCompatActivity {

    EditText edtRegEmail, edtRegPassword, edtRegConfirm;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        edtRegEmail = findViewById(R.id.edtRegEmail);
        edtRegPassword = findViewById(R.id.edtRegPassword);
        edtRegConfirm = findViewById(R.id.edtRegConfirm);
        mAuth = FirebaseAuth.getInstance();

    }

    public void register(View v){
        String email, password, confirm;
        email = edtRegEmail.getText().toString();
        password = edtRegPassword.getText().toString();
        confirm = edtRegConfirm.getText().toString();

        if( TextUtils.isEmpty(edtRegEmail.getText()) | TextUtils.isEmpty(edtRegPassword.getText()) |TextUtils.isEmpty(edtRegPassword.getText()) ){
            if(TextUtils.isEmpty(edtRegEmail.getText())){
                edtRegEmail.setError("Input your email here!");
            }
            else if(TextUtils.isEmpty(edtRegPassword.getText())){
                edtRegPassword.setError("Create password!");
            }
            else {
                edtRegConfirm.setError("Re-enter your password!");
            }
        }
        else{
            if(password.equals(confirm)){
                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    startActivity(new Intent(getApplicationContext(), MainActivity.class)
                                            .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                                            .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK));
                                }
                                else{
                                    Log.e("Auth error", "errror regristasi", task.getException());
                                }
                            }
                        });
            }
            else {
                Toast.makeText(this,"You put different password and confirmation, try again!", Toast.LENGTH_LONG).show();
            }
        }

    }

    public void toLogin(View v){
        startActivity(new Intent(this, Login.class));
    }
}
