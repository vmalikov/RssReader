package com.justforfun.rssreader.network;

import com.justforfun.rssreader.feature.feed.model.ChannelData;
import com.justforfun.rssreader.feature.feed.model.FeedItem;
import com.justforfun.rssreader.model.ChannelEntry;
import com.justforfun.rssreader.model.FeedEntry;

import java.util.ArrayList;

import io.reactivex.Single;


/**
 * Created by Vladimir on 5/16/17.
 */

public class LiveJournalRepository {

    public Single<ChannelData> fetchFeed(String user) {
        return RssClient.getInstance().fetchFeedFor(user)
                .flatMap(channelEntry -> convertToViewData(channelEntry));
    }

    private Single<ChannelData> convertToViewData(ChannelEntry channelEntry) {
        ChannelData channelData = new ChannelData();
        channelData.title = channelEntry.title();
        channelData.description = channelEntry.description();
        channelData.link = channelEntry.link();
        ArrayList<FeedItem> feedItems = new ArrayList<>();
        for(FeedEntry entry : channelEntry.items()) {
            feedItems.add(convertFeedEntry(entry));
        }
        channelData.items = feedItems;
        return Single.just(channelData);
    }

    private FeedItem convertFeedEntry(FeedEntry entry) {
        FeedItem item = new FeedItem();

        item.title = entry.title();
        item.description = entry.description();
        item.pubDate = entry.pubDate();
        item.link = entry.link();

        return item;
    }
}
