package com.justforfun.rssreader.feature.shared.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.justforfun.rssreader.feature.shared.IRouter;

import java.lang.ref.WeakReference;

/**
 * Created by Vladimir on 5/25/17.
 */

public class SharedViewModel extends ViewModel {
    private MutableLiveData<String> usernameData = new MutableLiveData<>();
    private MutableLiveData<IRouter> mRouterData = new MutableLiveData<>();

    public LiveData<String> getUsername() {
        return usernameData;
    }

    public void setUsername(String value) {
        this.usernameData.setValue(value);
    }

    public LiveData<IRouter> getRouterData() {
        return mRouterData;
    }

    public void setRouterData(IRouter router) {
        this.mRouterData.setValue(router);
    }
}
