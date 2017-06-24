package com.justforfun.rssreader.feature.search_feed;

import android.support.annotation.NonNull;
import com.justforfun.rssreader.R;
import com.justforfun.rssreader.feature.search_feed.adapter.SourceSelectionAdapter;
import com.justforfun.rssreader.feature.search_feed.model.ResourceRepository;
import com.justforfun.rssreader.network.RepositoryHelper;

/**
 * Created by Vladimir on 6/24/17.
 */

public class SelectSourceFragment extends SelectFragment {

    @Override @NonNull protected SourceSelectionAdapter provideAdapter() {
        SourceSelectionAdapter adapter = new SourceSelectionAdapter();

        RepositoryHelper.getInstance()
            .getReposLive()
            .observe(this, items -> adapter.setItems(items));

        adapter.setOnItemTouchListener(this::onItemSelected);
        return adapter;
    }

    protected void onItemSelected(ResourceRepository item) {
        model.setRepository(item);
        router.showScreen(new SelectUserFragment());
    }

    @Override protected void handleNewItem() {
        String newValue = binding.username.getText().toString();
        ResourceRepository newRepo = new ResourceRepository().setTitle(newValue).setUrl(newValue);
        RepositoryHelper.getInstance().addRepo(newRepo);
    }

    @NonNull @Override protected String getHint() {
        return getString(R.string.new_source_hint);
    }
}
