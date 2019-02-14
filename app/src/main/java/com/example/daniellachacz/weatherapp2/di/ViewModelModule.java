package com.example.daniellachacz.weatherapp2.di;

import com.example.daniellachacz.weatherapp2.viewmodel.HourlyViewModel;
import com.example.daniellachacz.weatherapp2.viewmodel.TodayViewModel;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(TodayViewModel.class)
    abstract ViewModel bindTodayViewModel(TodayViewModel todayViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(HourlyViewModel.class)
    abstract ViewModel bindHourlyViewModel(HourlyViewModel hourlyViewModel);

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelFactory viewModelFactory);
}
