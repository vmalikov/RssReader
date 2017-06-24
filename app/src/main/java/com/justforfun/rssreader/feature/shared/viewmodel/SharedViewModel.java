package com.justforfun.rssreader.feature.shared.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.justforfun.rssreader.feature.search_feed.model.ResourceRepository;
import com.justforfun.rssreader.feature.search_feed.model.UsernameItem;

/**
 * Created by Vladimir on 5/25/17.
 */

public class SharedViewModel extends ViewModel {
    private MutableLiveData<UsernameItem> usernameData = new MutableLiveData<>();
    private MutableLiveData<ResourceRepository> repository = new MutableLiveData<>();

    public LiveData<UsernameItem> getUsername() {
        return usernameData;
    }

    public void setUsername(UsernameItem value) {
        this.usernameData.setValue(value);
    }

    public LiveData<ResourceRepository> getRepository() {
        return repository;
    }

    public void setRepository(ResourceRepository value) {
        this.repository.setValue(value);
    }
}
