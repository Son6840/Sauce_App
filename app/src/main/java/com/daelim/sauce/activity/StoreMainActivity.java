package com.daelim.sauce.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.daelim.sauce.R;
import com.daelim.sauce.adapter.BannerAdapter;
import com.daelim.sauce.adapter.RecyclerViewDecoration;
import com.daelim.sauce.adapter.storyAdapter;
import com.daelim.sauce.items.Banner1_items;
import com.daelim.sauce.items.Story_items;
import com.daelim.sauce.items.getStoryData;
import com.kakao.sdk.story.model.Story;

import java.util.ArrayList;
import java.util.List;


public class StoreMainActivity extends AppCompatActivity {

    ImageView storeBanner;
    TextView storeName;
    Button review_btn ;
    Button button ;
    private RecyclerView storyView;
    private storyAdapter sAdapter = new storyAdapter();
    private View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_main);

        button = findViewById(R.id.order_btn);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StoreMainActivity.this, Calling_detail.class);
                startActivity(intent);
            }
        });
        setBanner();
        setStory();

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

    public void setStory(){

        storyView = findViewById(R.id.story);
        storyView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        storyView.addItemDecoration(new RecyclerViewDecoration(-20));
        storyView.setAdapter(sAdapter);

        //load Story
        sAdapter.setStory(new getStoryData().getItems());



    }
}
