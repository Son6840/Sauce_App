package com.daelim.sauce.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.daelim.sauce.R;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {

    private EditText editSearch;        // 검색어를 입력할 Input 창

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        editSearch = (EditText) findViewById(R.id.search_bar);
        editSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override

            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    //음식점찾기
                    return true;

                }
                return false;
            }

        });



    }
}