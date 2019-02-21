package com.talview.assignment.ui.albumdetail;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.talview.assignment.R;
import com.talview.assignment.database.DBManager;
import com.talview.assignment.database.entity.AlbumsDetailsEntity;
import com.talview.assignment.database.entity.CommentEntity;
import com.talview.assignment.database.entity.PostUser;
import com.talview.assignment.network.ApiInterface;
import com.talview.assignment.utils.InternetUtil;
import com.talview.assignment.utils.schedulerProvider.BaseSchedulerProvider;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.observers.DisposableObserver;

public class AlbumDetailsRepository {

    private ApiInterface apiInterface;
    private DBManager dbManager;
    private Application context;
    private InternetUtil internetUtil;
    private BaseSchedulerProvider schedulerProvider;
    private MutableLiveData<String> errorMsg;
    private MutableLiveData<Boolean> isLoading;


    @Inject
    public AlbumDetailsRepository(ApiInterface apiInterface, DBManager dbManager, Application context, InternetUtil internetUtil, BaseSchedulerProvider schedulerProvider) {
        this.apiInterface = apiInterface;
        this.dbManager = dbManager;
        this.context = context;
        this.internetUtil = internetUtil;
        this.schedulerProvider = schedulerProvider;

        errorMsg = new MutableLiveData<>();
        isLoading = new MutableLiveData<>();
    }

    // get error data
    public MutableLiveData<String> getErrorMsg() {
        return errorMsg;
    }

    // get loading state
    public MutableLiveData<Boolean> getIsLoading() {
        return isLoading;
    }

    // get user details
    public LiveData<List<AlbumsDetailsEntity>> getAlbums(int albumId) {

        int albumDetailsCount = dbManager.getAlbumDetailsDao().getAlbumDetailsCount();

        if (albumDetailsCount == 0) {

            // check if internet is available or not
            if (!internetUtil.isNetworkAvailable()) {
                errorMsg.setValue(context.getResources().getString(R.string.internet_not_available));
            } else {
                //get albums details data from server
                getAlbumDetailsFromServerAndInsertIntoDB();
            }

        }

        return dbManager.getAlbumDetailsDao().getAlbumsDetails(albumId);
    }

    private void getAlbumDetailsFromServerAndInsertIntoDB() {

        // start loading
        isLoading.setValue(true);

        apiInterface.getAlbumsDetails().subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.mainThread()).subscribeWith(new DisposableObserver<List<AlbumsDetailsEntity>>() {
            @Override
            public void onNext(List<AlbumsDetailsEntity> value) {

                // insert albums details into room database
                dbManager.getAlbumDetailsDao().insertAlbumsDetails(value);

                // reset loading value
                isLoading.setValue(false);
            }

            @Override
            public void onError(Throwable e) {

                errorMsg.setValue(e.getMessage());

                // reset loading value
                isLoading.setValue(false);
            }

            @Override
            public void onComplete() {
            }
        });

    }
}
