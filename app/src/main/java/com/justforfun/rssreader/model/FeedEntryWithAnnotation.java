package com.justforfun.rssreader.model;

import com.justforfun.simplexml.annotation.XmlName;

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

@XmlName(name = "item")
public class FeedEntryWithAnnotation {
    @XmlName(name = "title")
    public String title;
    @XmlName(names = {"content:encoded", "description"})
    public String description;
    @XmlName(name = "link")
    public String link;
    @XmlName(name = "pubDate")
    public String pubDate;

    @Override
    public String toString() {
        return "FeedEntryWithAnnotation{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", link='" + link + '\'' +
                ", pubDate='" + pubDate + '\'' +
                '}';
    }
}
