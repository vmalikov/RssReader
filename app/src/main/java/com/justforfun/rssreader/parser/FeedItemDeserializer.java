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

            switch(name) {
                case Constants.TITLE:
                case Constants.DESCRIPTION:
                case Constants.CONTENT_ENCODED:
                case Constants.LINK:
                case Constants.LAST_BUILD_DATE:
                case Constants.PUB_DATE:
                    String value = readByTagName(name, parser);
                    builder.setValueForFiled(name, value);
                    break;
                default:
                    skip(parser);
            }
        }
        return builder.build();
    }
}
