package com.example.mp8;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    TextView textView, textEmail, textpassword;
    EditText IDfield, Pfield;
    Button loginbutton, createAccountbutton;

    FirebaseDatabase fbdb;
    DatabaseReference dbrf;

    FirebaseAuth mAuth;
    FirebaseUser user = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fbdb = FirebaseDatabase.getInstance();
        dbrf = fbdb.getReference();

        //TextView
        textView = (TextView) findViewById(R.id.textView);
        textEmail = (TextView) findViewById(R.id.textEmailID);
        textpassword = (TextView) findViewById(R.id.textpassword);

        //EditText
        IDfield = (EditText) findViewById(R.id.emailID);
        Pfield = (EditText) findViewById(R.id.password);

        //Buttons
        loginbutton = (Button) findViewById(R.id.Login);
        createAccountbutton = (Button) findViewById(R.id.CreateAccount);

        mAuth = FirebaseAuth.getInstance();

    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        user = mAuth.getCurrentUser();
    }


    public void Login(View view) {
        String email = IDfield.getText().toString();
        String password = Pfield.getText().toString();
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            // Display sign in successful
                            Toast.makeText(getApplicationContext(), "Authentication Successful.", Toast.LENGTH_SHORT).show();
                            user = mAuth.getCurrentUser();

                            toPull();

                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(getApplicationContext(), "Authentication failed.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    public void CreateAccount(View view) {
        String email = IDfield.getText().toString();
        String password = Pfield.getText().toString();
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            // Display sign in successful
                            Toast.makeText(getApplicationContext(), "Created Account", Toast.LENGTH_SHORT).show();
                            user = mAuth.getCurrentUser();

                            toPull();

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.d("SomethingWong", "CreateAccount resulting in failure", task.getException());
                            Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    public void toPull() {
        Intent switchToPull = new Intent(this, PullActivity.class);
        startActivity(switchToPull);
    }

}
