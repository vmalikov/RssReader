package com.justforfun.rssreader.network.repository;

import android.support.annotation.NonNull;

import com.justforfun.rssreader.feature.feed.model.ChannelData;
import com.justforfun.rssreader.feature.feed.model.FeedItem;
import com.justforfun.rssreader.model.ChannelEntry;
import com.justforfun.rssreader.model.FeedEntry;
import com.justforfun.rssreader.network.RssClient;
import com.justforfun.rssreader.network.repository.AbstractRepository;

import java.util.ArrayList;

import io.reactivex.Single;


/**
 * Created by Vladimir on 5/16/17.
 */

public class LiveJournalRepository extends AbstractRepository {
    @Override
    public String getRssUrl() {
        return "http://%s.livejournal.com/data/rss"; // zmey-gadukin;
    }
}
