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

    public Single<ChannelData> loadChannelFor(String user) {
        return repository.fetchFeed(user)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())

                .doOnError(e -> e.printStackTrace())
                .doOnSuccess(channel -> setChannelData(channel));
    }

    public LiveData<ChannelData> getChannelData() {
        return channelData;
    }

    public void setChannelData(ChannelData value) {
        this.channelData.setValue(value);
    }

    public void setRepository(Class repositoryClass) {
        try {
            this.repository = (AbstractRepository) repositoryClass.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
