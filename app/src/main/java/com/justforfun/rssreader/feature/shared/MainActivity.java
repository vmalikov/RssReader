package com.justforfun.rssreader.feature.shared;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.justforfun.rssreader.R;
import com.justforfun.rssreader.databinding.ActivityMainBinding;
import com.justforfun.rssreader.feature.feed.FeedFragment;
import com.justforfun.rssreader.feature.search_feed.SourceSelectorFragment;
import com.justforfun.rssreader.feature.shared.viewmodel.SharedViewModel;

/**
 * Created by Vladimir on 5/16/17.
 */
public class MainActivity extends BaseActivity implements IToolbarableView, IRouter {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        setSupportActionBar(binding.toolbar);

        if(savedInstanceState == null) {
            binding.webview.getSettings().setJavaScriptEnabled(true);
            showScreen(new SourceSelectorFragment());
        }
    }

    @Override
    public void setTitle(String value) {
        binding.toolbar.setTitle(value);
    }

    @Override
    public void setLogoFrom(Drawable icon) {
        binding.toolbar.setNavigationIcon(icon);
    }

    @Override
    public void showScreen(BaseFragment fragment) {
        binding.webview.setVisibility(View.GONE);
        addFragment(R.id.fragment, fragment);
    }

    @Override
    public void showLink(String link) {
        binding.webview.setVisibility(View.VISIBLE);
        binding.webview.loadUrl(link);
    }

    @Override
    public void onBackPressed() {
        if(binding.webview.isShown()) {
            binding.webview.setVisibility(View.GONE);
            binding.webview.loadUrl("about:blank");
            return;
        }
        super.onBackPressed();
    }
}
