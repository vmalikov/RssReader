package com.justforfun.rssreader.network;

import android.arch.lifecycle.MutableLiveData;
import com.justforfun.rssreader.feature.search_feed.model.ResourceRepository;
import com.justforfun.rssreader.feature.search_feed.model.UsernameItem;
import java.util.ArrayList;

/**
 * Created by Vladimir on 6/16/17.
 */

public class RepositoryHelper {
    private MutableLiveData<ArrayList<ResourceRepository>> reposLive = new MutableLiveData<>();

    private static RepositoryHelper sInstance;

    public static RepositoryHelper getInstance() {
        if (sInstance == null) {
            sInstance = new RepositoryHelper();
            sInstance.initRepos();
        }
        return sInstance;
    }

    // FIXME: 6/22/17 make reading from storage
    private void initRepos() {
        addRepo(provideLJRepo());
        addRepo(provideMediumRepo());
        addRepo(provideReutersRepo());
    }

    public boolean addRepo(ResourceRepository repo) {
        ArrayList<ResourceRepository> value = reposLive.getValue();
        if (value == null) {
            value = new ArrayList<>();
        }
        if (value.contains(repo)) return false;

        value.add(repo);
        setReposLive(value);
        return true;
    }

    public MutableLiveData<ArrayList<ResourceRepository>> getReposLive() {
        return reposLive;
    }

    public void setReposLive(ArrayList<ResourceRepository> repos) {
        this.reposLive.setValue(repos);
    }

    // FIXME: 6/22/17 remove stubs
    private ResourceRepository provideLJRepo() {
        ArrayList<UsernameItem> usernames = new ArrayList<>();
        usernames.add(new UsernameItem("zmey-gadukin"));
        usernames.add(new UsernameItem("evo-lutio"));

        return new ResourceRepository().setTitle("Live Journal")
            .setUrl("http://%s.livejournal.com/data/rss")
            .addUsernames(usernames);
    }

    private ResourceRepository provideMediumRepo() {
        ArrayList<UsernameItem> usernames = new ArrayList<>();
        usernames.add(new UsernameItem("Medium"));

        return new ResourceRepository().setTitle("Medium")
            .setUrl("https://medium.com/feed/@%s")
            .addUsernames(usernames);
    }

    private ResourceRepository provideReutersRepo() {
        ArrayList<UsernameItem> usernames = new ArrayList<>();
        usernames.add(new UsernameItem("businessNews"));
        usernames.add(new UsernameItem("companyNews"));

        return new ResourceRepository().setTitle("Reuters")
            .setUrl("http://feeds.reuters.com/reuters/%s")
            .addUsernames(usernames);
    }
}
