package com.example.daniellachacz.weatherapp2.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.daniellachacz.weatherapp2.R;
import com.example.daniellachacz.weatherapp2.data.model.forecastWeather.List;
import com.example.daniellachacz.weatherapp2.databinding.RecyclerViewItemBinding;

import java.text.DecimalFormat;
import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    private LayoutInflater layoutInflater;
    private ArrayList<com.example.daniellachacz.weatherapp2.data.model.forecastWeather.List> mList;
    private Context mContext;

    public RecyclerViewAdapter(Context context, ArrayList<com.example.daniellachacz.weatherapp2.data.model.forecastWeather.List> weather) {
        this.layoutInflater = LayoutInflater.from(context);
        this.mList = weather;
        this.mContext = context;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final RecyclerViewItemBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.recycler_view_item, parent, false);
        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.MyViewHolder holder, int position) {
        com.example.daniellachacz.weatherapp2.data.model.forecastWeather.List list = mList.get(position);
        holder.bind(list);
        DecimalFormat df = new DecimalFormat("#0.0");
        holder.binding.tempMinItem.setText(String.valueOf(df.format(list.getMain().getTempMin()) + " ℃"));
        DecimalFormat df2 = new DecimalFormat("#0.0");
        holder.binding.tempMaxItem.setText(String.valueOf(df2.format(list.getMain().getTempMax()) + " ℃"));
        holder.binding.dateItem.setText(list.getDtTxt());
        holder.binding.descriptionItem.setText(list.getDescription());
        Glide.with(mContext).load(list.getIconUrl()).into(holder.binding.imageItem);

    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    public void setForecast(ArrayList<com.example.daniellachacz.weatherapp2.data.model.forecastWeather.List> list) {
        this.mList = list;
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private final RecyclerViewItemBinding binding;


        public MyViewHolder(@NonNull RecyclerViewItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(List item) {
            binding.executePendingBindings();
        }
    }
}
