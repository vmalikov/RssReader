package com.justforfun.rssreader.model;

import com.google.auto.value.AutoValue;

/**
 * Created by Vladimir on 5/16/17.
 */

/*
 <item>
  <title>Example entry</title>
  <description>Here is some text containing an interesting description.</description>
  <link>http://www.example.com/blog/post/1</link>
  <guid isPermaLink="true">7bd204c6-1655-4c27-aeee-53f933c5395f</guid>
  <pubDate>Sun, 06 Sep 2009 16:20:00 +0000</pubDate>
 </item>
*/

@AutoValue
public abstract class FeedEntry {
    public abstract String title();
    public abstract String description();
    public abstract String link();
    public abstract String pubDate();

    public static FeedEntry.Builder builder() {
        return new AutoValue_FeedEntry.Builder()
                .setTitle("")
                .setDescription("")
                .setLink("")
                .setPubDate("");
    }

    @AutoValue.Builder
    public abstract static class Builder {
        public abstract Builder setTitle(String value);
        public abstract Builder setDescription(String value);
        public abstract Builder setLink(String value);
        public abstract Builder setPubDate(String value);
        public abstract FeedEntry build();
    }
}
