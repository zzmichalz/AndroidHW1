package com.example.zad1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class spinnersound extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Button cancel;
    private Button accept;
    String text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinnersound);

        Intent t = getIntent();
        final int temp = t.getIntExtra("temp2",0);
        final String temp2 = Integer.toString(temp);

        Spinner spinner = findViewById(R.id.spinner1);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.spin, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        cancel = (Button) findViewById(R.id.button5);
        cancel.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(spinnersound.this, MainActivity.class);
                intent.putExtra("spin1",temp2);
                setResult(RESULT_OK,intent);
                finish();
            }
        });

        accept = (Button) findViewById(R.id.button6);
        accept.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(spinnersound.this, MainActivity.class);
                intent.putExtra("spin1",text);
                setResult(RESULT_OK,intent);
                finish();
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        text = parent.getItemAtPosition(position).toString();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
