package com.justforfun.rssreader.feature.search_feed.model;

import android.arch.lifecycle.MutableLiveData;
import com.justforfun.rssreader.feature.feed.model.ChannelData;
import com.justforfun.rssreader.network.RssClient;
import com.justforfun.rssreader.util.convertor.EntityConvertor;
import io.reactivex.Single;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vladimir on 6/22/17.
 */

public class ResourceRepository {
    private String title;
    private String url;
    private MutableLiveData<ArrayList<UsernameItem>> usernamesLive = new MutableLiveData<>();

    public Single<ChannelData> fetchFeed(String user) {
        return RssClient.getInstance().fetchFeedFor(user, url)
                .flatMap(EntityConvertor::convertData);
    }

    public ResourceRepository setTitle(String title) {
        this.title = title;
        return this;
    }

    public ResourceRepository setUrl(String url) {
        this.url = url;
        return this;
    }

    public ResourceRepository addUsername(UsernameItem usernameItem) {
        ArrayList<UsernameItem> value = usernamesLive.getValue();
        if (value == null) {
            value = new ArrayList<>();
        }
        if (value.contains(usernameItem)) return this;

        value.add(usernameItem);
        setReposLive(value);

        return this;
    }

    public ResourceRepository addUsernames(List<UsernameItem> usernameItems) {
        ArrayList<UsernameItem> value = usernamesLive.getValue();
        if (value == null) {
            value = new ArrayList<>();
        }
        if (value.contains(usernameItems)) return this;

        value.addAll(usernameItems);
        setReposLive(value);

        return this;
    }

    public MutableLiveData<ArrayList<UsernameItem>> getUsernamesLive() {
        return usernamesLive;
    }

    public void setReposLive(ArrayList<UsernameItem> repos) {
        this.usernamesLive.setValue(repos);
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ResourceRepository that = (ResourceRepository) o;

        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        return url != null ? url.equals(that.url) : that.url == null;
    }

    @Override
    public int hashCode() {
        int result = title != null ? title.hashCode() : 0;
        result = 31 * result + (url != null ? url.hashCode() : 0);
        return result;
    }
}
