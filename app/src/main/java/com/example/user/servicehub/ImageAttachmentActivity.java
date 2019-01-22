package com.example.user.servicehub;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.File;
import java.util.ArrayList;

public class ImageAttachmentActivity extends AppCompatActivity implements Imageutils.ImageAttachmentListener {


    ImageView iv_attachment;

    //For Image Attachment

    private Bitmap bitmap;
    private String file_name;

    Imageutils imageutils;
    String cn;
    Button btnt;

    DatabaseReference ref = FirebaseDatabase.getInstance().getReference("userPhoto");
    ArrayList<UserPhoto> u= new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_attachment);
        Bundle bundle = getIntent().getExtras();
        final String text= bundle.getString("Contact");
        cn=text;
        btnt=(Button) findViewById(R.id.btnt);

        btnt.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i=new Intent(ImageAttachmentActivity.this,ProfileActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("Contact", text);
                        i.putExtras(bundle);
                        startActivity(i);

//                        startActivity(i);
                    }
                }
        );




        imageutils =new Imageutils(this);

        iv_attachment=(ImageView)findViewById(R.id.imageView);

        iv_attachment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageutils.imagepicker(1);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        imageutils.onActivityResult(requestCode, resultCode, data);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        imageutils.request_permission_result(requestCode, permissions, grantResults);
    }

    @Override
    public void image_attachment(int from, String filename, Bitmap file, Uri uri) {
        this.bitmap=file;
        this.file_name=filename;
        iv_attachment.setImageBitmap(file);

        String path =  Environment.getExternalStorageDirectory() + File.separator + "ImageAttach" + File.separator;
        imageutils.createImage(file,filename,path,false);
        final String x=imageutils.BitMapToString(file);
        //String id=ref.push().getKey();
        //UserPhoto userPhoto=new UserPhoto(id,cn,x);
        //ref.child(id).setValue(userPhoto);
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    UserPhoto customer = postSnapshot.getValue(UserPhoto.class);
                    System.out.println(customer);
                    if (customer.getName().equals(cn)) {
                        String i=customer.getId();
                        ref.child(i).removeValue();
                        UserPhoto userPhoto=new UserPhoto(i,cn,x);
                        ref.child(i).setValue(userPhoto);



                        //Intent i = new Intent(LoginActivity.this, ProfileActivity.class);


                        //String c = eContactNo.getText().toString();
                        //Bundle bundle = new Bundle();
                        //bundle.putString("Contact", c);
                        //i.putExtras(bundle);
                        //startActivity(i);
                        //return;
                    }


                }}

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });


    }
}
