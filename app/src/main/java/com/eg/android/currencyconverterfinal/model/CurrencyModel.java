package com.eg.android.currencyconverterfinal.model;

public class CurrencyModel {
    private String currencyShortName, currencyFullName;

    public CurrencyModel(String currencyShortName, String currencyFullName) {
        this.currencyShortName = currencyShortName;
        this.currencyFullName = currencyFullName;
    }

    @Override
    public String toString() {
        return this.currencyShortName + "(" + currencyFullName + ")";
    }

    public String getCurrencyShortName() {
        return currencyShortName;
    }

    public void setCurrencyShortName(String currencyShortName) {
        this.currencyShortName = currencyShortName;
    }

    public String getCurrencyFullName() {
        return currencyFullName;
    }

    public void setCurrencyFullName(String currencyFullName) {
        this.currencyFullName = currencyFullName;
    }
}
