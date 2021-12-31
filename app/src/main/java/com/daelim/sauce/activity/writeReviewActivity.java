package com.daelim.sauce.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Message;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.app.AlertDialog;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.daelim.sauce.R;
import com.daelim.sauce.RetrofitInterface;
import com.daelim.sauce.adapter.MyAdapter;

import java.util.ArrayList;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class writeReviewActivity {

    private Context context;
    Retrofit retrofit;
    String baseUrl = "http://10.0.2.2:8080";
    String userid = "1";
    String storeid = "2";


    public writeReviewActivity(Context context) {
        this.context = context;
    }

    // 호출할 다이얼로그 함수를 정의한다.
    public void callFunction(final TextView review_msg) {

        //  Dialog클래스를 생성
        Dialog dlg = new Dialog(context);

        // 액티비티의 타이틀바를 숨김.
        dlg.requestWindowFeature(Window.FEATURE_NO_TITLE);

        // 커스텀 다이얼로그의 레이아웃을 설정
        dlg.setContentView(R.layout.activity_write_review);

//        DisplayMetrics dm = getApplicationContext().getResources().getDisplayMetrics();
//        int width = (int) (dm.widthPixels * 0.7); // Display 사이즈의 70%
//        getWindow().getAttributes().width = width;

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dlg.getWindow().getAttributes());
        lp.width = 700;
        lp.height = 700;
        Window window = dlg.getWindow();
        window.setAttributes(lp);

        dlg.show();


        // 커스텀 다이얼로그의 각 위젯들을 정의
        final EditText message = (EditText) dlg.findViewById(R.id.review_msg);
        final Button okButton = (Button) dlg.findViewById(R.id.okButton);
        final Button cancelButton = (Button) dlg.findViewById(R.id.cancelButton);


        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // '확인' 버튼 클릭시 메인 액티비티에서 설정한 main_label에
                // 커스텀 다이얼로그에서 입력한 메시지를 대입
//                review_msg.setText(message.getText().toString());
                Toast.makeText(context, "\"" + message.getText().toString() + "\" 을 입력하였습니다.", Toast.LENGTH_SHORT).show();

                String comment = message.getText().toString();

                retrofit = new Retrofit.Builder()
                        .baseUrl(baseUrl)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                RetrofitInterface retrofitInterface = retrofit.create(RetrofitInterface.class);

                retrofitInterface.sendrev("2", "2", comment).enqueue(new Callback<Message>() {


                    @Override
                    public void onResponse(Call<Message> call, Response<Message> response) {
                        final Message message = response.body();

                    }


                    @Override
                    public void onFailure(Call<Message> call, Throwable t) {
                        t.printStackTrace();
                    }


                });

                // 커스텀 다이얼로그를 종료한다.
                dlg.dismiss();
            }
        });
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "취소 했습니다.", Toast.LENGTH_SHORT).show();

                // 커스텀 다이얼로그를 종료한다.
                dlg.dismiss();
            }
        });
    }


}





