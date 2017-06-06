package com.justforfun.rssreader.feature.feed;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
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
import com.justforfun.rssreader.feature.shared.ILoadingView;
import com.justforfun.rssreader.feature.shared.viewmodel.SharedViewModel;
import com.justforfun.rssreader.util.ImageLoader;

/**
 * Created by Vladimir on 5/16/17.
 */

public class FeedFragment extends BaseFragment implements ILoadingView {

    private FeedLayoutBinding binding;
    private FeedViewModel feedViewModel;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.feed_layout,
                                        container, false);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        feedViewModel = ViewModelProviders.of(this).get(FeedViewModel.class);

        feedViewModel.getChannelData()
                     .observe(this, channel -> onDataUpdated(channel));

        SharedViewModel sharedViewModel = ViewModelProviders.of(getActivity()).get(SharedViewModel.class);

        sharedViewModel.getRepositoryClass().observe(this, feedViewModel::setRepository);
        sharedViewModel.getUsername().observe(this, this::showFeed);

        setupFeedsList();
    }

    private void showFeed(@NonNull String username) {
        feedViewModel.loadChannelFor(username)
                .doOnSubscribe(s -> showLoading())
                .doOnSuccess(s -> hideLoading())
                .doOnError(e -> showError(e.toString()))
                .subscribe();
    }

    private void onDataUpdated(@NonNull ChannelData channel) {
        if(channel.equals(ChannelData.empty)) {
            showEmptyState();
        } else {
            hideEmptyState();
            updateToolbar(channel);
        }
    }

    private void setupFeedsList() {
        binding.feedsList.setHasFixedSize(true);
        binding.feedsList.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.feedsList.setAdapter(provideAdapter());
    }

    private FeedsListAdapter provideAdapter() {
        FeedsListAdapter adapter = new FeedsListAdapter();

        feedViewModel.getChannelData().observe(this,
                channel -> adapter.setItems(channel.items));

        adapter.getPositionClicks()
                .doOnNext(item -> router.showLink(item.link))
                .subscribe();

        return adapter;
    }

    public void updateToolbar(ChannelData channel) {
        toolbarableView.setTitle(channel.title);
        ImageLoader.loadLogo(getActivity().getApplicationContext(),
                channel.image.url, drawable -> toolbarableView.setLogoFrom(drawable));
    }

    public void showEmptyState() {
        binding.emptyText.setVisibility(View.VISIBLE);
    }

    public void hideEmptyState() {
        binding.emptyText.setVisibility(View.GONE);
    }

    @Override
    public void showLoading() {
        binding.progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        binding.progressBar.setVisibility(View.GONE);
    }

    public void showError(String message) {
        hideLoading();
        showToastMessage(message);
    }
}
