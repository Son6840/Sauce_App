
package com.daelim.sauce.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.daelim.sauce.R;

public class Calling_detail extends AppCompatActivity {
// 전화걸기 엑티비티
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calling_detail);

    }
    public void dialPhone(View view) {
        EditText editText = (EditText) findViewById(R.id.phone_number_edit);// phone_number_edit id를 불러와서 EditText 선언
        dialPhoneNumber(editText.getText().toString());//dialPhoneNumber 클래스를 만들어서 intent 구현 후 입력 값 받아오기
    }
    public void dialPhoneNumber(String phoneNumber) {
                Intent intent = new Intent(Intent.ACTION_DIAL); //다이얼 앱을 실행하기 위한 액션을 인텐트에 설정
                intent.setData(Uri.parse("tel:"+phoneNumber)); //전화번호를 담는 URI 객체를 정의하세요

                if(intent.resolveActivity(getPackageManager()) !=null) {
                    startActivity(intent); //지금까지 정의한 인텐트를 이용하여 다이얼 앱을 실행하세요.

        }
    }
}