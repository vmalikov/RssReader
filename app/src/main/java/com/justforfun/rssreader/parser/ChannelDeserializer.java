package com.justforfun.rssreader.parser;

import android.text.TextUtils;
import android.util.Log;

import com.justforfun.rssreader.model.ChannelEntry;
import com.justforfun.rssreader.model.ChannelImage;
import com.justforfun.rssreader.model.FeedEntry;
import com.justforfun.rssreader.network.Constants;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Vladimir on 5/16/17.
 */

public class ChannelDeserializer extends XmlDeserializer {
    private static final String TAG = ChannelDeserializer.class.getSimpleName();

    public static ChannelEntry readEntry(XmlPullParser parser) throws IOException, XmlPullParserException {
        skipUntil(parser, Constants.CHANNEL);

        ChannelEntry.Builder builder = ChannelEntry.builder();
        ArrayList<FeedEntry> items = new ArrayList<>();

        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();

            if (name.equals(Constants.TITLE)) {
                builder.setTitle(readByTagName(Constants.TITLE, parser));
            } else if (name.equals(Constants.DESCRIPTION)) {
                builder.setDescription(readByTagName(Constants.DESCRIPTION, parser));
            } else if (name.equals(Constants.LINK)) {
                builder.setLink(readByTagName(Constants.LINK, parser));
            } else if (name.equals(Constants.LAST_BUILD_DATE)) {
                builder.setLastBuildDate(readByTagName(Constants.LAST_BUILD_DATE, parser));
            } else if (name.equals(Constants.ITEM)) {
                items.add(readItem(parser));
            } else if (name.equals(Constants.IMAGE)) {
                builder.setImage(readImage(parser));
            } else {
                skip(parser);
            }
        }
        return builder
                .setItems(items)
                .build();
    }

    private static FeedEntry readItem(XmlPullParser parser) throws IOException, XmlPullParserException {
        return FeedItemDeserializer.readEntry(parser);
    }

    // Processes link tags in the feed.
    protected static ChannelImage readImage(XmlPullParser parser) throws IOException, XmlPullParserException {
        ChannelImage.Builder builder = ChannelImage.builder();

        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();

            if (name.equals(Constants.URL)) {
                builder.setUrl(readByTagName(Constants.URL, parser));
            } else {
                skip(parser);
            }
        }

        return builder.build();
    }

    public static String getInnerXml(XmlPullParser parser)
            throws XmlPullParserException, IOException {
        StringBuilder sb = new StringBuilder();
        int depth = 1;
        while (depth != 0) {
            switch (parser.next()) {
                case XmlPullParser.END_TAG:
                    depth--;
                    if (depth > 0) {
                        sb.append("</" + parser.getName() + ">");
                    }
                    break;
                case XmlPullParser.START_TAG:
                    depth++;
                    StringBuilder attrs = new StringBuilder();
                    for (int i = 0; i < parser.getAttributeCount(); i++) {
                        attrs.append(parser.getAttributeName(i) + "=\""
                                + parser.getAttributeValue(i) + "\" ");
                    }
                    sb.append("<" + parser.getName() + " " + attrs.toString() + ">");
                    break;
                default:
                    sb.append(parser.getText());
                    break;
            }
        }
        String content = sb.toString();
        return content;
    }
}
