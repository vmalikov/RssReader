package com.justforfun.rssreader.network.repository;

import com.justforfun.rssreader.feature.feed.model.ChannelData;
import com.justforfun.rssreader.network.RssClient;

import io.reactivex.Single;

/**
 * Created by Vladimir on 6/1/17.
 */

public class MediumRepository extends AbstractRepository {
    private static final String rss_url = "https://medium.com/feed/@%s"; // Medium

    @Override
    public Single<ChannelData> fetchFeed(String user) {
        return RssClient.getInstance().fetchFeedFor(user, rss_url)
                .flatMap(channelEntry -> convertToViewData(channelEntry));
    }
}
