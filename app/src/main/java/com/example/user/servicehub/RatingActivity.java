package com.example.user.servicehub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RatingActivity extends AppCompatActivity {
    String cn,list[];
    DatabaseReference ref,ref1,ref2;
    int i=0;
    TextView a,b,c;
    RatingBar ratingBar;
    Button btnr,btnt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating);
        Bundle bundle = getIntent().getExtras();
        final String text = bundle.getString("Contact");
        cn = text;
        list = new String[100];
        ref = FirebaseDatabase.getInstance().getReference("torders");
        ref1 = FirebaseDatabase.getInstance().getReference("techRates");
        ref2 = FirebaseDatabase.getInstance().getReference("dumtorders");
        a=(TextView)findViewById(R.id.techName);
        b=(TextView)findViewById(R.id.techNo);
        c=(TextView)findViewById(R.id.techType);
        ratingBar=(RatingBar) findViewById(R.id.ratingBar);
        btnr=(Button) findViewById(R.id.buttonrate);
        btnt=(Button) findViewById(R.id.buttonhome);
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (final DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    final TakenOrder customer = postSnapshot.getValue(TakenOrder.class);
                    System.out.println(customer);
                    //setContentView(R.layout.activity_list);

                    if(customer.getContactNo().equals(cn)) {
                        a.setText("Name:" + customer.getTech().getName());
                        b.setText("Contact No:" + customer.getTech().getContactNo());
                        c.setText("Type:" + customer.getTech().getType());
                        final String ccn=customer.getTech().getContactNo();
                        //b.setText("Type:" + customer.getType());

                        btnr.setOnClickListener(
                                new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        //String id = ref1.push().getKey();
                                        //Customer customer=new Customer(id,a,b,d,c,e);
                                        //a.setText("ContactNo:" + customer.getContactNo());
                                        //DatabaseReference reff = FirebaseDatabase.getInstance().getReference();
                                        //Query applesQuery = reff.child("techRates").orderByChild("contactNo").equalTo(customer.getTech().getContactNo());


                                        ref1.addListenerForSingleValueEvent(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(DataSnapshot dataSnapshot) {
                                                for (DataSnapshot appleSnapshot : dataSnapshot.getChildren()) {
                                                    final TechRate tr = appleSnapshot.getValue(TechRate.class);
                                                    Toast.makeText(RatingActivity.this,tr.getN(), Toast.LENGTH_LONG).show();
                                                    if(tr.getTech().getContactNo().equals(ccn)) {
                                                        String id = tr.getId();
                                                        Tech tech = tr.getTech();

                                                        String N = tr.getN();

                                                        String total = tr.getTotal();
        //                                                Toast.makeText(RatingActivity.this,N, Toast.LENGTH_LONG).show();



                                                        //TechRate tra = new TechRate(id, tech, Integer.toString(Integer.parseInt(N) + 1), Integer.toString((int) (Integer.parseInt(total) + ratingBar.getRating())));
                                                        ref2.child(id).setValue(customer);
                                                        postSnapshot.getRef().removeValue();
                                                    }

                                                }
                                                Intent i = new Intent(RatingActivity.this, RatingActivity.class);
                                                Bundle bundle = new Bundle();
                                                bundle.putString("Contact",cn);
                                                i.putExtras(bundle);
                                                startActivity(i);

                                            }

                                            @Override
                                            public void onCancelled(DatabaseError databaseError) {
                                                Log.e("a", "onCancelled", databaseError.toException());
                                            }
                                        });
                                    }
                                }
                        );
                    }


                }}

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });

        btnt.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ref2.addListenerForSingleValueEvent(new ValueEventListener() {
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
                                    Intent i = new Intent(RatingActivity.this, ProfileActivity.class);
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
