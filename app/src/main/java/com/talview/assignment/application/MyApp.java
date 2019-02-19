package com.talview.assignment.application;

import android.app.Application;

import com.talview.assignment.BuildConfig;
import com.talview.assignment.di.AppModule;
import com.talview.assignment.di.ApplicationComponent;
import com.talview.assignment.di.DaggerApplicationComponent;
import com.talview.assignment.di.DbModule;
import com.talview.assignment.di.NetModule;

public class MyApp extends Application {

    private static ApplicationComponent component;

    @Override
    public void onCreate() {
        super.onCreate();

        component = DaggerApplicationComponent.builder().appModule(new AppModule(this)).
                netModule(new NetModule(BuildConfig.BASE_URL)).
                dbModule(new DbModule()).
                build();
    }

    public static ApplicationComponent getApplicationComponent() {
        return component;
    }
}
