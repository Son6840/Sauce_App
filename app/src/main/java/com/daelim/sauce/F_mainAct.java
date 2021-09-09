package com.daelim.sauce;

import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

public class F_mainAct extends Fragment {
    private View view;
    private Button movemenuList ;
    private View search_view;

    public TypedArray m_ArrayBannerImages;
    private ViewPager2 m_viewPager2Banner;
    private BannerAdapter bannerAdapter;
    @Nullable

    @Override
    public View onCreateView(@NonNull  LayoutInflater inflater, @Nullable  ViewGroup container, @Nullable  Bundle savedInstanceState) {
       view = inflater.inflate(R.layout.frag_main,container,false);

        search_view = view.findViewById(R.id.search_view);
        search_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SearchActivity.class);
                startActivity(intent);
            }
        });

       movemenuList = view.findViewById(R.id.movemenuList_btn);
       movemenuList.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
            getFragmentManager().beginTransaction().replace(R.id.main_frame,new storeList()).commit();//메뉴리스트프래그먼트 출력
           }
       });

//       Init();
//        m_viewPager2Banner.setAdapter(bannerAdapter);
//        m_viewPager2Banner.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);


        return view;
    }

    private void Init(){
        m_viewPager2Banner = view.findViewById(R.id.bannerImage);
        m_ArrayBannerImages = getResources().obtainTypedArray(R.array.bannerImages);

        bannerAdapter = new BannerAdapter(getActivity(),m_ArrayBannerImages);

    }
}

