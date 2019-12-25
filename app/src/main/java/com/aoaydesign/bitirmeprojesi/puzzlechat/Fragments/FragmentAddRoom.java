package com.aoaydesign.bitirmeprojesi.puzzlechat.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.aoaydesign.bitirmeprojesi.puzzlechat.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;

public class FragmentAddRoom extends Fragment {

    public FragmentAddRoom() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view;

        view = inflater.inflate(R.layout.fragment_add_room, container, false);
        final EditText odaIsmi = view.findViewById(R.id.oda_ismi);
        Button btnOdaEkle = view.findViewById(R.id.btn_odaEkle);




        btnOdaEkle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String oda_ismi = odaIsmi.getText().toString();
                FirebaseDatabase.getInstance().getReference("chats").child(oda_ismi).setValue("").addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isComplete()){
                            Toast.makeText(getContext(), "Oda ekleme başarılı", Toast.LENGTH_LONG).show();
                        }
                        else{
                            Toast.makeText(getContext(), "Oda eklenemedi", Toast.LENGTH_LONG).show();
                        }
                    }
                });
                odaIsmi.setText("");
            }
        });


        return view;
    }
}
