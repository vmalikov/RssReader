package com.justforfun.rssreader.parser;

import android.util.Log;

import com.justforfun.rssreader.model.FeedEntry;
import com.justforfun.rssreader.network.Constants;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

/**
 * Created by Vladimir on 5/16/17.
 */

public class FeedItemDeserializer extends XmlDeserializer {
    private static final String TAG = FeedItemDeserializer.class.getSimpleName();

    public static FeedEntry readEntry(XmlPullParser parser) throws IOException, XmlPullParserException {

        skipUntil(parser, Constants.ITEM);

        FeedEntry.Builder builder = FeedEntry.builder();

        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();

            if (name.equals(Constants.TITLE)) {
                builder.setTitle(readByTagName(Constants.TITLE, parser));
            } else if (name.equals(Constants.DESCRIPTION)) {
                builder.setDescription(readByTagName(Constants.DESCRIPTION, parser));
            } else if (name.equals(Constants.CONTENT_ENCODED)) {
                builder.setDescription(readByTagName(Constants.CONTENT_ENCODED, parser));
            } else if (name.equals(Constants.LINK)) {
                builder.setLink(readByTagName(Constants.LINK, parser));
            } else if (name.equals(Constants.PUB_DATE)) {
                builder.setPubDate(readByTagName(Constants.PUB_DATE, parser));
            } else {
                skip(parser);
            }
        }
        return builder.build();
    }
}
