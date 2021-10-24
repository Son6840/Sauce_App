package com.daelim.sauce.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;

import com.daelim.sauce.R;

public class Login_detail extends AppCompatActivity {
    ImageButton id2, id3;
    private View id4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login_detail);

        id2 = (ImageButton) findViewById(R.id.ID2);
        id3 = (ImageButton) findViewById(R.id.ID3);
        id4 = (ImageButton) findViewById(R.id.ID4);

        id2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Login_detail.this, MainActivity.class);
                startActivity(i);
            }
        });
        id4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                id4.setVisibility(View.GONE);
            }
        });



    }
    public void click(View view){
        if(view.getId() == R.id.ID3){
            id4.setVisibility(View.VISIBLE);
        }
        else {
            id4.setVisibility(View.GONE);
        }
        return;
    }
}