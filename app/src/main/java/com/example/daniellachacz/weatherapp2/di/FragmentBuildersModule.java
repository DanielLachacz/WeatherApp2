package com.example.daniellachacz.weatherapp2.di;

import com.example.daniellachacz.weatherapp2.view.HourlyFragment;
import com.example.daniellachacz.weatherapp2.view.TodayFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class FragmentBuildersModule {

    @ContributesAndroidInjector
    abstract TodayFragment contributeTodayFragment();

    @ContributesAndroidInjector
    abstract HourlyFragment contributeHourlyFragment();
}
