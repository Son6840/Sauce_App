package com.daelim.sauce.activity;

import androidx.annotation.Nullable;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;

import android.graphics.Bitmap;

import android.os.Bundle;

import android.provider.MediaStore;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.daelim.sauce.R;



public class MainActivity2 extends AppCompatActivity {
    private Button btn_picture;
    private ImageView imageView;
    final int GET_GALLERY_IMAGE = 200;

    private static  final int REQUEST_IMAGE_CODE =101;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Button btn_gallay;
        btn_gallay = (Button) findViewById(R.id.btn_gallay);
        btn_gallay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                startActivityForResult(intent,GET_GALLERY_IMAGE);
            }
        });

        imageView = findViewById(R.id.imageView);

        btn_picture = findViewById(R.id.btn_picture);
        btn_picture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takePicture();
            }
        });
    }
    public void takePicture(){
        Intent imageTakeIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        if (imageTakeIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(imageTakeIntent,REQUEST_IMAGE_CODE);
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CODE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            imageView.setImageBitmap(imageBitmap);
        }
    }
}

