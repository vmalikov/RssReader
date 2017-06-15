package com.justforfun.rssreader.network.repository;

import com.justforfun.rssreader.feature.feed.model.ChannelData;
import com.justforfun.rssreader.network.RssClient;
import com.justforfun.rssreader.util.convertor.EntityConvertor;

import io.reactivex.Single;

/**
 * Created by Vladimir on 6/1/17.
 */

public abstract class AbstractRepository {

    public abstract String getRssUrl();

    public Single<ChannelData> fetchFeed(String user) {
        return RssClient.getInstance().fetchFeedFor(user, getRssUrl())
                .flatMap(EntityConvertor::convertData);
    }
}
