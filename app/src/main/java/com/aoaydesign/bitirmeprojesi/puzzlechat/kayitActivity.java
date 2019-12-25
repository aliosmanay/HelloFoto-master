package com.aoaydesign.bitirmeprojesi.puzzlechat;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class kayitActivity extends AppCompatActivity {
    EditText userMail,userPassword;
    Button kayitOl;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kayit);

        userMail=(EditText) findViewById(R.id.editMail);
        userPassword=(EditText) findViewById(R.id.editPassword);
        kayitOl=(Button) findViewById(R.id.kayitol);


        mAuth=FirebaseAuth.getInstance();

        kayitOl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sifre = userPassword.getText().toString();
                String email= userMail.getText().toString();

                mAuth.createUserWithEmailAndPassword(email,sifre).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(getApplicationContext(),"Kayıt Başarılı",Toast.LENGTH_SHORT).show();
                            Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                            startActivity(intent);
                            finish();

                        }
                        else{
                            Toast.makeText(getApplicationContext(),"Kayıt Başarısız",Toast.LENGTH_SHORT).show();}
                    }
                });


            }
        });

        }

    }

