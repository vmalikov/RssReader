package com.justforfun.rssreader.feature.feed;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.justforfun.rssreader.feature.feed.model.ChannelData;
import com.justforfun.rssreader.network.repository.AbstractRepository;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Vladimir on 6/1/17.
 */

public class FeedViewModel extends ViewModel {

    private AbstractRepository repository;

    private MutableLiveData<ChannelData> channelData = new MutableLiveData<>();

    Single<ChannelData> loadChannelFor(String user) {
        return repository.fetchFeed(user)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())

                .doOnError(Throwable::printStackTrace)
                .doOnSuccess(this::setChannelData);
    }

    LiveData<ChannelData> getChannelData() {
        return channelData;
    }

    private void setChannelData(ChannelData value) {
        this.channelData.setValue(value);
    }

    void setRepository(Class repositoryClass) {
        try {
            this.repository = (AbstractRepository) repositoryClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
