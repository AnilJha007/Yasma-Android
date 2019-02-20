package com.talview.assignment.ui.home;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.talview.assignment.R;
import com.talview.assignment.database.DBManager;
import com.talview.assignment.database.entity.AlbumEntity;
import com.talview.assignment.database.entity.AlbumUser;
import com.talview.assignment.database.entity.PostEntity;
import com.talview.assignment.database.entity.PostUser;
import com.talview.assignment.network.ApiInterface;
import com.talview.assignment.utils.InternetUtil;
import com.talview.assignment.utils.schedulerProvider.BaseSchedulerProvider;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.observers.DisposableObserver;

public class AlbumRepository {

    private ApiInterface apiInterface;
    private DBManager dbManager;
    private Application context;
    private InternetUtil internetUtil;
    private BaseSchedulerProvider schedulerProvider;
    private MutableLiveData<String> errorMsg;


    @Inject
    public AlbumRepository(ApiInterface apiInterface, DBManager dbManager, Application context, InternetUtil internetUtil, BaseSchedulerProvider schedulerProvider) {
        this.apiInterface = apiInterface;
        this.dbManager = dbManager;
        this.context = context;
        this.internetUtil = internetUtil;
        this.schedulerProvider = schedulerProvider;

        errorMsg = new MutableLiveData<>();
    }

    // get error data
    public MutableLiveData<String> getErrorMsg() {
        return errorMsg;
    }

    // get user albums
    public LiveData<List<AlbumUser>> getUserAlbums() {

        int rowCount = dbManager.getAlbumDao().getRowsCount();

        if (rowCount == 0) {

            // check if internet is available or not
            if (!internetUtil.isNetworkAvailable()) {
                errorMsg.setValue(context.getResources().getString(R.string.internet_not_available));
            } else {
                //get albums data from server
                getAlbumsDataFromServerAndInsertIntoDB();
            }

        }

        return dbManager.getAlbumDao().getAllAlbumAndUser();
    }

    private void getAlbumsDataFromServerAndInsertIntoDB() {

        apiInterface.getAlbums().subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.mainThread()).subscribeWith(new DisposableObserver<List<AlbumEntity>>() {
            @Override
            public void onNext(List<AlbumEntity> value) {

                // insert albums into room database
                dbManager.getAlbumDao().insertAlbums(value);

            }

            @Override
            public void onError(Throwable e) {
                errorMsg.setValue(e.getMessage());
            }

            @Override
            public void onComplete() {

            }
        });

    }


}
