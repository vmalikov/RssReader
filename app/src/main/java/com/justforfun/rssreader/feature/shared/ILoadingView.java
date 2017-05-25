package com.justforfun.rssreader.feature.shared;

/**
 * Created by Vladimir on 5/16/17.
 */

public interface ILoadingView {
    void showLoading();
    void hideLoading();
    void showError(String message);
}
