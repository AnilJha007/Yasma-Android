package com.talview.assignment.di;

import android.app.Application;

import com.talview.assignment.database.DBManager;
import com.talview.assignment.network.ApiInterface;
import com.talview.assignment.utils.InternetUtil;
import com.talview.assignment.utils.schedulerProvider.BaseSchedulerProvider;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, NetModule.class, DbModule.class})
public interface ApplicationComponent {

    Application getApplication();

    ApiInterface getApiInterface();

    InternetUtil getInternetUtil();

    DBManager getDBManager();

    BaseSchedulerProvider getSchedulerProvider();

}
