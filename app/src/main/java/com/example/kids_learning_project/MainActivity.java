package com.example.kids_learning_project;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;


public class MainActivity extends AppCompatActivity {


    private PaintView paintView;
    Button undo_button, redo_button, undo_button, clear_button;
    private int STORAGE_PERMISSION_CODE = 1;
    private int count = 0;


    ImageSwitcher imageSwitcher;
    int[] images = {R.drawable.aa, R.drawable.bb, R.drawable.cc, R.drawable.dd, R.drawable.ee, R.drawable.ff, R.drawable.gg, R.drawable.hhh, R.drawable.ii, R.drawable.jj, R.drawable.kk, R.drawable.ll, R.drawable.mm, R.drawable.nn, R.drawable.oo, R.drawable.pp, R.drawable.qq, R.drawable.rr, R.drawable.ss, R.drawable.tt, R.drawable.uu, R.drawable.vv, R.drawable.ww, R.drawable.xx, R.drawable.yy, R.drawable.zz};
    int position = -1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Button undo_button, redo_button, save_button, clear_button;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        paintView = findViewById(R.id.paintView);

        undo_button = findViewById(R.id.undo_button);
        save_button = findViewById(R.id.save_button);
        redo_button = findViewById(R.id.redo_button);
        clear_button = findViewById(R.id.clear_button);


        ImageView imageView1 = (ImageView) findViewById(R.id.next);
        ImageView imageView2 = (ImageView) findViewById(R.id.prev);


        imageSwitcher = findViewById(R.id.imageSwitcher);


        imageSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                ImageView imageView = new ImageView(getApplicationContext());
                imageView.setLayoutParams(new ImageSwitcher.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT));
                return imageView;
            }

        });
        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (position < images.length - 1) {
                    position = position + 1;
                    imageSwitcher.setBackgroundResource(images[position]);
                }
            }
        });
        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (position > 0) {
                    position = position - 1;
                    imageSwitcher.setBackgroundResource(images[position]);
                }
            }
        });


        DisplayMetrics displayMetrics = new DisplayMetrics();
        SeekBar seekBar = findViewById(R.id.seekBar);


        undo_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                paintView.undo();
            }
        });


        redo_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                paintView.redo();
            }
        });

        clear_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                paintView.clear();
            }
        });

        save_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CheckPermission checkPermission = new CheckPermission(MainActivity.this);
                if (checkPermission.checkPermission())
                    paintView.saveImage();
                else
                    Toast.makeText(MainActivity.this, "Please allowed the  external storage permission", Toast.LENGTH_SHORT).show();

            }
        });


        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        paintView.initialise(displayMetrics);


        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                paintView.setStrokeWidth(seekBar.getProgress());


            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }


    private void requestStoragePermission() {

        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {

            new AlertDialog.Builder(this)
                    .setTitle("Permission needed")
                    .setMessage("Needed to save image")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, STORAGE_PERMISSION_CODE);

                        }
                    })
                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            dialog.dismiss();

                        }

                    })
                    .create().show();

        } else {

            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, STORAGE_PERMISSION_CODE);

        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (count == 0) {
            count++;
            requestStoragePermission();
        }
    }

    @Override
    public void onBackPressed() {
        Intent secondIntent = new Intent(MainActivity.this, OptionPage.class);
        startActivity(secondIntent);
        finish();
    }
}