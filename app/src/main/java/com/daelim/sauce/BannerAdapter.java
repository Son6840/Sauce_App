package com.daelim.sauce;

import android.content.Context;
import android.content.res.TypedArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class BannerAdapter extends RecyclerView.Adapter<BannerAdapter.MyViewHolder> {
    private TypedArray mItems; //drawable 경로 데이터 저장 배열
    private Context context;

    public BannerAdapter(Context context, TypedArray mItems)
    {
        this.context = context;
        this.mItems = mItems;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(context).inflate(R.layout.item_slider, parent, false);
        return new MyViewHolder(view);
    }

    //이미지 교체 함수
    @Override
    public void onBindViewHolder(@NonNull BannerAdapter.MyViewHolder holder, int position)
    {
        int index = position % mItems.length();

        if(index >= mItems.length())
        {
            index = 0;
        }
        holder.imageView.setImageResource(mItems.getResourceId(index, -1));
    }

    @Override
    public int getItemCount() {
        return Integer.MAX_VALUE; //이미지 슬라이드를 무한으로 할 수 있음
    }

    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        ImageView imageView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.bannerImage);
        }
    }
}
