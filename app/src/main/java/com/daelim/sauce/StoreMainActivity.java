package com.daelim.sauce;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class StoreMainActivity extends AppCompatActivity {

    ImageView storeBanner;
    TextView storeName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_main);
        setBanner();



    }

    private void setBanner(){
        storeName = findViewById(R.id.storeName);
        storeBanner = findViewById(R.id.storeBanner);
        storeBanner.setImageResource(R.drawable.banner01);
        storeName.setText("가게 이름");
    }
}