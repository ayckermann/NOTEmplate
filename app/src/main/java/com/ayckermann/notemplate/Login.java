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

public class Login extends AppCompatActivity {

    EditText edtEmail, edtPassword;

    FirebaseAuth mAuth;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassword);

        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        if(user!=null){
            startActivity(new Intent(this, MainActivity.class)
                    .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK));
        }

    }
    public void login(View v) {
        String emailUser = edtEmail.getText().toString();
        String password = edtPassword.getText().toString();

        if( TextUtils.isEmpty(edtEmail.getText()) | TextUtils.isEmpty(edtPassword.getText())){
            if(TextUtils.isEmpty(edtEmail.getText())){
                edtEmail.setError("Input your email here!");
            }
            else {
                edtPassword.setError("Input your password here!");
            }
        }
        else{
            mAuth.signInWithEmailAndPassword(emailUser, password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                startActivity(new Intent(getApplicationContext(), MainActivity.class)
                                        .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK));
                            }
                            else {
                                Log.e("Auth error", "errror lgin", task.getException());
                                Toast.makeText(Login.this, "Username or password incorrect.", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }

    public void toRegister(View v){
        startActivity(new Intent(this, Register.class));
    }
}
