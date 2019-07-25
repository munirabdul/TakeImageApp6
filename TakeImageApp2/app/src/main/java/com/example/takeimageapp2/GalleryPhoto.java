package com.example.takeimageapp2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;

public class GalleryPhoto extends AppCompatActivity implements onClick, onItemClick {
AdapterImage adapterImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery_photo);

        GridView gridView = (GridView) findViewById(R.id.gv_gallery_photo);
        adapterImage = new AdapterImage(this);
        gridView.setAdapter((ListAdapter) adapterImage);
        gridView.setOnItemClickListener((AdapterView.OnItemClickListener) this);

        String ExternalStorageDirectoryPath = Environment
                .getExternalStorageDirectory()
                .getAbsolutePath();
        String  targetPath = ExternalStorageDirectoryPath + "/Gambar/";
        Toast.makeText(getApplicationContext(), targetPath, Toast.LENGTH_SHORT).show();
        File targetDirectory = new File(targetPath);
        File[] files = targetDirectory.listFiles();
        for (File file : files) {
        adapterImage.add(file.getAbsolutePath());
        }
    }
    @Override
    public void onClick(View view){}

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int positions, long id){
        Toast.makeText(GalleryPhoto.this, "Gambar no" + positions + "Dipilih", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getApplicationContext(), SinglePhoto.class);
        intent.putExtra("path", adapterImage.itemList.get(positions));
        startActivity(intent);
    }
}