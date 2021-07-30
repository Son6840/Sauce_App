package com.daelim.sauce;

import android.app.Application;

import com.kakao.sdk.common.KakaoSdk;

public class KakaoApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        KakaoSdk.init(this, "68f5d7c8e5adbdb43b33e5fdfc5afb8d");
    }
}
