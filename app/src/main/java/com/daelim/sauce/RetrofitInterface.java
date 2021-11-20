package com.daelim.sauce;

import android.os.Message;

import com.daelim.sauce.items.storeInfo;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.Call;


import java.util.Map;

import retrofit2.http.Query;

    public interface RetrofitInterface {

        @GET("/test")
        Call<storeInfo> getStoreList(@Query("id") String id,
                                     @Query("store_name") String store_name,
                                     @Query("address") String address);


        @GET("/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json")
        Call<Map<String, Object>> getBoxOffice(@Query("key") String key,
                                               @Query("targetDt") String targetDt);


        @FormUrlEncoded
        @POST("/add/review")
        Call<Message> sendrev(@Field("userid") String userid,
                              @Field("storeid") String storeid,
                              @Field("comment") String comment );



    }