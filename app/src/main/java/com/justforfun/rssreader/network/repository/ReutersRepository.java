package com.justforfun.rssreader.network.repository;


/**
 * Created by Vladimir on 5/16/17.
 */

public class ReutersRepository extends AbstractRepository {
    @Override
    public String getRssUrl() {
        return "http://feeds.reuters.com/reuters/%s"; // businessNews
    }
}
