package com.eg.android.currencyconverterfinal;

import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.eg.android.currencyconverterfinal.adapter.CurrencyListAdapter;
import com.eg.android.currencyconverterfinal.databinding.ActivityMainBinding;
import com.eg.android.currencyconverterfinal.model.ConversionModel;
import com.eg.android.currencyconverterfinal.model.CurrencyModel;
import com.eg.android.currencyconverterfinal.viewmodel.MainActivityViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private CurrencyListAdapter adapter;
    private MainActivityViewModel conversionViewModel;
    private Spinner currencySpinner;
    private EditText etAmount;
    private String selectedCurrency = "USD";

    ActivityMainBinding activityMainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = activityMainBinding.getRoot();
        setContentView(view);

        currencySpinner = activityMainBinding.sCurrencyList;
        etAmount  = activityMainBinding.etAmount;
        ImageButton ibtnRefresh = activityMainBinding.ibtnRefresh;



        RecyclerView recyclerView = activityMainBinding.rvCurrencyList;
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        conversionViewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);

        adapter = new CurrencyListAdapter(this, conversionViewModel.getConversionObserver().getValue());

        recyclerView.setAdapter(adapter);

        conversionViewModel.getConversionObserver().observe(this, new Observer<List<ConversionModel>>() {
            @Override
            public void onChanged(List<ConversionModel> conversionModels) {
                if(conversionModels != null) {
                    adapter.setCurrencyInfo(conversionModels, etAmount.getText().toString(), selectedCurrency);



                }else {

                }
            }
        });
        conversionViewModel.makeGetConversionApiCall(selectedCurrency);
        conversionViewModel.makeCurrencyApiCall();

        conversionViewModel.getCurrencyObserver().observe(this, new Observer<List<CurrencyModel>>() {
            @Override
            public void onChanged(List<CurrencyModel> conversionModels) {
                if(conversionModels != null) {
                    ArrayAdapter<CurrencyModel> adapter = new ArrayAdapter<CurrencyModel>(getApplicationContext(), android.R.layout.simple_spinner_item, conversionModels);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    currencySpinner.setAdapter(adapter);

                }else {

                }
            }
        });


        etAmount.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {}

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                if(s.length() > 0) {

                    adapter.setCurrencyInfo("" + s);
                }else {
                    adapter.setCurrencyInfo("0");
                }
            }
        });

        ibtnRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                conversionViewModel.makeGetConversionApiCall(selectedCurrency);
            }
        });

        boolean isDarkThemeOn = (getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK)  == Configuration.UI_MODE_NIGHT_YES;


        currencySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(isDarkThemeOn) {

                    ((TextView) view).setTextColor(Color.WHITE); //Change selected text color
                }else {
                    ((TextView) view).setTextColor(Color.BLACK); //Change selected text color
                }


                selectedCurrency = ((CurrencyModel )(adapterView.getItemAtPosition(i))).getCurrencyShortName();

                final Handler handler = new Handler(Looper.getMainLooper());
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
//                        conversionViewModel.makeApiCall(selectedCurrency);
                    }
                }, 5000);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }
}