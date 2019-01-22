package com.example.user.servicehub;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class NotiAcitivity extends AppCompatActivity {

    String cn;
    DatabaseReference ref;
    TextView a,b,c;
    int k=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noti_acitivity);
        ref = FirebaseDatabase.getInstance().getReference("torders");
        a=(TextView) findViewById(R.id.n1);
        b=(TextView) findViewById(R.id.n2);
        c=(TextView) findViewById(R.id.n3);


        Bundle bundle = getIntent().getExtras();
        final String text= bundle.getString("Contact");
        cn=text;

        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //Bundle bundle = getIntent().getExtras();

                //final String text= bundle.getString("Contact");

                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    TakenOrder customer = postSnapshot.getValue(TakenOrder.class);
                    System.out.println(customer);
                    //Order od=customer.getOrder();
//                                    Toast.makeText(StatActivity.this,text, Toast.LENGTH_LONG).show();
                    if(customer.getContactNo().equals(text))
                    {

                        k++;
                        if(k==1)
                            a.setText("1. "+customer.getTech().getName()+" has accepted your order");
                        if(k==2)
                            b.setText("2. " +customer.getTech().getName()+" has accepted your order");
                        if(k==3)
                            c.setText("3. "+customer.getTech().getName()+" has accepted your order");

//                                        Toast.makeText(StatActivity.this,"Type is"+customer.getType(), Toast.LENGTH_LONG).show();





                        //Toast.makeText(StatActivity.this,"a is"+Integer.toString(a), Toast.LENGTH_LONG).show();


                    }





                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });

    }
}
