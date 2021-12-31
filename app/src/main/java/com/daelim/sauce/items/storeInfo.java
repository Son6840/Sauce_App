package com.daelim.sauce.items;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class storeInfo {

   @SerializedName("Stores")
   @Expose
   public ArrayList<storeDATA> stores;

   storeInfo(ArrayList<storeDATA> stores) {
      this.stores = stores;
   }

   public ArrayList<storeDATA> getStores() {
      return stores;
   }

   public void setStores(ArrayList<storeDATA> stores) {
      this.stores = stores;
   }


   public String toget() {
      return String.valueOf(stores);
   }
}