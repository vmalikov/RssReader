package com.justforfun.rssreader.feature.feed;

import com.justforfun.rssreader.feature.feed.model.ChannelData;
import com.justforfun.rssreader.network.LiveJournalRepository;

import java.util.concurrent.TimeUnit;

import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Created by Vladimir on 5/16/17.
 */

public class FeedPresenter {

    private IFeedableView view;

    private LiveJournalRepository liveJournalRepository;

    public FeedPresenter(IFeedableView view) {
        this.view = view;
        this.liveJournalRepository = new LiveJournalRepository();
    }

    public void loadFeedFor(String user) {
        view.showLoading();
        liveJournalRepository.fetchFeed(user)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError(e -> showError(e))
                .subscribe(channel -> showFeed(channel));
    }

    private void showError(Throwable e) {
        view.hideLoading();
        view.showError(e.getMessage());
    }

    private void showFeed(ChannelData channel) {
        view.hideLoading();
        view.showFeed(channel);
    }
}
