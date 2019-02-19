package com.talview.assignment.ui.home;

import android.app.Application;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;
import android.view.View;

import com.talview.assignment.database.DBManager;
import com.talview.assignment.database.entity.PostEntity;
import com.talview.assignment.network.ApiInterface;
import com.talview.assignment.utils.InternetUtil;
import com.talview.assignment.utils.schedulerProvider.BaseSchedulerProvider;

import java.util.List;
import java.util.ListIterator;

import javax.inject.Inject;

import io.reactivex.observers.DisposableObserver;


public class PostRepository {

    private ApiInterface apiInterface;
    private DBManager dbManager;
    private Application context;
    private InternetUtil internetUtil;
    private BaseSchedulerProvider schedulerProvider;

    @Inject
    public PostRepository(ApiInterface apiInterface, DBManager dbManager, Application context, InternetUtil internetUtil, BaseSchedulerProvider schedulerProvider) {
        this.apiInterface = apiInterface;
        this.dbManager = dbManager;
        this.context = context;
        this.internetUtil = internetUtil;
        this.schedulerProvider = schedulerProvider;
    }

    public MutableLiveData<List<PostEntity>> getUserPosts() {

        final MutableLiveData<List<PostEntity>> poListMutableLiveData = new MutableLiveData<>();

        //get data from server
        apiInterface.getPosts().subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.mainThread()).subscribeWith(new DisposableObserver<List<PostEntity>>() {
            @Override
            public void onNext(List<PostEntity> value) {
                poListMutableLiveData.setValue(value);
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onComplete() {

            }
        });

        return poListMutableLiveData;
    }
}
