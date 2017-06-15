package com.justforfun.rssreader.network;

import android.util.Log;

import com.justforfun.rssreader.model.ChannelEntryWithAnnotations;
import com.justforfun.simplexml.core.SimpleParser;

import java.io.InputStream;
import java.net.URL;

import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Vladimir on 5/16/17.
 */

public class RssClient {
    private static final String TAG = RssClient.class.getSimpleName();

    private static RssClient sInstance;

    public static RssClient getInstance() {
        if(sInstance == null) {
            sInstance = new RssClient();
        }
        return sInstance;
    }

    /** Use {@link RssClient#getInstance()} method */
    private RssClient() {}

    public Single<ChannelEntryWithAnnotations> fetchFeedFor(String user, String rss_url) {
        return Single.fromCallable(() -> {
            String urlString = String.format(rss_url, user);
            Log.i(TAG, "fetchFeedFor: " + urlString);
            URL url = new URL(urlString);

            InputStream inputStream = url.openConnection().getInputStream();
            return SimpleParser.Companion.parse(inputStream, ChannelEntryWithAnnotations.class);

        }).onErrorReturnItem(new ChannelEntryWithAnnotations())
          .subscribeOn(Schedulers.io());
    }
}
