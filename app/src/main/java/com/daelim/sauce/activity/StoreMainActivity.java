package com.daelim.sauce.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.daelim.sauce.R;

public class StoreMainActivity extends AppCompatActivity {

    ImageView storeBanner;
    TextView storeName;
    Button review_btn ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_main);
        setBanner();

        final TextView review_msg = (TextView) findViewById(R.id.review_msg);
        review_btn = findViewById(R.id.review_btn);
        review_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // 커스텀 다이얼로그를 생성한다. 사용자가 만든 클래스이다.
                writeReviewActivity writeReviewActivity = new writeReviewActivity(StoreMainActivity.this);

                // 커스텀 다이얼로그를 호출한다.
                // 커스텀 다이얼로그의 결과를 출력할 TextView를 매개변수로 같이 넘겨준다.
                writeReviewActivity.callFunction(review_msg);

            }
        });
    }


    private void setBanner(){
        storeName = findViewById(R.id.storeName);
        storeBanner = findViewById(R.id.storeBanner);
        storeBanner.setImageResource(R.drawable.banner01);
        storeName.setText("가게 이름");
        }
    }

