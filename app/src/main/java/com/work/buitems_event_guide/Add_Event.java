package com.work.buitems_event_guide;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Add_Event extends Activity {
    private static final String TAG = "Add_Event";
    private EditText cget, atet, adet, alet, enet;
    private DatabaseReference mAddEvent;
    private Button sb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__event);
        Button my_button= findViewById(R.id.button8);
        cget = findViewById(R.id.editText5);
        atet = findViewById(R.id.editText6);
        adet = findViewById(R.id.editText10);
        alet = findViewById(R.id.editText11);
        enet = findViewById(R.id.event_name);
        sb = findViewById(R.id.button);

       mAddEvent = FirebaseDatabase.getInstance().getReference().child("events");


        sb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String cg, at, ad, al, en;
                cg = cget.getText().toString();
                at = atet.getText().toString();
                ad = adet.getText().toString();
                al = alet.getText().toString();
                en = enet.getText().toString();
               if(al.equals("") || ad.equals("")|| at.equals("") || cg.equals("")){
                    Toast.makeText(Add_Event.this, "Pls fill the fields", Toast.LENGTH_SHORT).show();
                    return;
                }
                mAddEvent = mAddEvent.push();
               mAddEvent.child("cheif_guest").setValue(cg).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                mAddEvent.child("add_time").setValue(at);
                                mAddEvent.child("add_date").setValue(ad);
                                mAddEvent.child("add_location").setValue(al);
                                mAddEvent.child("add_event_name").setValue(en);
                                mAddEvent = FirebaseDatabase.getInstance().getReference().child("events");
                                adet.setText("");
                                cget.setText("");
                                atet.setText("");
                                alet.setText("");
                                enet.setText("");

                            }
                            else {
                                Toast.makeText(Add_Event.this, task.getException().toString(), Toast.LENGTH_SHORT).show();
                            }
                    }
                });
            }
        });


        my_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent my_intent=new Intent(Add_Event.this,Event_Edit.class);
                startActivity(my_intent);
            }
        });
        Button Go_Back= findViewById(R.id.button7);
        Go_Back .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent my_intent=new Intent(Add_Event.this,Admin_login.class);
                startActivity(my_intent) ;
            }
        });
    }
}
