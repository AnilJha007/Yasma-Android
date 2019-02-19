package com.talview.assignment.di;

import android.app.Application;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.talview.assignment.network.ApiInterface;
import com.talview.assignment.utils.InternetUtil;
import com.talview.assignment.utils.schedulerProvider.BaseSchedulerProvider;
import com.talview.assignment.utils.schedulerProvider.SchedulerProvider;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetModule {

    private String baseUrl;

    public NetModule(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    @Provides
    @Singleton
    public InternetUtil provideInternetUtil(Application context) {
        return new InternetUtil(context);
    }

    @Provides
    @Singleton
    public OkHttpClient provideOkHttpClient() {
        OkHttpClient.Builder client = new OkHttpClient.Builder();
        return client.build();
    }

    @Singleton
    @Provides
    public GsonConverterFactory provideConverter() {
        return GsonConverterFactory.create();
    }

    @Singleton
    @Provides
    public RxJava2CallAdapterFactory provideClient() {
        return RxJava2CallAdapterFactory.create();
    }

    @Singleton
    @Provides
    public Retrofit providerRetroFit(GsonConverterFactory converter, RxJava2CallAdapterFactory rxAdapter, OkHttpClient client) {
        return new Retrofit.Builder().
                baseUrl(baseUrl).
                client(client).
                addConverterFactory(converter).
                addCallAdapterFactory(rxAdapter).
                build();
    }

    @Singleton
    @Provides
    public ApiInterface provideApiService(Retrofit retrofit) {
        return retrofit.create(ApiInterface.class);
    }

    @Singleton
    @Provides
    public BaseSchedulerProvider provideSchedulerProvider() {
        return new SchedulerProvider();
    }

}
