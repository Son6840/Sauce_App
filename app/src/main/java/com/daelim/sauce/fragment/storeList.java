package com.daelim.sauce.fragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.daelim.sauce.adapter.MyAdapter;
import com.daelim.sauce.R;
import com.daelim.sauce.RetrofitInterface;
import com.daelim.sauce.activity.StoreMainActivity;
import com.daelim.sauce.items.storeDATA;
import com.daelim.sauce.items.storeInfo;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
// Retrofit을 이용한 스토어리스트 구현
public class storeList extends Fragment {
    public static Context context ;
    private View view;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private Button btn;
    private Button nbtn;
    //
    private final static String baseUrl = "http://10.0.2.2:8080";
    Retrofit retrofit;

    private String id;
    private String store_name;
    private String address;

    storeInfo storeInfo ;
    storeDATA storeDATA = new storeDATA();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frag_storelist, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.rv_recycleview);

        setStoreList();

        // onCreate()..
        btn = view.findViewById(R.id.rcv_show);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setStoreList();
            }
        });

        nbtn = view.findViewById(R.id.nbtn);
        nbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), StoreMainActivity.class); startActivity(intent);

            }
        });

        return view;
    }


    public void setStoreList() {

        retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitInterface retrofitInterface = retrofit.create(RetrofitInterface.class);

        Call<storeInfo> callinfo = retrofitInterface.getStoreList("E");
        callinfo.enqueue(new Callback<storeInfo>() {


            @Override
            public void onResponse(Call<storeInfo> call, Response<storeInfo> response) {
                Log.e("sucess", storeInfo.toget());

            }

            @Override
            public void onFailure(Call<storeInfo> call, Throwable t) {
                Log.e("err",t.toString());
            }
        });


//        retrofitInterface.getStoreList().enqueue(new Callback<Map<String, Object>>() {
//            @Override
//            public void onResponse(Call<Map<String, Object>> call, Response<Map<String, Object>> response) {
//
////                Map<String, Object> boxOfficeResult = (Map<String, Object>) response.body();
//                ArrayList<Map<String, Object>> jsonList = (ArrayList) body.get("dailyBoxOfficeList");
//                mAdapter = new MyAdapter(jsonList);
//
//            }
//
//            @Override
//            public void onFailure(Call<Map<String, Object>> call, Throwable t) {
//
//            }
//        });

    }


}








