package com.example.signinup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {
    EditText email;
    EditText password;
    Button register;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        email=findViewById(R.id.email);
        password=findViewById(R.id.password);
        register=findViewById(R.id.register);
        auth=FirebaseAuth.getInstance();
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailtxt=email.getText().toString();
                String passwordtxt=password.getText().toString();
                if(TextUtils.isEmpty(emailtxt) || TextUtils.isEmpty(passwordtxt))
                {
                    Toast.makeText(getApplicationContext(),"PLEASE ENTER CORRECTLY",Toast.LENGTH_SHORT).show();
                }
                else if(passwordtxt.length()<6)
                {
                    Toast.makeText(getApplicationContext(),"PASSWORD TOO SHORT",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    RegisterUser(emailtxt,passwordtxt);
                }
            }
        });
    }

    private void RegisterUser(String email, String password) {
        auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(getApplicationContext(),"REGISTERING USERS SUCCESSFULLY",Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(getApplicationContext(),OriginalActivity.class);
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"REGISTRATION FAILED",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}