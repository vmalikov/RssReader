package com.justforfun.rssreader.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Vladimir on 5/17/17.
 */

public class StringFormatter {

    public static String formatPubDate(String origin) {
        // origin sample: Tue, 16 May 2017 09:48:36 GMT
        // dest sample: 27.04.2017 14:35
        DateFormat dfOrigin = new SimpleDateFormat("E, dd MMM yyyy hh:mm:ss Z");
        DateFormat dfDest = new SimpleDateFormat("dd.MM.yyyy hh:mm");
        try {
            Date startDate = dfOrigin.parse(origin);
            return dfDest.format(startDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return origin;
    }
}
