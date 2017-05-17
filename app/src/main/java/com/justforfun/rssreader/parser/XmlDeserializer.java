package com.justforfun.rssreader.parser;

import android.text.TextUtils;

import com.justforfun.rssreader.network.Constants;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

/**
 * Created by Vladimir on 5/16/17.
 */

public class XmlDeserializer {

    // Processes simple tags with text only
    protected static String readByTagName(String tagName, XmlPullParser parser) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, null, tagName);
        String text = readText(parser);
        parser.require(XmlPullParser.END_TAG, null, tagName);
        return text;
    }

    // Processes link tags in the feed.
    protected static String readLink(XmlPullParser parser) throws IOException, XmlPullParserException {
        String link = "";
        parser.require(XmlPullParser.START_TAG, null, Constants.LINK);
        String tag = parser.getName();
        String relType = parser.getAttributeValue(null, "rel");
        if (tag.equals(Constants.LINK)) {
            if (!TextUtils.isEmpty(relType) && relType.equals("alternate")){
                link = parser.getAttributeValue(null, "href");
                parser.nextTag();
            } else {
                link = parser.getAttributeValue(null, "href");
            }
        }
        parser.require(XmlPullParser.END_TAG, null, Constants.LINK);
        return link;
    }

    // For the tags title and summary, extracts their text values.
    protected static String readText(XmlPullParser parser) throws IOException, XmlPullParserException {
        String result = "";
        if (parser.next() == XmlPullParser.TEXT) {
            result = parser.getText();
            parser.nextTag();
        }
        return result;
    }

    protected static void skip(XmlPullParser parser) throws XmlPullParserException, IOException {
        if (parser.getEventType() != XmlPullParser.START_TAG) {
            throw new IllegalStateException();
        }
        int depth = 1;
        while (depth != 0) {
            switch (parser.next()) {
                case XmlPullParser.END_TAG:
                    depth--;
                    break;
                case XmlPullParser.START_TAG:
                    depth++;
                    break;
            }
        }
    }

    protected static void skipUntil(XmlPullParser parser, String requiredTag) throws XmlPullParserException, IOException {
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }

            if(TextUtils.equals(parser.getName(), requiredTag)) {
                break;
            }
        }
    }
}
