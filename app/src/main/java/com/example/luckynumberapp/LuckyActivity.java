package com.example.luckynumberapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class LuckyActivity extends AppCompatActivity {

    TextView luckyText, textView2;
    Button shareButton;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lucky);

        luckyText = findViewById(R.id.textInputEditText);
        textView2 = findViewById(R.id.textVIew2);
        shareButton = findViewById(R.id.shareButton);

        Intent i = getIntent();
        String userName = i.getStringExtra("name");

        int randomNumber = randomNumberGenerator();

        textView2.setText(""+randomNumber);

        shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareData(userName,randomNumber);
            }
        });

    }

    public int randomNumberGenerator(){
        Random number = new Random();
        int upperLimit = 1000;
        int generatedNumber = number.nextInt(upperLimit);
        return generatedNumber;
    }

    public void shareData(String username, int randomNumber){
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("text/plain");

        String num = String.valueOf(randomNumber);

        i.putExtra(Intent.EXTRA_SUBJECT,username + " got Lucky Today!" );
        i.putExtra(Intent.EXTRA_TEXT, "His Lucky Number is: "+num);

        startActivity(Intent.createChooser(i,"Choose a Platform"));
    }
}