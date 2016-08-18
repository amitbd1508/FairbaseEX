package com.blackflag.fairbaseex;

import android.renderscript.Double2;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LocationActivity extends AppCompatActivity {

    private Button getUserListBtn;
    private FirebaseAuth auth;

    private EditText studentNameET,cgpa;
    private TextView studentListTV;
    private Button addStudentBtn;
    FirebaseDatabase db;
    DatabaseReference ref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        studentNameET = (EditText) findViewById(R.id.studentNameET);
        cgpa = (EditText) findViewById(R.id.cgpaET);
        studentListTV = (TextView) findViewById(R.id.studentListTV);
        addStudentBtn = (Button) findViewById(R.id.addStudentBtn);
        getUserListBtn = (Button) findViewById(R.id.getUserListBtn);

        auth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();
        ref = db.getReference("location");



        addStudentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String lat = studentNameET.getText().toString();
                String lon = cgpa.getText().toString();
                ref.push().setValue(new Location(Double.parseDouble(lat), Double.parseDouble(lon)));
            }
        });

        getUserListBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*ref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        String res = dataSnapshot.getValue(String.class);
                        studentListTV.setText(res);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });*/
                ref.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        Log.d("location", dataSnapshot.getValue().toString());
                        for (DataSnapshot child : dataSnapshot.getChildren()) {
                            Location location = child.getValue(Location.class);
                            String temp = studentListTV.getText().toString();
                            if(location.getLon()==90.000)
                                child.getRef().setValue(new Location(33.0000,44.0000));
                            studentListTV.setText(temp + "\n" + location.getLat() + " " + location.getLon());

                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });




    }
}
