package com.eg.android.currencyconverterfinal.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Map;

public class CurrencyJSONResponse {

    @SerializedName("currencies")
    @Expose
    private Map<String, String> result;

    public Map<String, String> getResult() {
        return result;
    }

    public void setResult(Map<String, String> result) {
        this.result = result;
    }
}
