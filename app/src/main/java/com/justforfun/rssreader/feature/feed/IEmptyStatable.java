package com.justforfun.rssreader.feature.feed;

import com.justforfun.rssreader.feature.shared.ILoadingView;

/**
 * Created by Vladimir on 6/1/17.
 */

public interface IEmptyStatable extends ILoadingView {
    void showEmptyState();
    void hideEmptyState();
}
