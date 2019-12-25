package com.aoaydesign.bitirmeprojesi.puzzlechat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class odaActivity extends AppCompatActivity {
    ListView chatOdalari;
    EditText odaAdi;
    Button odaEkle;
    ArrayList<String>chatOdalariList= new ArrayList<>();
    FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oda);

        chatOdalari= (ListView) findViewById(R.id.listChat);
        odaAdi=(EditText) findViewById(R.id.chatOdaAdi);
        odaEkle=(Button) findViewById(R.id.odaEkle);

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, chatOdalariList);

        database = FirebaseDatabase.getInstance();
        final DatabaseReference dbRef = database.getReference("chats");
        odaEkle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String oda=odaAdi.getText().toString();
                dbRef.child(oda).setValue("");
                odaAdi.setText("");
            }
        });

        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                chatOdalariList.clear();
                for(DataSnapshot ds:dataSnapshot.getChildren()){
                    chatOdalariList.add(ds.getKey());
                }
                chatOdalari.setAdapter(adapter);

                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        chatOdalari.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String secilenOda=chatOdalariList.get(position);
                Intent odaIntent=new Intent(getApplicationContext(),chatYapActivity.class);
                odaIntent.putExtra("odaKey",secilenOda);
                startActivity(odaIntent);
            }
        });

    }
}
