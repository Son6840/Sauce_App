package com.daelim.sauce.fragment;

import android.os.Bundle;
import android.os.Message;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.daelim.sauce.R;
import com.daelim.sauce.RetrofitInterface;
import com.daelim.sauce.items.mypageinfo;
import com.daelim.sauce.items.storeInfo;

import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class F_mypageAct extends Fragment {
    private View view;
    private ImageButton bt;
    private EditText editText;
    private EditText editText2;
    private EditText editText3;
    private EditText editText4;
    Retrofit retrofit;
    String baseUrl = "http://10.0.2.2:8080";
    private String email;
    private String id;
    private String pn;
    private String nk;

    @Nullable

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frag_mypage, container, false);
        getUserinfo();

        editText = view.findViewById(R.id.getEmail);
        editText2= view.findViewById(R.id.id5);
        editText3 = view.findViewById(R.id.id8);
        editText4 = view.findViewById(R.id.id9);
        bt = view.findViewById(R.id.update);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().replace(R.id.main_frame, new storeList()).commit();//메뉴리스트프래그먼트 출력
            }
        });
        return view;


    }

//  final Button getEmail = (Button) dlg.findViewById(R.id.getEmail);
//    getEmail.setOnClickListener(new View.OnClickListener() {
//        @Override
//        public void onclick(View view) {
    // '확인' 버튼 클릭시 메인 액티비티에서 설정한 main_label에
    // 커스텀 다이얼로그에서 입력한 메시지를 대입한다.
//                review_msg.setText(message.getText().toString());
//            Toast.makeText(context, "\"" + message.getText().toString() + "\" 을 입력하였습니다.", Toast.LENGTH_SHORT).show();

//            String comment = message.getText().toString();

    public void getUserinfo() {

        retrofit = new Retrofit.Builder()
                // baseUrl을 적어서 초기화
                .baseUrl(baseUrl)
                // 데이터를 파싱할 Converter 제공
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitInterface retrofitInterface = retrofit.create(RetrofitInterface.class);

        Call<mypageinfo> userListCall = retrofitInterface.getUserInfo(email, id, pn, nk );
        // enqueue 비동기 방식
        userListCall.enqueue(new Callback<mypageinfo>() {


            @Override
            public void onResponse(Call<mypageinfo> call, Response<mypageinfo> response) {
                // 통신에 성공한경우
                // userid,email,phonenumber,nickname을 받아와서 화면에 보여준다
                String get_userid = "1827975262";
                String get_email = "jdonghun45@gmail.com";
                String get_phonenumber = "01048903506";
                String get_nickname = "JUNG";
//                String get_userid = response.body().getUserid();
//                String get_email = response.body().getEmail();
                Log.e("getF_mypageAct()", "아이디 : " + "1827975262" + ", 이메일 : " + "jdonghun45@gmail.com");
                editText.setText(get_userid);
                editText2.setText(get_email);
                editText3.setText(get_phonenumber);
                editText4.setText(get_nickname);
            }
//                String getted_email = response.body();
//                int getted_id = response.body().getId();
//                Log.e("getuserinfo()", "이메일 : " + getted_email + ",  아이디 : " + getted_id);



            @Override
            public void onFailure(Call<mypageinfo> call, Throwable t) {
                // 통신에 실패한 경우
                Log.e("error", "err");

            }

        });



    }

}

