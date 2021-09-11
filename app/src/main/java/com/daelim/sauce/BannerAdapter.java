package com.daelim.sauce;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.shapes.RoundRectShape;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class BannerAdapter extends RecyclerView.Adapter<BannerAdapter.MyViewHolder> {

    private List<SliderItems> sliderItemsList;
    private ViewPager2 viewPager2;

    BannerAdapter(List<SliderItems> sliderItems, ViewPager2 viewPager2) {
        this.sliderItemsList = sliderItems;
        this.viewPager2 = viewPager2;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(
                R.layout.item_slider, parent, false
        ));
    }

    //이미지 교체 함수
    @Override
    public void onBindViewHolder(@NonNull BannerAdapter.MyViewHolder holder, int position) {
        holder.setImage(sliderItemsList.get(position));
        if (position == sliderItemsList.size() - 2) {
            viewPager2.post(runnable);
        }
    }

    @Override
    public int getItemCount() {
        return sliderItemsList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;

        public MyViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.bannerImage);
        }

        void setImage(SliderItems sliderItems) {
            imageView.setImageResource(sliderItems.getImage());
        }

    }
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            sliderItemsList.addAll(sliderItemsList);
            notifyDataSetChanged();
        }
    };
}


