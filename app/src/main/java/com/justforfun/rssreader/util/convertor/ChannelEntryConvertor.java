package com.justforfun.rssreader.util.convertor;

import android.support.annotation.NonNull;

import com.justforfun.rssreader.feature.feed.model.ChannelData;
import com.justforfun.rssreader.feature.feed.model.FeedItem;
import com.justforfun.rssreader.model.ChannelEntry;
import com.justforfun.rssreader.model.FeedEntry;

import java.util.ArrayList;

import io.reactivex.Single;

/**
 * Created by Vladimir on 6/15/17.
 */

public class ChannelEntryConvertor extends EntityConvertor<ChannelEntry> {

    @NonNull
    public Single<ChannelData> convertToViewData(ChannelEntry channelEntry) {
        if(channelEntry.shouldBeEmpty()) return Single.just(ChannelData.empty);

        ChannelData channelData = new ChannelData();
        channelData.title = channelEntry.title();
        channelData.description = channelEntry.description();
        channelData.link = channelEntry.link();
        channelData.image = convertFeedEntryImage(channelEntry);
        channelData.items = convertFeedEntryItems(channelEntry);

        return Single.just(channelData);
    }

    @NonNull private ArrayList<FeedItem> convertFeedEntryItems(ChannelEntry channelEntry) {
        ArrayList<FeedItem> feedItems = new ArrayList<>();
        for(FeedEntry entry : channelEntry.items()) {
            feedItems.add(convertFeedEntry(entry));
        }
        return feedItems;
    }

    @NonNull private ChannelData.Image convertFeedEntryImage(ChannelEntry channelEntry) {
        ChannelData.Image image = new ChannelData.Image();
        image.url = channelEntry.image().url();
        return image;
    }

    @NonNull private FeedItem convertFeedEntry(FeedEntry entry) {
        FeedItem item = new FeedItem();

        item.title = entry.title();
        item.description = entry.description();
        item.pubDate = entry.pubDate();
        item.link = entry.link();

        return item;
    }
}
