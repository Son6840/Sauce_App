package com.daelim.sauce.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.daelim.sauce.R;

import java.util.ArrayList;
import java.util.Map;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private ArrayList<Map<String, Object>> items = new ArrayList<Map<String, Object>>();

    public MyAdapter(ArrayList<Map<String, Object>> resultList){
        this.items=resultList;
    }

    @NonNull
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item , parent, false);
        return new MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Map<String, Object> item = items.get(position);
        holder.setItem(item);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        protected TextView storeName;
        protected ImageView storeImg;

        public MyViewHolder(View itemView) {
            super(itemView);
            storeImg=itemView.findViewById(R.id.storeImg);
            storeName=itemView.findViewById(R.id.storeName);


        }


        public void setItem(Map<String, Object> item){

            //"rank", "movieNm", "openDt"은 Json파일에 저장되어 있던 key값
            storeName.setText(item.get("id").toString());


        }
    }
}