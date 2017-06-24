package com.justforfun.rssreader.feature.search_feed.adapter;

import android.support.v7.widget.RecyclerView;
import io.reactivex.functions.Consumer;
import io.reactivex.subjects.PublishSubject;

/**
 * Created by Vladimir on 6/24/17.
 */

public abstract class BaseSelectionAdapter<T extends RecyclerView.ViewHolder, K> extends RecyclerView.Adapter<T> {

    protected final PublishSubject<K> onClickSubject = PublishSubject.create();

    public void setOnItemTouchListener(Consumer<? super K> onNext) {
        onClickSubject.doOnNext(onNext::accept).subscribe();
    }
}
