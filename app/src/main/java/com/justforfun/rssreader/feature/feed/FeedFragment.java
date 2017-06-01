package com.justforfun.rssreader.feature.feed;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.justforfun.rssreader.R;
import com.justforfun.rssreader.databinding.FeedLayoutBinding;
import com.justforfun.rssreader.feature.feed.adapter.FeedsListAdapter;
import com.justforfun.rssreader.feature.feed.model.ChannelData;
import com.justforfun.rssreader.feature.shared.BaseFragment;
import com.justforfun.rssreader.feature.shared.IScreen;
import com.justforfun.rssreader.feature.shared.IToolbarableView;
import com.justforfun.rssreader.feature.shared.viewmodel.SharedViewModel;
import com.justforfun.rssreader.util.ImageLoader;

/**
 * Created by Vladimir on 5/16/17.
 */

public class FeedFragment extends BaseFragment implements IChannelableView {
    private static final String TAG = FeedFragment.class.getSimpleName();

    private FeedLayoutBinding binding;
    private FeedsListAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.feed_layout,
                                        container, false);

        setupFeedsList();

        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        FeedViewModel feedViewModel = ViewModelProviders.of(this)
                                                        .get(FeedViewModel.class);

        feedViewModel.setView(this);
        feedViewModel.getChannelData()
                     .observe(this, channel -> showFeed((ChannelData) channel));


        SharedViewModel model = ViewModelProviders.of(getActivity()).get(SharedViewModel.class);
        model.getUsername().observe(this, feedViewModel::loadChannelFor);
    }

    private void setupFeedsList() {
        binding.feedsList.setHasFixedSize(true);
        binding.feedsList.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new FeedsListAdapter();
        binding.feedsList.setAdapter(adapter);
    }

    @Override
    public void showFeed(ChannelData channel) {
        adapter.setItems(channel.items);
    }

    @Override
    public void updateToolbar(ChannelData channel) {
        toolbarableView.setTitle(channel.title);
        ImageLoader.loadLogo(getActivity().getApplicationContext(),
                channel.image.url, drawable -> toolbarableView.setLogoFrom(drawable));
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
