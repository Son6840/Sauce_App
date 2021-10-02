package com.daelim.sauce;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;

import com.daelim.sauce.activity.MainActivity;

public class Login_detail2 extends AppCompatActivity {
    ImageButton id3;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login_detail2);

        id3 =(ImageButton) findViewById(R.id.ID3);


        id3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Login_detail2.this, MainActivity.class);
                startActivity(i);
            }
        });






    }

}