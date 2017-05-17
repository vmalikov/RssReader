package com.justforfun.rssreader.feature.shared;

import android.os.Bundle;

import com.justforfun.rssreader.R;
import com.justforfun.rssreader.feature.feed.FeedFragment;

/**
 * Created by Vladimir on 5/16/17.
 */
public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addFragment(R.id.fragment, new FeedFragment());
    }
}
