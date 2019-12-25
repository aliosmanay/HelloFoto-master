package com.aoaydesign.bitirmeprojesi.puzzlechat.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.aoaydesign.bitirmeprojesi.puzzlechat.R;
import com.aoaydesign.bitirmeprojesi.puzzlechat.chatYapActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FragmentAllRooms extends Fragment {

    FirebaseDatabase database;
    List<String> chatOdalariList;
    ListView chatOdalari;

    public FragmentAllRooms() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_all_chat_rooms, container, false);
        chatOdalariList = new ArrayList<>();
        chatOdalari = view.findViewById(R.id.list_all_chats);
        database = FirebaseDatabase.getInstance();
        DatabaseReference dbRef = database.getReference("chats");

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, android.R.id.text1, chatOdalariList);

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
                Intent odaIntent=new Intent(getContext(),chatYapActivity.class);
                odaIntent.putExtra("odaKey",secilenOda);
                startActivity(odaIntent);
            }
        });

        return view;
    }
}
