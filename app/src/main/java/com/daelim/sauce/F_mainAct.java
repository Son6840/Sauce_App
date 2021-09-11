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
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import java.util.ArrayList;
import java.util.List;

public class F_mainAct extends Fragment {
    private View view;
    private Button movemenuList ;
    private View search_view;


    private ViewPager2 banner1;
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

        setBanner1();

        return view;
    }

    private void setBanner1(){
        banner1 = view.findViewById(R.id.banner1);
        List<SliderItems>sliderItems = new ArrayList<>();
        sliderItems.add(new SliderItems(R.drawable.banner01));
        sliderItems.add(new SliderItems(R.drawable.banner02));
        sliderItems.add(new SliderItems(R.drawable.banner03));
        banner1.setAdapter(new BannerAdapter(sliderItems,banner1));


        banner1.setClipToPadding (false) ;
        banner1.setClipChildren (false) ;
        banner1.setOffscreenPageLimit ( 3 ) ;
        banner1.getChildAt ( 0 ) .setOverScrollMode (RecyclerView. OVER_SCROLL_NEVER ) ;

        CompositePageTransformer compositePageTransformer = new CompositePageTransformer () ;
        compositePageTransformer.addTransformer (new MarginPageTransformer( 40 )) ;
        compositePageTransformer.addTransformer (new ViewPager2.PageTransformer () {
            @Override
            public void transformPage (@NonNull View page , float position) {
                float r = 1 - Math. abs (position) ;
                page.setScaleY ( 0.85f + r * 0.15f ) ;
            }
        }) ;

        banner1.setPageTransformer (compositePageTransformer) ;



   Runnable sliderRunnable = new Runnable () {
        @Override
        public void run () {
            banner1.setCurrentItem (banner1.getCurrentItem () + 1 ) ;
        }
    } ;

}
}



