package com.justforfun.rssreader.model;

import com.justforfun.simplexml.annotation.XmlName;

/**
 * Created by Vladimir on 5/16/17.
 */

@XmlName(name = "image")
public class ChannelImageWithAnnotations {
    @XmlName(name = "url")
    public String url;
}