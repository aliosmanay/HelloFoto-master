package com.aoaydesign.bitirmeprojesi.puzzlechat;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    EditText userMail,userPassword;
    Button girisYap;
    TextView uyari;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userMail=(EditText) findViewById(R.id.email);
        userPassword=(EditText) findViewById(R.id.password);
        girisYap=(Button) findViewById(R.id.girisButton);
        uyari=(TextView) findViewById(R.id.uyari);

        mAuth=FirebaseAuth.getInstance();

        FirebaseUser user = mAuth.getCurrentUser();
        if(user!=null){
            Intent intent = new Intent(MainActivity.this,TabActivity.class);
            startActivity(intent);
        }


        girisYap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userEmail=userMail.getText().toString();
                String userSifre=userPassword.getText().toString();
                mAuth.signInWithEmailAndPassword(userEmail,userSifre).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Intent authIntent=new Intent(getApplicationContext(),TabActivity.class);
                            startActivity(authIntent);
                            finish();
                        }
                        else{
                            Toast.makeText(getApplicationContext(),"Giriş yapılamadı.",Toast.LENGTH_SHORT).show();}}
                });
            }});
        uyari.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent uyariIntent=new Intent(getApplicationContext(),kayitActivity.class);
                startActivity(uyariIntent);
                finish();
            }
        });

    }
}
