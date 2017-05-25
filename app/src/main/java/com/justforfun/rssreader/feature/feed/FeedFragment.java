package com.justforfun.rssreader.feature.feed;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.justforfun.rssreader.R;
import com.justforfun.rssreader.databinding.FeedLayoutBinding;
import com.justforfun.rssreader.feature.feed.adapter.FeedsListAdapter;
import com.justforfun.rssreader.feature.feed.model.ChannelData;
import com.justforfun.rssreader.feature.shared.BaseFragment;
import com.justforfun.rssreader.feature.shared.IToolbarableView;
import com.justforfun.rssreader.util.ImageLoader;

/**
 * Created by Vladimir on 5/16/17.
 */

public class FeedFragment extends BaseFragment implements IChannelableView {
    private static final String TAG = FeedFragment.class.getSimpleName();

    private ChannelPresenter presenter;
    private FeedLayoutBinding binding;
    private FeedsListAdapter adapter;
    private IToolbarableView toolbarableView;

    public static FeedFragment newInstance(IToolbarableView toolbarableView) {
        return new FeedFragment()
                .setToolbarableView(toolbarableView);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.feed_layout, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupFeedsList();

        presenter = new ChannelPresenter(this);
        presenter.loadChannelFor("zmey-gadukin");
    }

    private void setupFeedsList() {
        binding.feedsList.setHasFixedSize(true);
        binding.feedsList.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new FeedsListAdapter();
        binding.feedsList.setAdapter(adapter);
    }

    public FeedFragment setToolbarableView(IToolbarableView toolbarableView) {
        if(toolbarableView == null) throw new IllegalArgumentException("Please provide view with toolbar!");

        this.toolbarableView = toolbarableView;
        return this;
    }

    @Override
    public void showFeed(ChannelData channel) {
        adapter.setItems(channel.items);
    }

    @Override
    public void updateToolbar(ChannelData channel) {
        toolbarableView.setTitle(channel.title);
        ImageLoader.loadLogo(getContext(), channel.image.url, drawable -> toolbarableView.setLogoFrom(drawable));
    }

    @Override
    public void showLoading() {
        binding.progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        binding.progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showError(String message) {
        showToastMessage(message);
    }
}
