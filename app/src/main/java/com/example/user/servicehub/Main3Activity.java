package com.example.user.servicehub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Main3Activity extends AppCompatActivity {


    DatabaseHelper myDb;
    EditText editName, editPassword, editConPassword, editAddress, editContactNo;
    Spinner LocationList;
    CheckBox Response;
    Button btn,btnhome;
    DatabaseReference databaseReference,ref;
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        myDb = new DatabaseHelper(this);
        btn = (Button) findViewById(R.id.buttonAdd);
        databaseReference= FirebaseDatabase.getInstance().getReference("customers");
        ref= FirebaseDatabase.getInstance().getReference("userPhoto");

        editName = (EditText) findViewById(R.id.editTextName);
        editPassword = (EditText) findViewById(R.id.editTextPassword);
        editConPassword = (EditText) findViewById(R.id.editTextConPassword);
        editAddress = (EditText) findViewById(R.id.editTextAddress);
        editContactNo = (EditText) findViewById(R.id.editTextContactNo);
        LocationList = (Spinner) findViewById(R.id.SpinnerLocation);
        Response = (CheckBox) findViewById(R.id.CheckBoxResponse);

//        Add();
        AddCustomer();
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();


    }
    public void Add() {
        btn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //Log.d("A","B");


                        if(Response.isChecked() && editPassword.getText().toString().equals(editConPassword.getText().toString())) {


                            if (!editName.getText().toString().isEmpty() && !editPassword.getText().toString().isEmpty() &&!editAddress.getText().toString().isEmpty() && !editContactNo.getText().toString().isEmpty())


                            {
                                boolean isInserted = myDb.insertData(editName.getText().toString(),
                                        editPassword.getText().toString(),
                                        editAddress.getText().toString(),
                                        LocationList.getSelectedItem().toString(),
                                        editContactNo.getText().toString());
                                if (isInserted == true)
                                {
                                    Toast.makeText(Main3Activity.this, "Signed Up", Toast.LENGTH_LONG).show();
                                    setContentView(R.layout.goback);
                                    btnhome = (Button) findViewById(R.id.buttonhome);

                                    btnhome.setOnClickListener(
                                            new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {
                                                    Intent i=new Intent(Main3Activity.this,MainActivity.class);
                                                    startActivity(i);

                                                }
                                            }
                                    );



                                }
                                else
                                    Toast.makeText(Main3Activity.this, "Sign Up Failed", Toast.LENGTH_LONG).show();
                            }
                            else
                                Toast.makeText(Main3Activity.this, "Sign Up Failed", Toast.LENGTH_LONG).show();
                        }
                        else
                        Toast.makeText(Main3Activity.this, "Sign Up Failed", Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

    public void AddCustomer() {
        btn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        //Log.d("A","B");
                        if(Response.isChecked() && editPassword.getText().toString().equals(editConPassword.getText().toString())) {


                            if (!editName.getText().toString().isEmpty() && !editPassword.getText().toString().isEmpty() &&!editAddress.getText().toString().isEmpty() && !editContactNo.getText().toString().isEmpty())


                            {
                                int s=1;

                                final String a,b,c,d,e;
                                a=editName.getText().toString();
                                b=editPassword.getText().toString();
                                c=editAddress.getText().toString();
                                d=LocationList.getSelectedItem().toString();
                                e=editContactNo.getText().toString();


                                if(b.length()<6)
                                    s=2;

                                if(e.length()!=11 || !e.startsWith("01"))
                                {
                                    s=3;
                                    //Intent i = new Intent(Main3Activity.this, Main3Activity.class);
                                    //Toast.makeText(Main3Activity.this,"Password must be of at least 6 characters", Toast.LENGTH_LONG).show();

                                    //startActivity(i);

                                }


/*                                ref.addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                        //Bundle bundle = getIntent().getExtras();

                                        //final String text= bundle.getString("Contact");
                                        //String text=s2.getSelectedItem().toString();

                                        for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                                            Customer customer = postSnapshot.getValue(Customer.class);
                                            System.out.println(customer);
                                            //Order od=customer.getOrder();
//                                    Toast.makeText(StatActivity.this,text, Toast.LENGTH_LONG).show();
                                            if(customer.getContactNo().equals(e))
                                            {

//                                        Toast.makeText(StatActivity.this,"Type is"+customer.getType(), Toast.LENGTH_LONG).show();


                                                Intent i = new Intent(Main3Activity.this, Main3Activity.class);
                                                Toast.makeText(Main3Activity.this,"This Number is not Available", Toast.LENGTH_LONG).show();

                                                startActivity(i);

                                                //Toast.makeText(StatActivity.this,"a is"+Integer.toString(a), Toast.LENGTH_LONG).show();


                                            }





                                        }


                                    }

                                    @Override
                                    public void onCancelled(DatabaseError databaseError) {
                                        System.out.println("The read failed: " + databaseError.getCode());
                                    }
                                });
*/




                            if(s==1) {
                                String id = databaseReference.push().getKey();
                                Customer customer = new Customer(id, a, b, d, c, e);
                                databaseReference.child(id).setValue(customer);
                                UserPhoto userPhoto = new UserPhoto(id, e, null);
                                ref.child(id).setValue(userPhoto);


                                Toast.makeText(Main3Activity.this, "Signed Up", Toast.LENGTH_LONG).show();
                                setContentView(R.layout.goback);
                                btnhome = (Button) findViewById(R.id.buttonhome);

                                btnhome.setOnClickListener(
                                        new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                Intent i = new Intent(Main3Activity.this, MainActivity.class);
                                                startActivity(i);

                                            }
                                        }
                                );
                            }
                            else if(s==2)
                            {
                                Toast.makeText(Main3Activity.this, "Password should be of at least 6 characters", Toast.LENGTH_LONG).show();

                            }
                                else if(s==3)
                                    Toast.makeText(Main3Activity.this, "Check your number", Toast.LENGTH_LONG).show();



                            }
                            else
                                Toast.makeText(Main3Activity.this, "Sign Up Failed. Check all fields.", Toast.LENGTH_LONG).show();
                        }
                        else
                            Toast.makeText(Main3Activity.this, "Sign Up Failed. Check all fields.", Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

}
