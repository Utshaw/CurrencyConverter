package com.eg.android.currencyconverterfinal.model;

public class ConversionModel {

    private String currencyName;
    private Float conversionRate;

    @Override
    public String toString() {
        return this.currencyName;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

    public Float getConversionRate() {
        return conversionRate;
    }

    public void setConversionRate(Float conversionRate) {
        this.conversionRate = conversionRate;
    }

    public ConversionModel(String currencyName, Float conversionRate) {
        this.currencyName = currencyName;
        this.conversionRate = conversionRate;
    }

//    private String currencyName;
//    private double conversionRate;
//
//    public String getCurrencyName() {
//        return currencyName;
//    }
//
//    public void setCurrencyName(String currencyName) {
//        this.currencyName = currencyName;
//    }
//
//    public double getConversionRate() {
//        return conversionRate;
//    }
//
//    public void setConversionRate(double conversionRate) {
//        this.conversionRate = conversionRate;
//    }
//
//    public CurrencyModel(String currencyName, double conversionRate) {
//        this.currencyName = currencyName;
//        this.conversionRate = conversionRate;
//    }
}


