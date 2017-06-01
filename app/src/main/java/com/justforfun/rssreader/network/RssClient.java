package com.justforfun.rssreader.network;

import android.util.Log;

import com.justforfun.rssreader.model.ChannelEntry;
import com.justforfun.rssreader.parser.ChannelDeserializer;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import io.reactivex.Observable;
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

    public Single<ChannelEntry> fetchFeedFor(String user, String rss_url) {
        return Single.fromCallable(() -> {
            String urlString = String.format(rss_url, user);
            Log.i(TAG, "fetchFeedFor: " + urlString);
            URL url = new URL(urlString);

            InputStream inputStream = url.openConnection().getInputStream();
            return ChannelDeserializer.parseRss(inputStream);

        }).onErrorReturnItem(ChannelEntry.builder().build())
          .subscribeOn(Schedulers.io());
    }
}
