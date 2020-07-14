package com.example.beintouch;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;


public class Dashboard extends AppCompatActivity {
    private Button btn;
    public TextView firstname,lastname;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        //set firstname and lastname
        firstname = findViewById(R.id.textfirstname);
        lastname = findViewById(R.id.textlastname);


        // show firstname

        shownames();
        btn = findViewById(R.id.logout);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(Dashboard.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void shownames() {
        Intent intent = getIntent();
        String fname = intent.getStringExtra("firstname");
        String lname = intent.getStringExtra("lastname");
        firstname.setText(fname);
        lastname.setText(lname);

    }
}