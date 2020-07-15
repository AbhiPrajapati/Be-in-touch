package com.example.beintouch;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class Dashboard extends AppCompatActivity {
    private Button btn;
    public TextView firstname,lastname;
    public LinearLayout profile;
    private FirebaseAuth mAuth;
    DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        //set firstname and lastname
        firstname = findViewById(R.id.textfirstname);
        lastname = findViewById(R.id.textlastname);
        profile = findViewById(R.id.userProfile);


        //userProfile on click listner

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),EditProfile.class);
                startActivity(i);
            }
        });

        // show firstname
        shownames();


        //data send to editprofile
        //sendDatatoEditprofile();



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

//    private void sendDatatoEditprofile() {
//        String fname = firstname.getText().toString();
//        String lname = lastname.getText().toString();
//        String email = emailid.getText().toString();
//        String phone = phoneno.getText().toString();
//        Intent intent = new Intent(getApplicationContext(),EditProfile.class);
//        intent.putExtra("firstname",fname);
//        intent.putExtra("lastname",lname);
//        intent.putExtra("email",email);
//        intent.putExtra("phone",phone);
//        startActivity(intent);
//    }

    private void shownames() {
        Intent i = getIntent();
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds: snapshot.getChildren()){
                    firstname.setText(ds.child("firstname").getValue(String.class));
                    lastname.setText(ds.child("lastname").getValue(String.class));
                    //emailid.setText(ds.child("email").getValue(String.class));
                    //phoneno.setText(ds.child("phone").getValue(String.class));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Dashboard.this, "their is error in data fetching",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}