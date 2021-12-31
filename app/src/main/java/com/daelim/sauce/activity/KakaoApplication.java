package com.daelim.sauce.activity;

import android.app.Application;

import com.kakao.sdk.common.KakaoSdk;
//초기화 액티비티
public class KakaoApplication extends Application {
    @Override
    public void onCreate() { // oncreate 에서 카카오 sdk api 를 호출해서 초기화
        super.onCreate();
        //키값
        KakaoSdk.init(this, "");
    }
}
