package com.talview.assignment.ui.home;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.talview.assignment.R;
import com.talview.assignment.database.DBManager;
import com.talview.assignment.database.entity.PostEntity;
import com.talview.assignment.database.entity.PostUser;
import com.talview.assignment.database.entity.UserEntity;
import com.talview.assignment.network.ApiInterface;
import com.talview.assignment.utils.InternetUtil;
import com.talview.assignment.utils.schedulerProvider.BaseSchedulerProvider;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.observers.DisposableObserver;


public class PostRepository {

    private ApiInterface apiInterface;
    private DBManager dbManager;
    private Application context;
    private InternetUtil internetUtil;
    private BaseSchedulerProvider schedulerProvider;
    private MutableLiveData<String> errorMsg;

    @Inject
    public PostRepository(ApiInterface apiInterface, DBManager dbManager, Application context, InternetUtil internetUtil, BaseSchedulerProvider schedulerProvider) {
        this.apiInterface = apiInterface;
        this.dbManager = dbManager;
        this.context = context;
        this.internetUtil = internetUtil;
        this.schedulerProvider = schedulerProvider;

        errorMsg = new MutableLiveData<>();

    }

    // get error message
    public MutableLiveData<String> getError() {
        return errorMsg;
    }

    // get user posts
    public LiveData<List<PostUser>> getUserPosts() {

        // get data count
        int rowCount = dbManager.getPostDao().getRowsCount();

        // call api to pull data from server
        if (rowCount == 0) {

            // check if internet is available or not
            if (!internetUtil.isNetworkAvailable()) {
                errorMsg.setValue(context.getResources().getString(R.string.internet_not_available));
            } else {
                //get post data from server
                getPostsDataFromServerAndInsertIntoDB();
                // get user data from server
                getUsersDataFromServerAndInsertIntoDB();
            }
        }

        return dbManager.getPostDao().getAllPostAndUser();

    }

    private void getUsersDataFromServerAndInsertIntoDB() {

        apiInterface.getUsers().subscribeOn(schedulerProvider.io()).observeOn(schedulerProvider.mainThread()).subscribeWith(new DisposableObserver<List<UserEntity>>() {
            @Override
            public void onNext(List<UserEntity> value) {

                // insert user data into room database
                dbManager.getUserDao().insertUsers(value);
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

    private void getPostsDataFromServerAndInsertIntoDB() {

        apiInterface.getPosts().subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.mainThread()).subscribeWith(new DisposableObserver<List<PostEntity>>() {
            @Override
            public void onNext(List<PostEntity> value) {

                // insert posts into room database
                dbManager.getPostDao().insertPosts(value);

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
