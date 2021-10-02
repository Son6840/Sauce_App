package com.daelim.sauce.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.daelim.sauce.R;
import com.daelim.sauce.items.Banner1_items;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class BannerAdapter extends RecyclerView.Adapter<BannerAdapter.MyViewHolder> {

    private List<Banner1_items> banner1itemsList;
    private ViewPager2 viewPager2;

    public BannerAdapter(List<Banner1_items> banner1itemList, ViewPager2 viewPager2) {
        this.banner1itemsList = banner1itemList;
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
        holder.setImage(banner1itemsList.get(position));
        if (position == banner1itemsList.size() - 2) {
            viewPager2.post(runnable);
        }
    }

    @Override
    public int getItemCount() {
        return banner1itemsList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;

        public MyViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.bannerImage);
        }

        void setImage(Banner1_items banner1items) {
            imageView.setImageResource(banner1items.getImage());
        }

    }
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            banner1itemsList.addAll(banner1itemsList);
            notifyDataSetChanged();
        }
    };
}


