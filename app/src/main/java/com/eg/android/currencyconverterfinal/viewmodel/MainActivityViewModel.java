package com.eg.android.currencyconverterfinal.viewmodel;


import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.eg.android.currencyconverterfinal.model.ConversionJSONResponse;
import com.eg.android.currencyconverterfinal.model.ConversionModel;
import com.eg.android.currencyconverterfinal.model.CurrencyJSONResponse;
import com.eg.android.currencyconverterfinal.model.CurrencyModel;
import com.eg.android.currencyconverterfinal.network.APIService;
import com.eg.android.currencyconverterfinal.network.RetroInstance;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivityViewModel extends ViewModel {

    static {
        System.loadLibrary("currencyconverterfinal");
    }

    public native String stringFromJNI();
    public native String stringFromJNI2();

    private MutableLiveData<List<ConversionModel>> conversionList;
    private MutableLiveData<List<CurrencyModel>> currencyList;
    APIService apiService;


    public MainActivityViewModel() {
        apiService = RetroInstance.getRetroClient().create(APIService.class);
        conversionList = new MutableLiveData<>();
        currencyList= new MutableLiveData<>();
    }


    public MutableLiveData<List<ConversionModel>> getConversionObserver() {
        return conversionList;
    }

    public MutableLiveData<List<CurrencyModel>> getCurrencyObserver() {
        return currencyList;
    }


    public void makeGetConversionApiCall(String source) {
        source = "USD";

        Call<ConversionJSONResponse> call = apiService.getConversionList(
                stringFromJNI(),
                source
        );
        call.enqueue(new Callback<ConversionJSONResponse>() {
            @Override
            public void onResponse(Call<ConversionJSONResponse> call, Response<ConversionJSONResponse> response) {
                Log.d("20DEC2021", "onResponse: " + response);
                Map<String, Float> conversionMap = response.body().getResult();
                if(conversionMap != null) {
                    List<ConversionModel> list = new ArrayList<>();
                    for (Map.Entry<String, Float> entry : conversionMap.entrySet()) {
                        list.add(new ConversionModel(entry.getKey(), entry.getValue()));
                    }

                    conversionList.postValue(list);
                }else {

                }
            }

            @Override
            public void onFailure(Call<ConversionJSONResponse> call, Throwable t) {
                conversionList.postValue(null);
            }
        });
    }


    public void makeCurrencyApiCall() {
        Call<CurrencyJSONResponse> call = apiService.getCurrencyList(
                stringFromJNI2()
        );
        call.enqueue(new Callback<CurrencyJSONResponse>() {
            @Override
            public void onResponse(Call<CurrencyJSONResponse> call, Response<CurrencyJSONResponse> response) {

                Map<String, String> currencyMap = response.body().getResult();
                List<CurrencyModel> list = new ArrayList<>();
                for (Map.Entry<String, String> entry : currencyMap.entrySet()) {
                    list.add(new CurrencyModel(entry.getKey(), entry.getValue()));
                }

                currencyList.postValue(list);
            }

            @Override
            public void onFailure(Call<CurrencyJSONResponse> call, Throwable t) {
                currencyList.postValue(null);
            }
        });
    }

}
