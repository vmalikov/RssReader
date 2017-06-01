package com.justforfun.rssreader.feature.feed;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.Nullable;

import com.justforfun.rssreader.feature.feed.model.ChannelData;
import com.justforfun.rssreader.network.LiveJournalRepository;

import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Created by Vladimir on 6/1/17.
 */

public class FeedViewModel<T extends IChannelableView> extends ViewModel {

    @Nullable
    private T view;

    private LiveJournalRepository liveJournalRepository;

    private MutableLiveData<ChannelData> channelData = new MutableLiveData<>();

    public FeedViewModel() {
        liveJournalRepository = new LiveJournalRepository();
    }

    public void setView(T view) {
        this.view = view;
    }

    public void loadChannelFor(String user) {
        view.showLoading();
        liveJournalRepository.fetchFeed(user)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError(e -> showError(e))
                .subscribe(channel -> onChannelReceived(channel));
    }

    private void onChannelReceived(ChannelData channel) {
        setChannelData(channel);
        view.hideLoading();
        view.updateToolbar(channel);
    }

    private void showError(Throwable e) {
        view.hideLoading();
        view.showError(e.getMessage());
    }

    public LiveData<ChannelData> getChannelData() {
        return channelData;
    }

    public void setChannelData(ChannelData value) {
        this.channelData.setValue(value);
    }
}
