package com.justforfun.rssreader.feature.feed;

import com.justforfun.rssreader.feature.feed.model.ChannelData;
import com.justforfun.rssreader.network.LiveJournalRepository;

import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Created by Vladimir on 5/16/17.
 */

public class ChannelPresenter {

    private IChannelableView view;

    private LiveJournalRepository liveJournalRepository;

    public ChannelPresenter(IChannelableView view) {
        this.view = view;
        this.liveJournalRepository = new LiveJournalRepository();
    }

    public void loadChannelFor(String user) {
        view.showLoading();
        liveJournalRepository.fetchFeed(user)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError(e -> showError(e))
                .subscribe(channel -> onChannelLoaded(channel));
    }

    private void showError(Throwable e) {
        view.hideLoading();
        view.showError(e.getMessage());
    }

    private void onChannelLoaded(ChannelData channel) {
        view.hideLoading();
        view.updateToolbar(channel);
        view.showFeed(channel);
    }
}
