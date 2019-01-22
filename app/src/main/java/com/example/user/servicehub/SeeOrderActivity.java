package com.example.user.servicehub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SeeOrderActivity extends AppCompatActivity {

    DatabaseReference ref,ref1;
    TextView a,b,c,d,e,f;
    Button next,gb;
    String cn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_order);

        a=(TextView) findViewById(R.id.Name);
        b=(TextView) findViewById(R.id.Contact);
        c=(TextView) findViewById(R.id.Date);
        d=(TextView) findViewById(R.id.Type);
        e=(TextView) findViewById(R.id.Description);

        next=(Button) findViewById(R.id.next);
        gb= (Button) findViewById(R.id.buttonhome);

        Bundle bundle = getIntent().getExtras();
        final String text = bundle.getString("Contact");
        cn = text;

        ref = FirebaseDatabase.getInstance().getReference("torders");
        ref1 = FirebaseDatabase.getInstance().getReference("dumtorders");

        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //Bundle bundle = getIntent().getExtras();
                //final String text= bundle.getString("Contact");
                for (final DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    final TakenOrder customer = postSnapshot.getValue(TakenOrder.class);
                    System.out.println(customer);

                    if(customer.getContactNo().equals(cn))
                    {
                        a.setText("Name:"+customer.getTech().getName());
                        b.setText("Contact No:"+customer.getTech().getContactNo());
                        c.setText("Date:"+customer.getDate());

                    }





                    next.setOnClickListener(
                            new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {

                                    ref1.child(customer.getId()).setValue(customer);
                                    postSnapshot.getRef().removeValue();

                                    Intent i = new Intent(SeeOrderActivity.this, SeeOrderActivity.class);
                                    Bundle bundle = new Bundle();
                                    bundle.putString("Contact",cn);
                                    i.putExtras(bundle);
                                    startActivity(i);

                                }
                            }
                    );





                }}

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });

        gb.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ref1.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                //Bundle bundle = getIntent().getExtras();
                                //final String text= bundle.getString("Contact");
                                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                                    TakenOrder customer = postSnapshot.getValue(TakenOrder.class);
                                    System.out.println(customer);
                                    //Order od=customer.getOrder();
                                    String id=customer.getId();

                                    ref.child(id).setValue(customer);
                                    postSnapshot.getRef().removeValue();
                                    Intent i = new Intent(SeeOrderActivity.this, ProfileActivity.class);
                                    Bundle bundle = new Bundle();
                                    bundle.putString("Contact",text);
                                    i.putExtras(bundle);
                                    startActivity(i);


                                }}

                            @Override
                            public void onCancelled(DatabaseError databaseError) {
                                System.out.println("The read failed: " + databaseError.getCode());
                            }
                        });



                    }
                }
        );



    }
}
