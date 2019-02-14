package com.example.daniellachacz.weatherapp2.view;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.daniellachacz.weatherapp2.R;
import com.example.daniellachacz.weatherapp2.adapter.RecyclerViewAdapter;
import com.example.daniellachacz.weatherapp2.data.model.forecastWeather.List;
import com.example.daniellachacz.weatherapp2.databinding.FragmentHourlyBinding;
import com.example.daniellachacz.weatherapp2.di.ViewModelFactory;
import com.example.daniellachacz.weatherapp2.viewmodel.HourlyViewModel;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import dagger.android.support.AndroidSupportInjection;

public class HourlyFragment extends Fragment {

    private String location;

    @Inject
    ViewModelFactory viewModelFactory;

    private HourlyViewModel hourlyViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentHourlyBinding fragmentHourlyBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_hourly, container, false);
        fragmentHourlyBinding.setLifecycleOwner(this);
        View view = fragmentHourlyBinding.getRoot();
        hourlyViewModel = ViewModelProviders.of(this, viewModelFactory).get(HourlyViewModel.class);
        fragmentHourlyBinding.setHourlyViewModel(hourlyViewModel);

        hourlyViewModel.location.set(location);

        fragmentHourlyBinding.locationTextView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                hourlyViewModel.getCity();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        ArrayList<List> list = new ArrayList<>();

        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        final RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(getActivity(), list);
        recyclerView.setAdapter(recyclerViewAdapter);

        hourlyViewModel.getForecast().observe(getViewLifecycleOwner(), recyclerViewAdapter::setForecast);

        return view;
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onBusEvent(BusEvent event) {
        location = event.location;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        AndroidSupportInjection.inject(this);
        EventBus.getDefault().register(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
