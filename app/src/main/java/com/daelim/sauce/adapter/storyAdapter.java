package com.daelim.sauce.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.daelim.sauce.R;
import com.daelim.sauce.items.Story_items;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Map;

public class storyAdapter extends RecyclerView.Adapter<storyAdapter.ViewHolder> {


    private ArrayList<Story_items> sList = new ArrayList<>();



    @NonNull
    @NotNull
    @Override
    public storyAdapter.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.story_items, parent, false);
        storyAdapter.ViewHolder vh = new storyAdapter.ViewHolder(view);

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull storyAdapter.ViewHolder holder, int position) {

        Story_items story_items = sList.get(position);

        Glide.with(holder.itemView.getContext())
                .load(story_items.getStory())
                .into(holder.img_item);

        //holder.name.setText(story_items.getStoryName);
    }

    @Override
    public int getItemCount() {
        return sList.size();
    }

    public void setStory(ArrayList<Story_items> sList){

        this.sList = sList;
    }


    class ViewHolder extends RecyclerView.ViewHolder{
        ImageView img_item;

         ViewHolder(View itemView) {
            super(itemView);
            img_item = itemView.findViewById(R.id.storyImg);
        }
    }


}
