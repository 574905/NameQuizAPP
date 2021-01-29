package com.example.namequizapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Picture;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.ImageView;

import java.util.BitSet;

public class AddActivity extends AppCompatActivity {

    // declaring the variables
    Button takePicture;
    ImageView picture;
    Button galleryPicture;
    Uri imageUrl;
    private static final int PICK_IMAGE = 1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);



        // finding the button and imageview from xml
        takePicture = (Button) findViewById(R.id.button_take);
        picture = (ImageView) findViewById(R.id.image_picture);
        galleryPicture = (Button) findViewById(R.id.button_excisting);




        // checking if the permission to access camera is granted, if not we'll ask for permission
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.CAMERA
            }, 100);
        }

        takePicture.setOnClickListener(x->{
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent, 100);
        });



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100) {
            Bitmap captureImage = (Bitmap) data.getExtras().get("data");
            picture.setImageBitmap(captureImage);
        }
    }
}