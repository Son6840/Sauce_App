package com.daelim.sauce.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.daelim.sauce.R;
import com.kakao.sdk.auth.model.OAuthToken;
import com.kakao.sdk.user.UserApiClient;
import com.kakao.sdk.user.model.User;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;

public class Login extends AppCompatActivity {
    private static final String TAG = "LoginActivity";
    EditText mID;
    EditText mPW;
    Button join;
    Button home;
    ImageButton owner;
    TextView nothing;
    private View loginButton, logoutButton;
    private TextView nickName;
    private ImageView profileImage;
    String st;
    String str;
    // private ImageView imageView2;



    // OAuthLogin mOAuthLoginModule;
    Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);
        mContext = getApplicationContext();

        // imageView2 = findViewById(R.id.imageView2);
        loginButton = findViewById(R.id.login);
        logoutButton = findViewById(R.id.logout);
        nickName = findViewById(R.id.nickname);
        profileImage = findViewById(R.id.profile);
        nothing = findViewById(R.id.nothing);





       /* imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOAuthLoginModule = OAuthLogin.getInstance();
                mOAuthLoginModule.init(
                        mContext
                        ,getString(R.string.naver_client_id)
                        ,getString(R.string.naver_client_secret)
                        ,getString(R.string.naver_client_name)
                );
                @SuppressLint("HandlerLeak")
                OAuthLoginHandler mOAuthLoginHandler = new OAuthLoginHandler() {
                    @Override
                    public void run(boolean success) {
                        if (success) {
                            String accessToken = mOAuthLoginModule.getAccessToken(mContext);
                            String refreshToken = mOAuthLoginModule.getRefreshToken(mContext);
                            long expiresAt = mOAuthLoginModule.getExpiresAt(mContext);
                            String tokenType = mOAuthLoginModule.getTokenType(mContext);

                            Log.i("LoginData","accessToken : "+ accessToken);
                            Log.i("LoginData","refreshToken : "+ refreshToken);
                            Log.i("LoginData","expiresAt : "+ expiresAt);
                            Log.i("LoginData","tokenType : "+ tokenType);

                        } else {
                            String errorCode = mOAuthLoginModule
                                    .getLastErrorCode(mContext).getCode();
                            String errorDesc = mOAuthLoginModule.getLastErrorDesc(mContext);
                            Toast.makeText(mContext, "errorCode:" + errorCode
                                    + ", errorDesc:" + errorDesc, Toast.LENGTH_SHORT).show();
                        }
                    };
                };

                mOAuthLoginModule.startOauthLoginActivity(Login.this, mOAuthLoginHandler);
            }
        });

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOAuthLoginModule.logout(mContext);
                Toast.makeText(Login.this, "로그아웃 되었습니다.", Toast.LENGTH_SHORT).show();
            }
        });
*/




        Function2<OAuthToken, Throwable, Unit> callback = new Function2<OAuthToken, Throwable, Unit>() {
            @Override
            public Unit invoke(OAuthToken oAuthToken, Throwable throwable) {
                if (oAuthToken != null) {

                }
                if (throwable != null) {

                }
                updateKakaoLoginUi();
                return null;
            }
        };


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (UserApiClient.getInstance().isKakaoTalkLoginAvailable(Login.this)) {
                    UserApiClient.getInstance().loginWithKakaoTalk(Login.this, callback);
                } else {
                    UserApiClient.getInstance().loginWithKakaoAccount(Login.this, callback);

                }
            }
        });

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserApiClient.getInstance().logout(new Function1<Throwable, Unit>() {
                    @Override
                    public Unit invoke(Throwable throwable) {
                        updateKakaoLoginUi();
                        return null;
                    }
                });

            }
        });


        updateKakaoLoginUi();


        mID = (EditText) findViewById(R.id.ID);
        mPW = (EditText) findViewById(R.id.PASSWORD);

        join = (Button) findViewById(R.id.Join);
        home = (Button) findViewById(R.id.home);
        owner = (ImageButton) findViewById(R.id.owner);

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
        owner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*mPW.setVisibility(View.VISIBLE);
                mID.setVisibility(View.VISIBLE);
                join.setVisibility(View.GONE);
                home.setVisibility(View.GONE);
                nothing.setVisibility(View.GONE);
                loginButton.setVisibility(View.GONE);*/
                Intent i = new Intent(Login.this, Login_detail.class);
                startActivity(i);
            }
        });



    }

    private void updateKakaoLoginUi() {
        UserApiClient.getInstance().me(new Function2<User, Throwable, Unit>() {
            @Override
            public Unit invoke(User user, Throwable throwable) {
                if(user != null){
                    Log.i(TAG,"invoke: id=" + user.getId());
//                    long num = user.getId();
//                    String str = String.valueOf(num);
//                   if(str.equals("1827975262")) {
//                       Intent i = new Intent(Login.this, Login_detail2.class);
//                       startActivity(i);
//                   }
                    Log.i(TAG,"invoke: email=" + user.getKakaoAccount().getEmail());
                    Log.i(TAG,"invoke: nickname=" + user.getKakaoAccount().getProfile().getNickname());
                    Log.i(TAG,"invoke: gender=" + user.getKakaoAccount().getGender());
                    Log.i(TAG,"invoke: age=" + user.getKakaoAccount().getAgeRange());


                    Glide.with(profileImage).load(user.getKakaoAccount().getProfile().getThumbnailImageUrl()).into(profileImage);

                    profileImage.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent i = new Intent(Login.this, MainActivity.class);
                            startActivity(i);
                        }
                    });
                    loginButton.setVisibility(View.GONE);
                    logoutButton.setVisibility(View.VISIBLE);
                    mID.setVisibility(View.GONE);
                    mPW.setVisibility(View.GONE);
                    owner.setVisibility(View.GONE);


                } else{

                    profileImage.setImageBitmap(null);
                    loginButton.setVisibility(View.VISIBLE);
                    logoutButton.setVisibility(View.GONE);
                    owner.setVisibility(View.VISIBLE);

                }
                return null;
            }
        });
    }


}