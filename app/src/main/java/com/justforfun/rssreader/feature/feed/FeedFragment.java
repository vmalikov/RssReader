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
import com.justforfun.rssreader.feature.search_feed.model.UsernameItem;
import com.justforfun.rssreader.feature.shared.BaseFragment;
import com.justforfun.rssreader.feature.shared.viewmodel.SharedViewModel;
import com.justforfun.rssreader.util.ImageLoader;

/**
 * Created by Vladimir on 5/16/17.
 */

public class FeedFragment extends BaseFragment {

    private FeedLayoutBinding binding;
    private FeedViewModel feedViewModel;

    @Nullable @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
        @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.feed_layout, container, false);
        return binding.getRoot();
    }

    @Override public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        feedViewModel = ViewModelProviders.of(this).get(FeedViewModel.class);

        feedViewModel.getChannelData().observe(this, channel -> onDataUpdated(channel));

        SharedViewModel sharedViewModel =
            ViewModelProviders.of(getActivity()).get(SharedViewModel.class);

        sharedViewModel.getRepository().observe(this, feedViewModel::setRepository);
        sharedViewModel.getUsername().observe(this, this::showFeed);

        setupFeedsList();
    }

    private void showFeed(@NonNull UsernameItem item) {
        feedViewModel.loadChannelFor(item.getUsername())
            .doOnSubscribe(s -> binding.setIsLoading(true))
            .doOnSuccess(s -> binding.setIsLoading(false))
            .doOnError(e -> binding.setIsLoading(false))
            .doOnError(e -> showToastMessage(e.toString()))
            .subscribe();
    }

    private void onDataUpdated(@NonNull ChannelData channel) {

        boolean isEmpty = channel.equals(ChannelData.empty);
        binding.setIsEmpty(isEmpty);

        if (!isEmpty) {
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

        feedViewModel.getChannelData().observe(this, channel -> adapter.setItems(channel.items));

        adapter.getPositionClicks().doOnNext(item -> router.showLink(item.link)).subscribe();

        return adapter;
    }

    public void updateToolbar(ChannelData channel) {
        toolbarableView.setTitle(channel.title);
        ImageLoader.loadLogo(getActivity().getApplicationContext(), channel.image.url,
            drawable -> toolbarableView.setLogoFrom(drawable));
    }
}
