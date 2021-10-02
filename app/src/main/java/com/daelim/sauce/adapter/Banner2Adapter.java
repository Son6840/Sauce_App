package com.daelim.sauce.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.daelim.sauce.R;
import com.daelim.sauce.items.Banner2_items;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class Banner2Adapter extends RecyclerView.Adapter<Banner2Adapter.MyViewHolder> {
    private List<Banner2_items> banner2itemsList;
    private ViewPager2 viewPager2;

    public Banner2Adapter(List<Banner2_items> banner2itemList, ViewPager2 viewPager2) {
        this.banner2itemsList = banner2itemList;
        this.viewPager2 = viewPager2;
    }


    @NonNull
    @Override
    public Banner2Adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Banner2Adapter.MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(
                R.layout.item_slider, parent, false
        ));
    }

    //이미지 교체 함수
    @Override
    public void onBindViewHolder(@NonNull Banner2Adapter.MyViewHolder holder, int position) {
        holder.setImage(banner2itemsList.get(position));
        if (position == banner2itemsList.size() - 2) {
            viewPager2.post(runnable);
        }
    }

    @Override
    public int getItemCount() {
        return banner2itemsList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;

        public MyViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.bannerImage);
        }

        void setImage(Banner2_items banner2items) {
            imageView.setImageResource(banner2items.getImage());
        }

    }
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            banner2itemsList.addAll(banner2itemsList);
            notifyDataSetChanged();
        }
    };
}
