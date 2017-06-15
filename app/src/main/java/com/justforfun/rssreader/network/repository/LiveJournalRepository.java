package com.justforfun.rssreader.network.repository;


/**
 * Created by Vladimir on 5/16/17.
 */

public class LiveJournalRepository extends AbstractRepository {
    @Override
    public String getRssUrl() {
        return "http://%s.livejournal.com/data/rss"; // zmey-gadukin;
    }
}
