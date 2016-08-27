package com.blackflag.fairbaseex;

import android.content.Intent;
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

    private Button getLocationList;
    private FirebaseAuth auth;

    private EditText etLat, etLon;
    private TextView tvLocation;
    private Button addLocation;
    FirebaseDatabase db;
    DatabaseReference ref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        etLat = (EditText) findViewById(R.id.etLat);
        etLon = (EditText) findViewById(R.id.etLon);
        tvLocation = (TextView) findViewById(R.id.locationList);
        addLocation = (Button) findViewById(R.id.addLocation);
        getLocationList = (Button) findViewById(R.id.getLocation);

        auth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();
        ref = db.getReference("location");



        addLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String lat = etLat.getText().toString();
                String lon = etLon.getText().toString();
                ref.push().setValue(new Location(Double.parseDouble(lat), Double.parseDouble(lon)));
            }
        });

        getLocationList.setOnClickListener(new View.OnClickListener() {
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
                            String temp = tvLocation.getText().toString();
//                            if(location.getLon()==90.000)
//                                child.getRef().setValue(new Location(33.0000,44.0000));  /update code
                            tvLocation.setText(temp + "\n" + location.getLat() + " " + location.getLon());

                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });

        findViewById(R.id.showInMap).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),MapsActivity.class));
            }
        });




    }
}
