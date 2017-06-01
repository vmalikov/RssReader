package com.justforfun.rssreader.feature.search_feed;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.justforfun.rssreader.R;
import com.justforfun.rssreader.databinding.SourceSelectorLayoutBinding;
import com.justforfun.rssreader.feature.feed.FeedFragment;
import com.justforfun.rssreader.feature.shared.BaseFragment;
import com.justforfun.rssreader.feature.shared.IRouter;
import com.justforfun.rssreader.feature.shared.viewmodel.SharedViewModel;
import com.justforfun.rssreader.network.repository.LiveJournalRepository;
import com.justforfun.rssreader.network.repository.MediumRepository;
import com.justforfun.rssreader.util.Keyboard;

/**
 * Created by Vladimir on 5/16/17.
 */

public class SourceSelectorFragment extends BaseFragment {

    private SharedViewModel model;
    private SourceSelectorLayoutBinding binding;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.source_selector_layout,
                                           container, false);

        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        model = ViewModelProviders.of(getActivity()).get(SharedViewModel.class);
        setupGoListener(router);
        setupSourceGroupListeners();

        // FIXME: 5/25/17 remove stub zmey-gadukin :: evo-lutio :: Medium
        binding.username.setText("Medium");
        binding.username.setSelection(binding.username.getText().length());
    }

    private void setupGoListener(IRouter router) {
        binding.go.setOnClickListener(v -> {
            Keyboard.hideKeyboard(binding.username);
            model.setUsername(binding.username.getText().toString());
            router.showScreen(new FeedFragment());
        });
    }

    private void setupSourceGroupListeners() {
        // initial value
        model.setRepositoryClass(LiveJournalRepository.class);

        binding.ljButton.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if(isChecked) model.setRepositoryClass(LiveJournalRepository.class);
        });

        binding.mediumButton.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if(isChecked) model.setRepositoryClass(MediumRepository.class);
        });
    }
}
