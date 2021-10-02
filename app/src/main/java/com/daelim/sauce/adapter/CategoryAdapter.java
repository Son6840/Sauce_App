package com.daelim.sauce.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.daelim.sauce.activity.MainActivity;
import com.daelim.sauce.R;
import com.daelim.sauce.items.Category_items;

import java.util.ArrayList;


public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {
    ArrayList<Category_items> cItems = new ArrayList<Category_items>();





    public CategoryAdapter(ArrayList<Category_items> category_items ) {
        this.cItems = category_items;

    }

    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // LayoutInflater를 이용하여 전 단계에서 만들었던 item.xml을 inflate 시킵니다.
        // return 인자는 ViewHolder 입니다.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_items, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Item을 하나, 하나 보여주는(bind 되는) 함수입니다.
        holder.onBind(cItems.get(position));
    }

    @Override
    public int getItemCount() {
        // RecyclerView의 총 개수 입니다.
        return cItems.size();
    }

    public void addItem(Category_items category_items) {
        // 외부에서 item을 추가시킬 함수입니다.
        cItems.add(category_items);
    }

    // RecyclerView의 핵심인 ViewHolder 입니다.
    // 여기서 subView를 setting 해줍니다.
    class ViewHolder extends RecyclerView.ViewHolder {


        private final TextView menuName;
        private final ImageView menuImage;

        ViewHolder(View itemView) {
            super(itemView);

            menuName = itemView.findViewById(R.id.menuName);
            menuImage = itemView.findViewById(R.id.menuIcon);
            MainActivity mainActivity = (MainActivity)itemView.getContext();
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(itemView.getContext(), "메뉴이름은"+menuName.getText()+"입니다",Toast.LENGTH_SHORT).show();

                    mainActivity.switchCategory(menuName.getText().toString());
                }
            });
        }

        void onBind(Category_items category_items) {
            menuName.setText(category_items.getName());
            menuImage.setImageResource(category_items.getResId());
        }
    }



}


