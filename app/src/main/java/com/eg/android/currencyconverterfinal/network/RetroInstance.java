package com.eg.android.currencyconverterfinal.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroInstance {

//    https://run.mocky.io/v3/
//    http://api.currencylayer.com/
    public static String BASE_URL = "http://api.currencylayer.com/";

    private static Retrofit retrofit;

    public static Retrofit getRetroClient() {
        if(retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

        }

        return retrofit;
    }
}
