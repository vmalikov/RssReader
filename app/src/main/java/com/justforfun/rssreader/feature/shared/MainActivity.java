package com.justforfun.rssreader.feature.shared;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.Snackbar;

import com.justforfun.rssreader.R;
import com.justforfun.rssreader.databinding.ActivityMainBinding;
import com.justforfun.rssreader.feature.feed.FeedFragment;

/**
 * Created by Vladimir on 5/16/17.
 */
public class MainActivity extends BaseActivity implements IToolbarableView {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        setSupportActionBar(binding.toolbar);

        binding.fab.setOnClickListener(view -> Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show());

        addFragment(R.id.fragment, FeedFragment.newInstance(this));
    }

    @Override
    public void setTitle(String value) {
        binding.toolbarLayout.setTitle(value);
    }
}
