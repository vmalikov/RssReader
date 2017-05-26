package com.justforfun.rssreader.feature.search_feed;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import com.justforfun.rssreader.R;
import com.justforfun.rssreader.databinding.SourceSelectorLayoutBinding;
import com.justforfun.rssreader.feature.feed.FeedFragment;
import com.justforfun.rssreader.feature.shared.BaseFragment;
import com.justforfun.rssreader.feature.shared.IRouter;
import com.justforfun.rssreader.feature.shared.IScreen;
import com.justforfun.rssreader.feature.shared.IToolbarableView;
import com.justforfun.rssreader.feature.shared.viewmodel.SharedViewModel;

/**
 * Created by Vladimir on 5/16/17.
 */

public class SourceSelectorFragment extends BaseFragment implements IScreen {

    private SharedViewModel model;
    private SourceSelectorLayoutBinding binding;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.source_selector_layout, container, false);

        model = ViewModelProviders.of(getActivity()).get(SharedViewModel.class);
        model.getRouterData().observe(this, router -> setupGoListener(router));

        return binding.getRoot();
    }

    private void setupGoListener(IRouter router) {
        binding.go.setOnClickListener(v -> {
            hideKeyboard(binding.editText);
            model.setUsername(binding.editText.getText().toString());
            router.showScreen(new FeedFragment());
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // FIXME: 5/25/17 remove stub zmey-gadukin
        binding.editText.setText("evo-lutio");
        binding.editText.setSelection(binding.editText.getText().length());
    }

    @Override
    public void setToolbarableView(IToolbarableView view) {

    }

    // TODO: 5/25/17 move to Util class
    private void hideKeyboard(View v) {
        InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
    }
}
