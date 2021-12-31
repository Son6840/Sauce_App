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
//카카오 로그인 액티비티
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
                // 웹페이지를 통해서 연결하면 되는데 동일하게 콜백을 받으면 됩니다.
                // 콜백을 동일하게 받기 때문에 별도의 콜백 객체로 뽑아냄
                // 양쪽 모두 파라메타로 전달
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
// updateKakaoLoginUi() 메소드를 만들어서 이것을 호출
// 메소드 안에서는 사용자가 카카오톡 계정으로 로그인이 되어있는 상태 확인
    private void updateKakaoLoginUi() {
        //UserApiClient 클라이언트 제공 내 계정정보 얻어 오고 파라메터에서는 new function2 객체를 파라메타로 전달
        UserApiClient.getInstance().me(new Function2<User, Throwable, Unit>() {
            @Override
            // invoke 라는 메소드를 콜백으로 호출
            public Unit invoke(User user, Throwable throwable) {
                // user가 null인지 아닌지 판단하면 로그인 상태 여부 판단가능
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
                    // profileImage 위한 클래스 변수를 만들고
                    //로그인 버튼과 로그아웃 프로필 이미지를 불러온다.
                    // glide 이미지로딩 라이브러리
                    // 토큰 전달 로그인 성공 토큰 미전달시 로그인 실패
                    // 토큰이 널이 아니면 로그인 되었을때 처리하면 되고
                    // 결과가 오류가 있다면 throwable이 null이 아니기 때문이다
                    Glide.with(profileImage).load(user.getKakaoAccount().getProfile().getThumbnailImageUrl()).into(profileImage);

                    profileImage.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent i = new Intent(Login.this, MainActivity.class);
                            startActivity(i);
                        }
                    });
                    // 버튼을 숨기기 위해서 loginbutton, logoutbutton을 클래스 멤버로 생성하고
                    // 로그인 버튼에 visibility를 화면에서 사라지게 만들고
                    loginButton.setVisibility(View.GONE);
                    // 로그아웃버튼을 visible로 나타나게 만든다
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