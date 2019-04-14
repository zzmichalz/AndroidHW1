package com.example.zad1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class radiobutton extends AppCompatActivity {

    private Button cancel;
    private Button accept;
    RadioGroup radioGroup;
    RadioButton radioButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radiobutton);

        radioGroup = findViewById(R.id.radioGroup);

        cancel = (Button) findViewById(R.id.button4);
        cancel.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(radiobutton.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        accept = (Button) findViewById(R.id.button3);
        accept.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                int radioId = radioGroup.getCheckedRadioButtonId();
                radioButton = findViewById(radioId);

                Intent intent = new Intent(radiobutton.this, MainActivity.class);
                intent.putExtra("radioButton",radioButton.getText().toString());
                startActivity(intent);
            }
        });
    }
}
