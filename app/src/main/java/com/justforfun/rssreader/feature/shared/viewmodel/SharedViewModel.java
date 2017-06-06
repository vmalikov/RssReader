package com.justforfun.rssreader.feature.shared.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.justforfun.rssreader.feature.feed.FeedViewModel;
import com.justforfun.rssreader.feature.feed.model.FeedItem;
import com.justforfun.rssreader.feature.shared.IRouter;

import java.lang.ref.WeakReference;

/**
 * Created by Vladimir on 5/25/17.
 */

public class SharedViewModel extends ViewModel {
    private MutableLiveData<String> usernameData = new MutableLiveData<>();
    private MutableLiveData<Class> repositoryClass = new MutableLiveData<>();

    public LiveData<String> getUsername() {
        return usernameData;
    }

    public void setUsername(String value) {
        this.usernameData.setValue(value);
    }

    public LiveData<Class> getRepositoryClass() {
        return repositoryClass;
    }

    public void setRepositoryClass(Class value) {
        this.repositoryClass.setValue(value);
    }
}
