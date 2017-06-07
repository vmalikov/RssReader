package com.justforfun.rssreader.model;

import com.google.auto.value.AutoValue;

import com.justforfun.rssreader.feature.feed.model.ChannelData;
import com.justforfun.rssreader.util.FiledFiller;

import java.lang.reflect.Field;
import java.util.ArrayList;

/**
 * Created by Vladimir on 5/16/17.
 */

/*
    <?xml version="1.0" encoding="UTF-8" ?>
    <rss version="2.0">
    <channel>
     <title>RSS Title</title>
     <description>This is an example of an RSS feed</description>
     <link>http://www.example.com/main.html</link>
     <lastBuildDate>Mon, 06 Sep 2010 00:01:00 +0000 </lastBuildDate>
     <pubDate>Sun, 06 Sep 2009 16:20:00 +0000</pubDate>
     <ttl>1800</ttl>

     <item> ... </item>

    </channel>
    </rss>
 */

@AutoValue
public abstract class ChannelEntry {

    public abstract String title();
    public abstract String description();
    public abstract String link();
    public abstract String lastBuildDate();
    public abstract ChannelImage image();
    public abstract ArrayList<FeedEntry> items();

    public static Builder builder() {
        return new AutoValue_ChannelEntry.Builder()
                .setTitle("")
                .setDescription("")
                .setItems(new ArrayList<>())
                .setLink("")
                .setLastBuildDate("")
                .setImage(ChannelImage.builder().build());
    }

    @AutoValue.Builder
    public abstract static class Builder {
        public abstract Builder setTitle(String value);
        public abstract Builder setDescription(String value);
        public abstract Builder setLink(String value);
        public abstract Builder setLastBuildDate(String value);
        public abstract Builder setImage(ChannelImage value);
        public abstract Builder setItems(ArrayList<FeedEntry> items);
        public abstract ChannelEntry build();

        public void setValueForFiled(String name, String value) {
            FiledFiller.setValueForFiled(this, name, value);
        }

        public void setValueForFiled(String name, ChannelImage value) {
            FiledFiller.setValueForFiled(this, name, value);
        }
    }
}

