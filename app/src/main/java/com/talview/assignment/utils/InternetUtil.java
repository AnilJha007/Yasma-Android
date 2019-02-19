package com.talview.assignment.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.talview.assignment.R;

public class InternetUtil {

    private static final String TAG = InternetUtil.class.getSimpleName();
    private Context _mContext;

    public InternetUtil(Context context) {
        this._mContext = context;
    }

    // check if internet is available or not
    public boolean isNetworkAvailable() {
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) _mContext
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            return networkInfo != null && networkInfo.isConnected();

        } catch (Exception e) {
            Log.e(TAG, _mContext.getResources().getString(R.string.unable_to_check), e);
        }
        return false;
    }
}
