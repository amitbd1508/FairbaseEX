package com.blackflag.fairbaseex;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class FairbaseActivity extends AppCompatActivity {

    private Button getUserListBtn;
    private FirebaseAuth auth;

    private EditText studentNameET;
    private TextView studentListTV;
    private Button addStudentBtn;
    FirebaseDatabase db;
    DatabaseReference ref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fairbase);
        studentNameET = (EditText) findViewById(R.id.studentNameET);
        studentListTV = (TextView) findViewById(R.id.studentListTV);
        addStudentBtn = (Button) findViewById(R.id.addStudentBtn);
        getUserListBtn = (Button) findViewById(R.id.getUserListBtn);

        auth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();
        ref = db.getReference("users");

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
                        Log.d("userList", dataSnapshot.getValue().toString());
                        for (DataSnapshot child : dataSnapshot.getChildren()) {
                            Student student = child.getValue(Student.class);
                            String temp = studentListTV.getText().toString();
                            studentListTV.setText(temp + "\n" + student.getName() + " " + student.getCgpa());
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });


        addStudentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = studentNameET.getText().toString();
                ref.push().setValue(new Student(name, 3.7));
            }
        });

    }
}