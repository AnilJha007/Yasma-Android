package com.talview.assignment.di;

import android.app.Application;

import com.talview.assignment.network.ApiInterface;
import com.talview.assignment.utils.InternetUtil;
import com.talview.assignment.utils.schedulerProvider.BaseSchedulerProvider;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, NetModule.class})
public interface ApplicationComponent {

    Application getApplication();

    ApiInterface getApiInterface();

    InternetUtil getInternetUtil();

    BaseSchedulerProvider getSchedulerProvider();

}
