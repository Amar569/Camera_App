package com.example.mycamera;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;

public class ImageDisplayActivity extends AppCompatActivity {

    ImageView imageview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_display);

        imageview = findViewById(R.id.imageView);

        // Get the ByteArray from Intent and convert it back to Bitmap
        byte[] byteArray = getIntent().getByteArrayExtra("image");
        if (byteArray != null) {
            Bitmap bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
            imageview.setImageBitmap(bitmap);
        }


    }
}