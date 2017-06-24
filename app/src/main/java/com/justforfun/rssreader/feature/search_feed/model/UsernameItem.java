package com.justforfun.rssreader.feature.search_feed.model;

/**
 * Created by Vladimir on 6/24/17.
 */

public class UsernameItem {
    private String username;

    public UsernameItem(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UsernameItem that = (UsernameItem) o;

        return username != null ? username.equals(that.username) : that.username == null;
    }

    @Override public int hashCode() {
        return username != null ? username.hashCode() : 0;
    }
}
