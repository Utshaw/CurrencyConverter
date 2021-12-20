package com.eg.android.currencyconverterfinal.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Map;

public class ConversionJSONResponse {

    @SerializedName("quotes")
    @Expose
    private Map<String, Float> result;

    public Map<String, Float> getResult() {
        return result;
    }

    public void setResult(Map<String, Float> result) {
        this.result = result;
    }
}
