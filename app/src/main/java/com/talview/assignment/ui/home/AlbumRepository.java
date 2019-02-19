package com.talview.assignment.ui.home;

import android.app.Application;

import com.talview.assignment.database.DBManager;
import com.talview.assignment.network.ApiInterface;
import com.talview.assignment.utils.InternetUtil;
import com.talview.assignment.utils.schedulerProvider.BaseSchedulerProvider;

import javax.inject.Inject;

public class AlbumRepository {

    private ApiInterface apiInterface;
    private DBManager dbManager;
    private Application context;
    private InternetUtil internetUtil;
    private BaseSchedulerProvider schedulerProvider;

    @Inject
    public AlbumRepository(ApiInterface apiInterface, DBManager dbManager, Application context, InternetUtil internetUtil, BaseSchedulerProvider schedulerProvider) {
        this.apiInterface = apiInterface;
        this.dbManager = dbManager;
        this.context = context;
        this.internetUtil = internetUtil;
        this.schedulerProvider = schedulerProvider;
    }


}
