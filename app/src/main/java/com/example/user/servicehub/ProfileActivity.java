package com.example.user.servicehub;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileActivity extends AppCompatActivity {


    DatabaseHelper myDb;
    TextView t,t1,t2,t3;
    Button btn,btnu,btnr,btnh,bn,log;
    DatabaseReference ref = FirebaseDatabase.getInstance().getReference("customers");
    DatabaseReference ref1 = FirebaseDatabase.getInstance().getReference("userPhoto");
    String cn,bm;
    Imageutils imageutils;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        myDb = new DatabaseHelper(this);
        t=(TextView)findViewById(R.id.Contact);
        t1=(TextView)findViewById(R.id.Name);
        t2=(TextView)findViewById(R.id.Address);
        t3=(TextView)findViewById(R.id.Location);
        btn=(Button)findViewById(R.id.button);
        btnu=(Button)findViewById(R.id.update);
        btnr=(Button)findViewById(R.id.seeRate);
        btnh=(Button)findViewById(R.id.seehistory);
        bn=(Button) findViewById(R.id.noti);
        log=(Button) findViewById(R.id.logout);
        imageView=(ImageView) findViewById(R.id.image);
        //imageutils =new Imageutils(this);
        Bundle bundle = getIntent().getExtras();
        final String text= bundle.getString("Contact");
        cn=text;
        t.setText("Contact No :" + text);
        //Cursor res = myDb.getAllFromContact(text);
        //if (res.getCount() == 0) {
            // show message
  //          showMessage("Error", "Nothing found");
            //return;
        //}

        //StringBuffer buffer = new StringBuffer();
        /*while (res.moveToNext()) {
            t1.setText("Name :" + res.getString(0) + "\n");
            t3.setText("Address :" + res.getString(2) + "\n");
            t2.setText("Location :" + res.getString(3) + "\n");
            //buffer.append("LOCATION :" + res.getString(3) + "\n\n");
            //buffer.append("CONTACT_NO :" + res.getString(4) + "\n\n");
        }*/

        ref.addListenerForSingleValueEvent(new ValueEventListener() {
                                               @Override
                                               public void onDataChange(DataSnapshot dataSnapshot) {
                                                   for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                                                       Customer customer = postSnapshot.getValue(Customer.class);
                                                       //System.out.println(customer);
                                                       if(text.equals(customer.getContactNo())) {
                                                           t1.setText("Name :" + customer.getName() + "\n");
                                                           t3.setText("Address :" + customer.getAddress() + "\n");
                                                           t2.setText("Location :" + customer.getLocation() + "\n");
                                                       }


                                                   }
                                               }

                                               @Override
                                               public void onCancelled(DatabaseError databaseError) {
                                                   System.out.println("The read failed: " + databaseError.getCode());
                                               }
                                           });

        ref1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    UserPhoto customer = postSnapshot.getValue(UserPhoto.class);
                    //System.out.println(customer);
                    if(text.equals(customer.getName())) {
                        bm=customer.getPhoto();
                        Bitmap bitmap=StringToBitMap(bm);
                        imageView.setImageBitmap(bitmap);
                        //t1.setText("Name :" + customer.getName() + "\n");
                        //t3.setText("Address :" + customer.getAddress() + "\n");
                        //t2.setText("Location :" + customer.getLocation() + "\n");
                    }


                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });


        // Show all data
//        showMessage("Data", buffer.toString());



        press();
        pic();
        rate();
        history();
        not();
        logout();

    }

    public void logout() {
        log.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(ProfileActivity.this, MainActivity.class);

                        startActivity(i);
                    }
                }
        );
    }

    public Bitmap StringToBitMap(String encodedString){
        try {
            byte [] encodeByte= Base64.decode(encodedString,Base64.DEFAULT);
            Bitmap bitmap= BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            return bitmap;
        } catch(Exception e) {
            e.getMessage();
            return null;
        }
    }

    public void not() {
        bn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(ProfileActivity.this, NotiAcitivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("Contact",cn);
                        i.putExtras(bundle);
                        startActivity(i);
                    }
                }
        );
    }



    public void press() {
        btn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(ProfileActivity.this, Main2Activity.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("Contact",cn);
                        i.putExtras(bundle);
                        startActivity(i);
                    }
                }
        );
    }

    public void pic() {
        btnu.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i=new Intent(ProfileActivity.this,ImageAttachmentActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("Contact",cn);
                        i.putExtras(bundle);
                        startActivity(i);
                    }
                }
        );


    }
    public void rate() {
        btnr.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i=new Intent(ProfileActivity.this, RatingActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("Contact",cn);
                        i.putExtras(bundle);
                        startActivity(i);
                    }
                }
        );


    }
    public void history() {
        btnh.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(ProfileActivity.this, SeeOrderActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("Contact",cn);
                        i.putExtras(bundle);
                        startActivity(i);
                    }
                }
        );
    }

}
