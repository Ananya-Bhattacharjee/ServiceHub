package com.example.user.servicehub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Date;

public class Main2Activity extends AppCompatActivity {

    DatabaseReference databaseReference;
    Spinner type,location;
    EditText desc,address;
    Button btn,btnhome;
    String cn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Bundle bundle = getIntent().getExtras();
        final String text= bundle.getString("Contact");
        cn=text;
        btn = (Button) findViewById(R.id.button);
        databaseReference= FirebaseDatabase.getInstance().getReference("orders");
        //ref= FirebaseDatabase.getInstance().getReference("userPhoto");

        desc = (EditText) findViewById(R.id.EditTextPtype);
        address = (EditText) findViewById(R.id.EditTextAddress);
        location = (Spinner) findViewById(R.id.SpinnerLocation);
        type = (Spinner) findViewById(R.id.SpinnerFeedbackType);
        AddOrder();
        //client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();

    }

    public void AddOrder() {

        btn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String a,b,c,d;
                        a=desc.getText().toString();
                        b=address.getText().toString();
                        c=location.getSelectedItem().toString();
                        d=type.getSelectedItem().toString();
                        if(!a.isEmpty() && !b.isEmpty())
                        {
                            Date date=new Date();
                            String id=databaseReference.push().getKey();
                            Order order=new Order(id,cn,d,a,c,b,date.toString());
                            databaseReference.child(id).setValue(order);
                            Toast.makeText(Main2Activity.this, "Order is successful", Toast.LENGTH_LONG).show();
                            setContentView(R.layout.gobackorder);
                            btnhome = (Button) findViewById(R.id.buttonhome);

                            btnhome.setOnClickListener(
                                    new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            Intent i=new Intent(Main2Activity.this,ProfileActivity.class);
                                            Bundle bundle = new Bundle();
                                            bundle.putString("Contact", cn);
                                            i.putExtras(bundle);


                                            startActivity(i);

                                        }
                                    }
                            );



                        }


                    }
                }
        );



    }
}
