package com.eg.android.currencyconverterfinal.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.eg.android.currencyconverterfinal.R;
import com.eg.android.currencyconverterfinal.model.ConversionModel;

import java.util.List;

public class CurrencyListAdapter extends RecyclerView.Adapter<CurrencyListAdapter.MyViewHolder>{

    private Context context;
    private List<ConversionModel> currencyList;
    private float amount;
    private String sourceCurrency;

    public CurrencyListAdapter(Context context, List<ConversionModel> currencyList) {
        this.context = context;
        this.currencyList = currencyList;
        this.amount = 1;
    }

    public void setCurrencyInfo(List<ConversionModel> currencyList, String amount, String sourceCurrency) {
        this.currencyList = currencyList;
        this.sourceCurrency = sourceCurrency;
        if(TextUtils.isEmpty(amount)) {
            this.amount = 0.0f;
        }else {
            this.amount = Float.parseFloat(amount);
        }
        notifyDataSetChanged();
    }

    public void setCurrencyInfo( String amount) {
        try{
            if(TextUtils.isEmpty(amount)) {
                this.amount = 0.0f;
            }else {
                this.amount = Float.parseFloat(amount);
            }
            notifyDataSetChanged();
        }catch (NumberFormatException e) {
            Toast.makeText(context, "Invalid input", Toast.LENGTH_SHORT).show();
        }
    }



    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.recycler_row, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.tvCurrencyName.setText(this.currencyList.get(position).getCurrencyName().toString().replaceFirst(sourceCurrency, ""));
        holder.tvConverted.setText(String.valueOf(this.currencyList.get(position).getConversionRate() * this.amount));
//        holder.tvConverted.setText(String.valueOf(this.currencyList.get(position).getConversionRate()));

    }

    @Override
    public int getItemCount() {
        if(this.currencyList != null) {
            return this.currencyList.size();
        }
        return 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvCurrencyName, tvConverted;


        public MyViewHolder(View itemView) {
            super(itemView);
            tvCurrencyName = itemView.findViewById(R.id.tvCurrencyName);
            tvConverted = itemView.findViewById(R.id.tvConverted);

        }
    }
}
