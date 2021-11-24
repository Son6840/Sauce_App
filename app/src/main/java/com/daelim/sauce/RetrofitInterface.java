package com.daelim.sauce;

import retrofit2.http.Field;
import retrofit2.http.GET;


    import java.util.Map;
import com.daelim.sauce.fragment.F_mypageAct;
    import retrofit2.Call;
    import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

    public interface RetrofitInterface {
        @GET("/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json")
        Call<Map<String, Object>> getBoxOffice(@Query("key") String key,
                                               @Query("targetDt") String targetDt);

        @GET("/15039545/v1/uddi:142ee2ae-0988-41eb-998b-d67dace405ad")
        Call<Map<String, Object>> getV2(@Query("serviceKey") String serviceKey,
                                        @Query("page") String page,
                                        @Query("perPage") String perPage
        );
        @GET("/getuserinfo")
        Call<F_mypageAct> getUserInfo(@Query("email") String email,
                                              @Query("id") String id);



    }