package com.daelim.sauce;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.material.tabs.TabLayout;
import com.kakao.sdk.auth.model.OAuthToken;
import com.kakao.sdk.user.UserApiClient;
import com.kakao.sdk.user.model.User;

import kotlin.Unit;
import kotlin.jvm.functions.Function2;

public class Login extends AppCompatActivity {
    private static final String TAG = "Login";
    EditText mID;
    EditText mPW;
    private View loginButton, logoutButton;
    private TextView nickName;
    private ImageView profileImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);

        loginButton = findViewById(R.id.login2);
        logoutButton = findViewById(R.id.logout);
        //nickName = findViewById(R.id.nickname);
       // profileImage = findViewById(R.id.profile);

      /*  Function2<OAuthToken, Throwable, Unit> callback = new Function2<OAuthToken, Throwable, Unit>() {
            @Override
            public Unit invoke(OAuthToken oAuthToken, Throwable throwable) {
                if (oAuthToken != null) {
                    // TBD
                }
                if (throwable != null) {
                    // TBD
                }
                updateKakaoLoginUi();
                return null;
            }
        };*/







        updateKakaoLoginUi();


        mID = (EditText) findViewById(R.id.ID);
        mPW = (EditText) findViewById(R.id.PASSWORD);

        Button join = (Button) findViewById(R.id.Join);
        Button home = (Button) findViewById(R.id.home);

        join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Login.this, Calling_detail.class);
                startActivity(i);
            }
        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Login.this, MainActivity.class);
                startActivity(i);
            }
        });

    }

    private void updateKakaoLoginUi() {
        UserApiClient.getInstance().me(new Function2<User, Throwable, Unit>() {
            @Override
            public Unit invoke(User user, Throwable throwable) {
                if(user !=null){
                    Log.i(TAG,"invoke: id=" + user.getId());
                    Log.i(TAG,"invoke: email=" + user.getKakaoAccount().getEmail());
                    Log.i(TAG,"invoke: nickname=" + user.getKakaoAccount().getProfile().getNickname());
                    Log.i(TAG,"invoke: gender=" + user.getKakaoAccount().getGender());
                    Log.i(TAG,"invoke: age=" + user.getKakaoAccount().getAgeRange());

                    nickName.setText(user.getKakaoAccount().getProfile().getNickname());
                   Glide.with(profileImage).load(user.getKakaoAccount().getProfile().getThumbnailImageUrl()).into(profileImage);
                    loginButton.setVisibility(View.GONE);
                    logoutButton.setVisibility(View.VISIBLE);
                } else{
                    nickName.setText(null);
                    profileImage.setImageBitmap(null);
                    logoutButton.setVisibility(View.VISIBLE);
                    logoutButton.setVisibility(View.GONE);

                }
                return null;
            }
        });
    }

}