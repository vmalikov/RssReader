package com.justforfun.rssreader.network.repository;

import com.justforfun.rssreader.feature.feed.model.ChannelData;
import com.justforfun.rssreader.network.RssClient;

import io.reactivex.Single;

/**
 * Created by Vladimir on 6/1/17.
 */

public class MediumRepository extends AbstractRepository {
    @Override
    public String getRssUrl() {
        return "https://medium.com/feed/@%s"; // Medium
    }
}
