package com.justforfun.rssreader.feature.feed;

import com.justforfun.rssreader.feature.feed.model.ChannelData;
import com.justforfun.rssreader.feature.shared.LoadingView;

/**
 * Created by Vladimir on 5/16/17.
 */

public interface IFeedableView extends LoadingView {
    void showFeed(ChannelData channel);
}
