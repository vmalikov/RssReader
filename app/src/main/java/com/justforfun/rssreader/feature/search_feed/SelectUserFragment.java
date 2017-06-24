package com.justforfun.rssreader.feature.search_feed;

import android.support.annotation.NonNull;
import com.justforfun.rssreader.R;
import com.justforfun.rssreader.feature.feed.FeedFragment;
import com.justforfun.rssreader.feature.search_feed.adapter.UserSelectionAdapter;
import com.justforfun.rssreader.feature.search_feed.model.UsernameItem;

/**
 * Created by Vladimir on 6/24/17.
 */

public class SelectUserFragment extends SelectFragment {

    @Override @NonNull protected UserSelectionAdapter provideAdapter() {
        UserSelectionAdapter adapter = new UserSelectionAdapter();

        model.getRepository()
            .observe(this, repo -> repo.getUsernamesLive()
                .observe(this, usernames -> adapter.setItems(usernames)));

        adapter.setOnItemTouchListener(this::onItemSelected);
        return adapter;
    }

    protected void onItemSelected(UsernameItem item) {
        model.setUsername(item);
        router.showScreen(new FeedFragment());
    }

    @Override protected void handleNewItem() {
        model.getRepository().observe(this, repo -> {
            repo.addUsername(new UsernameItem(binding.username.getText().toString()));
        });
    }

    @NonNull @Override protected String getHint() {
        return getString(R.string.new_user_hint);
    }
}
