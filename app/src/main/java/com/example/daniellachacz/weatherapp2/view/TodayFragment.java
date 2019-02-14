package com.example.daniellachacz.weatherapp2.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.daniellachacz.weatherapp2.R;
import com.example.daniellachacz.weatherapp2.data.model.currentWeather.Weather;
import com.example.daniellachacz.weatherapp2.databinding.FragmentTodayBinding;
import com.example.daniellachacz.weatherapp2.viewmodel.TodayViewModel;
import com.example.daniellachacz.weatherapp2.di.ViewModelFactory;

import org.greenrobot.eventbus.EventBus;

import java.text.DecimalFormat;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import dagger.android.support.AndroidSupportInjection;

public class TodayFragment extends Fragment {

    private String location;

    private TodayViewModel todayViewModel;

    @Inject
    ViewModelFactory viewModelFactory;

    @SuppressLint("SetTextI18n")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentTodayBinding fragmentTodayBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_today, container, false);
        View view = fragmentTodayBinding.getRoot();
        todayViewModel = ViewModelProviders.of(this, viewModelFactory).get(TodayViewModel.class);
        fragmentTodayBinding.setTodayViewModel(todayViewModel);

        fragmentTodayBinding.setTodayFragmentInterface(new TodayFragmentInterface() {
            @Override
            public void onSearchClick(View v) {
                if (fragmentTodayBinding.locationEditText.getText().toString().isEmpty()) {
                    Toast.makeText(getContext(), "Location field is empty!", Toast.LENGTH_SHORT).show();
                } else
                todayViewModel.getCity();
            }
        });

        todayViewModel.getWeather().observe(getViewLifecycleOwner(), new Observer<Weather>() {
            @Override
            public void onChanged(Weather weather) {
                fragmentTodayBinding.nameTextView.setText(weather.getName());
                String desc = weather.getDescription();
                String desc2 = Character.toString(desc.charAt(0)).toUpperCase()+desc.substring(1);
                fragmentTodayBinding.conditionTextView.setText(desc2);
                Double temp = weather.getMain().getTemp();
                DecimalFormat df = new DecimalFormat("#0.0");
                fragmentTodayBinding.tempTextView.setText(df.format(temp) + " ℃");
                Glide.with(TodayFragment.this).load(weather.getIconUrl()).into(fragmentTodayBinding.iconImageView);
                fragmentTodayBinding.windTextView.setText("Wind: " + String.valueOf(weather.getWind().getSpeed()) + " km/h");
                fragmentTodayBinding.humidityTextView.setText("Humidity: " + weather.getMain().getHumidity() + " %");
                fragmentTodayBinding.pressureTextView.setText("Pressure: " + weather.getMain().getPressure() + " mBar");

                location = fragmentTodayBinding.nameTextView.getText().toString();
            }
        });

        return view;
    }

    private void sendLocation(String location) {
        EventBus.getDefault().postSticky(new BusEvent(location));
    }

    @Override
    public void onPause() {
        super.onPause();
        sendLocation(location);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        AndroidSupportInjection.inject(this);
        super.onAttach(context);
    }
}
