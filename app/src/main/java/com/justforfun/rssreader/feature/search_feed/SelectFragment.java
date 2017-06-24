package com.justforfun.rssreader.feature.search_feed;

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
import com.justforfun.rssreader.databinding.SourceSelectorLayoutBinding;
import com.justforfun.rssreader.feature.search_feed.adapter.BaseSelectionAdapter;
import com.justforfun.rssreader.feature.shared.BaseFragment;
import com.justforfun.rssreader.feature.shared.viewmodel.SharedViewModel;
import com.justforfun.rssreader.util.Keyboard;

/**
 * Created by Vladimir on 5/16/17.
 */

public abstract class SelectFragment extends BaseFragment {

    protected SharedViewModel model;
    protected SourceSelectorLayoutBinding binding;

    @NonNull protected abstract BaseSelectionAdapter provideAdapter();

    abstract protected void handleNewItem();

    @NonNull abstract protected String getHint();

    @Nullable @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
        @Nullable Bundle savedInstanceState) {

        binding =
            DataBindingUtil.inflate(inflater, R.layout.source_selector_layout, container, false);

        return binding.getRoot();
    }

    @Override public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        model = ViewModelProviders.of(getActivity()).get(SharedViewModel.class);

        setupAddListener();
        setupList();

        binding.username.setHint(getHint());
    }

    private void setupList() {
        binding.list.setHasFixedSize(true);
        binding.list.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.list.setAdapter(provideAdapter());
    }

    protected void setupAddListener() {
        binding.add.setOnClickListener(v -> {
            Keyboard.hideKeyboard(binding.username);

            handleNewItem();

            binding.username.setText("");
            binding.username.clearFocus();
        });
    }
}
