package com.daelim.sauce.fragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.daelim.sauce.adapter.MyAdapter;
import com.daelim.sauce.R;
import com.daelim.sauce.RetrofitInterface;
import com.daelim.sauce.activity.StoreMainActivity;

import java.util.ArrayList;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class storeList extends Fragment {

    private View view;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private Button btn;
    private Button nbtn;

    String baseUrl = "http://www.kobis.or.kr";
    String API_KEY = "d07e74658ecb48b7b1e3afd00b459338";
    Retrofit retrofit;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frag_storelist, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.rv_recycleview);

        Retrofit();
        // onCreate()..
        btn = view.findViewById(R.id.rcv_show);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerView.setAdapter(mAdapter);
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


    public void Retrofit() {

        retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RetrofitInterface retrofitInterface = retrofit.create(RetrofitInterface.class);

        retrofitInterface.getBoxOffice(API_KEY, "20210916").enqueue(new Callback<Map<String, Object>>() {
            @Override
            public void onResponse(Call<Map<String, Object>> call, Response<Map<String, Object>> response) {

                Map<String, Object> boxOfficeResult = (Map<String, Object>) response.body().get("boxOfficeResult");
                ArrayList<Map<String, Object>> jsonList = (ArrayList) boxOfficeResult.get("dailyBoxOfficeList");
                mAdapter = new MyAdapter(jsonList);

            }

            @Override
            public void onFailure(Call<Map<String, Object>> call, Throwable t) {

            }
        });

    }
}







