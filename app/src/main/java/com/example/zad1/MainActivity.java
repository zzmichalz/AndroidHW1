package com.example.zad1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private Button contact;
    private Button sound;
    TextView textView;
    String radio;
    String music;
    MediaPlayer mySound;
    ImageView picture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        picture = (ImageView) findViewById(R.id.imageView);

        int[] images = {R.drawable.img1,R.drawable.img2,R.drawable.img3,R.drawable.img4,R.drawable.img5};
        Random rand = new Random();
        picture.setImageResource(images[rand.nextInt(images.length)]);

        textView = findViewById(R.id.textView);
        Intent r = getIntent();
        radio = r.getStringExtra("radioButton");

        if(radio == null){
            radio = "choose contact";
        }

            textView.setText(radio);

            Intent s = getIntent();
            music = s.getStringExtra("spin1");

            if(music == null){
                music = "Sound1";
            }

        switch (music) {
            case "Sound1":
                mySound = MediaPlayer.create(this,R.raw.sound1);
                break;
            case "Sound2":
                mySound = MediaPlayer.create(this,R.raw.sound2);
                break;
            case "Sound3":
                mySound = MediaPlayer.create(this,R.raw.sound3);
                break;
            case "Sound4":
                mySound = MediaPlayer.create(this,R.raw.sound4);
                break;
            case "Sound5":
                mySound = MediaPlayer.create(this,R.raw.sound5);
                break;
        }

        contact = (Button) findViewById(R.id.button);
        contact.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openRadio();
            }
        });

        sound = (Button) findViewById(R.id.button2);
        sound.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openSound();
            }
        });

        FloatingActionButton fab = findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    if (mySound.isPlaying() == true) {
                        mySound.pause();
                    } else
                        mySound.start();

            }
        });
    }

    public void openRadio(){
        Intent intent = new Intent(this, radiobutton.class);
        startActivity(intent);
    }

    public void openSound(){
        Intent intent = new Intent(this, spinnersound.class);
        startActivity(intent);
    }

    @Override
    protected void onPause(){
        super.onPause();
        mySound.release();
    }

}