package com.daelim.sauce.fragment;

import android.os.Bundle;
import android.os.Message;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.daelim.sauce.R;
import com.daelim.sauce.RetrofitInterface;

import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class F_mypageAct extends Fragment {
    private View view;
    private ImageButton bt;
    Retrofit retrofit;
    String baseUrl = "http://10.0.2.2:8080";
    private String email;
    private String id;

    @Nullable

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frag_mypage, container, false);
        getuserinfo();


        bt = view.findViewById(R.id.update);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().replace(R.id.main_frame, new storeList()).commit();//메뉴리스트프래그먼트 출력
            }
        });
        return view;


    }

    final Button getEmail = (Button) dlg.findViewById(R.id.getEmail);
    getEmail.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onclick(View view) {
            // '확인' 버튼 클릭시 메인 액티비티에서 설정한 main_label에
            // 커스텀 다이얼로그에서 입력한 메시지를 대입한다.
//                review_msg.setText(message.getText().toString());
            Toast.makeText(context, "\"" + message.getText().toString() + "\" 을 입력하였습니다.", Toast.LENGTH_SHORT).show();

            String comment = message.getText().toString();

    public void getuserinfo() {
        retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitInterface retrofitInterface = retrofit.create(RetrofitInterface.class);

        Call<F_mypageAct> userListCall = retrofitInterface.getUserInfo(email, id);
        userListCall.enqueue(new Callback<F_mypageAct>() {
            @Override
            public void onResponse(Call<F_mypageAct> call, Response<F_mypageAct> response) {
                String getted_email = response.body().;
                int getted_id = response.body().getId();
                Log.e("getuserinfo()", "이메일 : " + getted_email + ",  아이디 : " + getted_id);

                }



            @Override
            public void onFailure(Call<F_mypageAct> call, Throwable t) {
                Log.e("error", "err");

            }
        });
    }


}

