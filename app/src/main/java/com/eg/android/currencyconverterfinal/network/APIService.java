package com.eg.android.currencyconverterfinal.network;



import com.eg.android.currencyconverterfinal.model.ConversionJSONResponse;
import com.eg.android.currencyconverterfinal.model.CurrencyJSONResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIService {

    // 99b0a4dc-9d79-4906-852a-ed00eb729af1
    // 214b8190-a1f6-4b11-bdd3-296353760199
    // live?access_key=2b3c5c4886b24f13b8196289f93adf50
    @GET("live")
    Call<ConversionJSONResponse> getConversionList(
            @Query("access_key") String key,
            @Query("source") String source
    );

    @GET("list")
    Call<CurrencyJSONResponse> getCurrencyList(
            @Query("access_key") String key
    );



}
