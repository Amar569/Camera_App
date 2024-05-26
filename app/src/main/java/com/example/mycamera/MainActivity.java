package com.example.mycamera;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ImageView imageview;
    Button button_image;
    Bitmap bitmap;
    int imageCount = 0;

    ListView listView;
    ImageListAdapter adapter;
    ArrayList<Bitmap> imageList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //imageview = findViewById(R.id.imageview);
        button_image = findViewById(R.id.button_image);


        listView = findViewById(R.id.listView);
        imageList = new ArrayList<>();
        adapter = new ImageListAdapter(this, imageList);
        listView.setAdapter(adapter);

        //Request for camera runtime permission
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{
                    Manifest.permission.CAMERA
            }, 100);
        }

//        imageview.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity.this, ImageDisplayActivity.class);
//                if (bitmap != null) {
//                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
//                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
//                    byte[] byteArray = stream.toByteArray();
//                    intent.putExtra("image", byteArray);
//                }
//                startActivity(intent);
//            }
//        });

        button_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 100);
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bitmap selectedImage = imageList.get(position);
                Intent intent = new Intent(MainActivity.this, ImageDisplayActivity.class);
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                selectedImage.compress(Bitmap.CompressFormat.PNG, 100, stream);
                byte[] byteArray = stream.toByteArray();
                intent.putExtra("image", byteArray);
                startActivity(intent);
                //imageview.setImageBitmap(selectedImage);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == RESULT_OK) {
            bitmap = (Bitmap) data.getExtras().get("data");
            imageCount++;
            updateButtonText();
//            imageview.setImageBitmap(bitmap);

            imageList.add(bitmap);
            adapter.notifyDataSetChanged();

        }
    }

    private void updateButtonText() {
        String buttonText = "Capture Image (" + imageCount + ")";
        button_image.setText(buttonText);
    }
}