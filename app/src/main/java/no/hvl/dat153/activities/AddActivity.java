package no.hvl.dat153.activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.namequizapp.R;

import no.hvl.dat153.classes.Person;
import no.hvl.dat153.classes.PersonDatabase;

public class AddActivity extends MenuActivity {


    Button takePicture;
    Button galleryPicture;
    ImageView picture;
    TextView name;
    PersonDatabase db;

    private static final int IMAGE_PICK_CODE = 1000;
    private static final int PERMISSION_CODE = 1001;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        // declaring the variables
        db = PersonDatabase.getInstance(this);
        takePicture = (Button) findViewById(R.id.button_take);
        galleryPicture = (Button) findViewById(R.id.button_excisting);
        picture = (ImageView) findViewById(R.id.image_picture);
        name = (TextView) findViewById(R.id.nameText);


    }


    public void addingThePerson(View view){
        if(picture==null){
            Toast.makeText(this, "Picture is null, try again!", Toast.LENGTH_SHORT).show();
        } else if(name==null) {
            Toast.makeText(this, "Name is null, try again!", Toast.LENGTH_SHORT).show();
        } else {
            Person p = new Person(name.getText().toString(), picture.getDrawable());
            db.personDao().insertPerson(p);
            Intent i = new Intent(this, DatabaseActivity.class);
            startActivity(i);
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100) {
            Bitmap captureImage = (Bitmap) data.getExtras().get("data");
            picture.setImageBitmap(captureImage);
        } else {
            picture.setImageURI(data.getData());

        }
    }

    public void tPicture(View view){

        if(ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.CAMERA
            }, 100);
        }
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent, 100);

    }

    public void gPicture(View view){

            if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.M){
                if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                        == PackageManager.PERMISSION_DENIED) {
                    String [] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE};
                    requestPermissions(permissions, PERMISSION_CODE);

                } else {
                    pickImageFromGallery();


                }
            } else {
                pickImageFromGallery();

            }

    }

    public void pickImageFromGallery(){
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, IMAGE_PICK_CODE);
    }
}