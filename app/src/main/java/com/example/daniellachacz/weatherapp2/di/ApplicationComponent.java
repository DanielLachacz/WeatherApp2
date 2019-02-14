package com.example.daniellachacz.weatherapp2.di;

import android.app.Application;

import com.example.daniellachacz.weatherapp2.WeatherApp;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {AndroidSupportInjectionModule.class, AppModule.class, MainActivityModule.class})
public interface ApplicationComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);
        ApplicationComponent build();
    }
    void inject(WeatherApp weatherApp);

}
