package com.example.mp8;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class PullActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    Button Q1Btn, Q2Btn, pushBtn, signOutBtn;
    EditText IDfield;

    FirebaseDatabase fbdb;
    DatabaseReference dbrf;

    FirebaseAuth mAuth;
    FirebaseUser user;

    int Studentid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pull);

        //Buttons
        Q1Btn = findViewById(R.id.Q1Button);
        Q2Btn = findViewById(R.id.Q2Button);
        pushBtn = findViewById(R.id.pushButton);
        signOutBtn = findViewById(R.id.SignOutBtn);

        //RecyclerView
        recyclerView = findViewById(R.id.recyclerView);

        //EditText
        IDfield = findViewById(R.id.editText);

        fbdb = FirebaseDatabase.getInstance();
        dbrf = fbdb.getReference();

        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();

    }

    public void QueryOne(View view) {
        Studentid = Integer.parseInt(IDfield.getText().toString());
        DatabaseReference query = dbrf.child("simpsons/students/");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        })
    }

    public void QueryTwo(View view) {

    }

    public void PushClick(View view) {
        Intent toPush = new Intent(this, PushActivity.class);
    }

    public void SignOut(View view) {
        mAuth.signOut();
        user = null;
        Log.d("SignOut:", "Log Out");
        Toast.makeText(getApplicationContext(), "Log Out", Toast.LENGTH_SHORT).show();
        finish();
    }

}
