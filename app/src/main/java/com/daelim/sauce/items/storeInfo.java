package com.daelim.sauce.items;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class storeInfo {

   @SerializedName("body")
    public List<Data> body;

   public String toString(){
       return "storeinfo{" +
               "body ="+body+"}";
   }
}
 class Data{

    @SerializedName("temholiday") private String temholiday;

    @SerializedName("scate") private String scate;

    @SerializedName("breaktime") private String breaktime;

    @SerializedName("address") private String address;

    @SerializedName("lon") private String lon;

    @SerializedName("holiday") private String holiday;

    @SerializedName("opening") private String opening;

    @SerializedName("boss_name") private String boss_name;

    @SerializedName("number") private String number;

    @SerializedName("store_phone") private String store_phone;

    @SerializedName("intro") private String intro;

    @SerializedName("store_name") private String store_name;

    @SerializedName("id") private String id;

    @SerializedName("created_date") private String created_date;

    @SerializedName("lat") private String lat;
}
