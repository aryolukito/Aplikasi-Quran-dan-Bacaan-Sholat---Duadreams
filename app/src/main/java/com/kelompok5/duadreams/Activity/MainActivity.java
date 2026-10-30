package com.kelompok5.duadreams.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;

import com.kelompok5.duadreams.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ConstraintLayout btn1=findViewById(R.id.btn1);
        btn1.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, NiatSalatActivity.class)));

        ConstraintLayout btn2=findViewById(R.id.btn2);
        btn2.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, QuranListActivity.class)));

        ConstraintLayout btn3=findViewById(R.id.btn3);
        btn3.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, BacaanSalatActivity.class)));

        ConstraintLayout btn4=findViewById(R.id.btn4);
        btn4.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, AyatKursiActivity.class)));
    }
}