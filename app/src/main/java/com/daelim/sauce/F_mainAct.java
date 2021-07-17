package com.daelim.sauce;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class F_mainAct extends Fragment {
    private View view;
    private Button movemenuList ;
    MainActivity mainActivity = new MainActivity();
    @Nullable

    @Override
    public View onCreateView(@NonNull  LayoutInflater inflater, @Nullable  ViewGroup container, @Nullable  Bundle savedInstanceState) {
       view = inflater.inflate(R.layout.frag_main,container,false);


       movemenuList = view.findViewById(R.id.movemenuList_btn);
       movemenuList.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
            getFragmentManager().beginTransaction().replace(R.id.main_frame,new storeList()).commit();//메뉴리스트프래그먼트 출력
           }
       });
        return view;
    }

}

