package com.example.zad1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private Button contact;
    private Button sound;
    TextView textView;
    String radio;
    String music;
    ImageView picture;
    private int current_sound = 0;

    private MediaPlayer buttonPlayer;
    static public Uri[] sounds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        picture = (ImageView) findViewById(R.id.imageView);
        textView = findViewById(R.id.textView);

        int[] images = {R.drawable.img1,R.drawable.img2,R.drawable.img3,R.drawable.img4,R.drawable.img5};
        Random rand = new Random();
        picture.setImageResource(images[rand.nextInt(images.length)]);

        sounds = new Uri[5];

        sounds[0] = Uri.parse("android.resource://" + getPackageName() + "/" +
                R.raw.sound1);
        sounds[1] = Uri.parse("android.resource://" + getPackageName() + "/" +
                R.raw.sound2);
        sounds[2] = Uri.parse("android.resource://" + getPackageName() + "/" +
                R.raw.sound3);
        sounds[3] = Uri.parse("android.resource://" + getPackageName() + "/" +
                R.raw.sound4);
        sounds[4] = Uri.parse("android.resource://" + getPackageName() + "/" +
                R.raw.sound5);

        buttonPlayer = new MediaPlayer();
        buttonPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);

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

        buttonPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.start();
            }
        });

        FloatingActionButton fab = findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(buttonPlayer.isPlaying() == true){
                    buttonPlayer.stop();
                }
                else{
                    buttonPlayer.reset();

                    try {
                        buttonPlayer.setDataSource(getApplicationContext(),sounds[current_sound]);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    buttonPlayer.prepareAsync();
                }

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent
            data) {
        if (resultCode == RESULT_OK) {
            if(requestCode == 1)
            {
                radio = data.getStringExtra("radioButton");
                textView.setText(radio);
            }
            else if(requestCode == 2){
                music = data.getStringExtra("spin1");
                switch(music){
                    case "0":
                        current_sound = 0;
                        break;
                    case "1":
                        current_sound = 1;
                        break;
                    case "2":
                        current_sound = 2;
                        break;
                    case "3":
                        current_sound = 3;
                        break;
                    case "4":
                        current_sound = 4;
                        break;
                }
            }
        }
        }

    public void openRadio(){
        Intent intent = new Intent(this, radiobutton.class);
        intent.putExtra("temp",radio);
        startActivityForResult(intent, 1);
    }

    public void openSound(){
        Intent intent = new Intent(this, spinnersound.class);
        intent.putExtra("temp2",current_sound);
        startActivityForResult(intent, 2);
    }

    @Override
    protected void onPause() {
        super.onPause();
        buttonPlayer.pause();
    }

}