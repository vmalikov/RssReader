package com.justforfun.rssreader.model;

import android.text.TextUtils;

import com.justforfun.simplexml.annotation.XmlAsArray;
import com.justforfun.simplexml.annotation.XmlName;

import java.util.ArrayList;

/**
 * Created by Vladimir on 6/15/17.
 */

@XmlName(name = "channel")
public class ChannelEntryWithAnnotations {

    @XmlName(name = "title")
    public String title;
    @XmlName(name = "description")
    public String description;
    @XmlName(name = "link")
    public String link;
    @XmlName(name = "lastBuildDate")
    public String lastBuildDate;
    @XmlName(name = "image")
    public ChannelImageWithAnnotations image;
    @XmlAsArray(name = "item")
    public ArrayList<FeedEntryWithAnnotation> items = new ArrayList<>();

    public boolean shouldBeEmpty() {
        return TextUtils.isEmpty(title)
                && TextUtils.isEmpty(description)
                && TextUtils.isEmpty(lastBuildDate)
                && TextUtils.isEmpty(link)
                && (items == null || items.isEmpty())
                ;
    }
}
