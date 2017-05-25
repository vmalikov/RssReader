package com.justforfun.rssreader.feature.shared;

import android.databinding.DataBindingUtil;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

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

        addFragment(R.id.fragment, FeedFragment.newInstance(this));
    }

    @Override
    public void setTitle(String value) {
        binding.toolbar.setTitle(value);
    }

    @Override
    public void setLogoFrom(Drawable icon) {
        binding.toolbar.setNavigationIcon(icon);
    }
}
