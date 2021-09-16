package com.daelim.sauce;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

public class F_mainAct extends Fragment {
    private View view;
    private Button movemenuList ;
    private View search_view;


    private ViewPager2 banner1;
    private ViewPager2 banner2;
    private BannerAdapter bannerAdapter;

    private RecyclerView categoryView ;
    private CategoryAdapter categoryAdapter ;
    private FragmentActivity myContext;
    Context mycontext;
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

//       movemenuList = view.findViewById(R.id.movemenuList_btn);
//       movemenuList.setOnClickListener(new View.OnClickListener() {
//           @Override
//           public void onClick(View v) {
//            getFragmentManager().beginTransaction().replace(R.id.main_frame,new storeList()).commit();//메뉴리스트프래그먼트 출력
//           }
//       });

        setBanner1();
        setBanner2();
        setCategory();

        return view;
    }

    private void setBanner1(){
        banner1 = view.findViewById(R.id.banner1);
        List<Banner1_items>sliderItems = new ArrayList<>();
        sliderItems.add(new Banner1_items(R.drawable.banner01));
        sliderItems.add(new Banner1_items(R.drawable.banner02));
        sliderItems.add(new Banner1_items(R.drawable.banner03));
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

    private void setBanner2(){
        banner2 = view.findViewById(R.id.banner2);
        List<Banner2_items>sliderItems = new ArrayList<>();
        sliderItems.add(new Banner2_items(R.drawable.banner01));
        sliderItems.add(new Banner2_items(R.drawable.banner02));
        sliderItems.add(new Banner2_items(R.drawable.banner03));
        banner2.setAdapter(new Banner2Adapter(sliderItems,banner2));


        banner2.setClipToPadding (false) ;
        banner2.setClipChildren (false) ;
        banner2.setOffscreenPageLimit ( 3 ) ;
        banner2.getChildAt ( 0 ) .setOverScrollMode (RecyclerView. OVER_SCROLL_NEVER ) ;

        CompositePageTransformer compositePageTransformer = new CompositePageTransformer () ;
        compositePageTransformer.addTransformer (new MarginPageTransformer( 40 )) ;
        compositePageTransformer.addTransformer (new ViewPager2.PageTransformer () {
            @Override
            public void transformPage (@NonNull View page , float position) {
                float r = 1 - Math. abs (position) ;
                page.setScaleY ( 0.85f + r * 0.15f ) ;
            }
        }) ;

        banner2.setPageTransformer (compositePageTransformer) ;



        Runnable sliderRunnable = new Runnable () {
            @Override
            public void run () {
                banner2.setCurrentItem (banner2.getCurrentItem () + 1 ) ;
            }
        } ;

    }
    public void setCategory(){
        categoryView = (RecyclerView)view.findViewById(R.id.category);
        ArrayList<Category_items> category_items = new ArrayList<>();
        CategoryAdapter categoryAdapter = new CategoryAdapter(category_items);

        categoryAdapter.addItem(new Category_items("chicken",R.drawable.banner01));
        categoryAdapter.addItem(new Category_items("pizza", R.drawable.button));
        categoryAdapter.addItem(new Category_items("ham", R.drawable.group98));

        RecyclerView.LayoutManager layoutManager=new GridLayoutManager(getActivity(),2);
        categoryView.setLayoutManager(layoutManager);
        categoryView.setAdapter(categoryAdapter);
    }







    }







