package com.daelim.sauce;

public class Category_items {
    //카테고리 아이템 정보

    String name;
    int resId;

    public Category_items(String name,int resId){
        this.name = name;
        this.resId = resId;

    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
    public int getResId(){
        return resId;
    }
    public void setResId(int resId){
        this.resId = resId;
    }
}
