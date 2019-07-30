package com.seventee170897gmail.comgmailseventee170897;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class Camera extends AppCompatActivity {
    ImageView imgview;
    Button Btn;
    Button Btn1;
    Bitmap imgbitmap;
    // TODO : pendeklarasian variabel

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        write();
        read();
        Btn = findViewById(R.id.btnok);
        Btn1 = findViewById(R.id.btnback);
        imgview = findViewById(R.id.imgview);
        // TODO : pendeklarasian variabel untuk mengambil data dari id pada kelas xml

        Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(takePictureIntent, 0);
            }
            // TODO : pada button diberi setOnClickListener yang mana dilakukan intent
        });
        Btn1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                finish();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            imgbitmap = (Bitmap) data.getExtras().get("data");
            imgview.setImageBitmap(imgbitmap);
            Saveimg(imgbitmap);
            // TODO : Kondisi Result_OK untuk menyimpan gambar yang dilakukan pada method Saveimg

        } else if (resultCode == RESULT_CANCELED) {
            Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
        }
        // TODO : Kondisi lain yaitu Result_canceled untuk melakukan pembatalan
    }

    private void Saveimg(Bitmap finalBitmap) {
        File penyimpanan = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).toString());
        if (!penyimpanan.exists()) {
            penyimpanan.mkdirs();
        }
        // TODO : Pada method Saveimg dilakukan proses membuat klausa baru dengan tipe File

        Toast.makeText(this, penyimpanan.toString(), Toast.LENGTH_LONG).show();

        Random generator = new Random();
        int mbr = 10000;
        mbr = generator.nextInt(mbr);
        File datasimpan = new File(penyimpanan, "IMG_" + mbr + ".jpg");
        if (datasimpan.exists())
            datasimpan.delete();

        try {
            FileOutputStream out = new FileOutputStream(datasimpan);
            finalBitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void write() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            Toast.makeText(this, "READY!", Toast.LENGTH_LONG).show();
        } else Toast.makeText(this, "FAILED!", Toast.LENGTH_LONG).show();
    }
    // TODO : Pada method Write untuk menulis data

    public void read() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            Toast.makeText(this, "OK!", Toast.LENGTH_LONG).show();
        } else Toast.makeText(this, "FAILURE!", Toast.LENGTH_LONG).show();
    }
    // TODO :  method Write untuk membaca data
}