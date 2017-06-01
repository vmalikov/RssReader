package com.justforfun.rssreader.feature.feed;

import com.justforfun.rssreader.feature.feed.model.ChannelData;
import com.justforfun.rssreader.feature.shared.ILoadingView;

/**
 * Created by Vladimir on 5/16/17.
 */

public interface IChannelableView extends IEmptyStatable {
    void showFeed(ChannelData channel);
    void updateToolbar(ChannelData channel);
}
