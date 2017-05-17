package com.justforfun.rssreader.feature.feed.model;

import java.util.ArrayList;

/**
 * Created by Vladimir on 5/16/17.
 */

public class ChannelData {
    public String title;
    public String description;
    public String link;
    public Image image;
    public ArrayList<FeedItem> items;

    public static class Image {
        public String url;
        public int width;
        public int height;
    }
}
