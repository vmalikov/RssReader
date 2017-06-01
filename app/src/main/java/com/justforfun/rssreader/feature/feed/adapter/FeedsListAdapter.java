package com.justforfun.rssreader.feature.feed.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.justforfun.rssreader.R;
import com.justforfun.rssreader.databinding.FeedItemLayoutBinding;
import com.justforfun.rssreader.feature.feed.model.FeedItem;
import com.justforfun.rssreader.util.StringFormatter;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

/**
 * Created by Vladimir on 5/17/17.
 */

public class FeedsListAdapter extends RecyclerView.Adapter<FeedsListAdapter.FeedViewHolder> {
    private static final String TAG = FeedsListAdapter.class.getSimpleName();

    private final PublishSubject<FeedItem> onClickSubject = PublishSubject.create();

    private ArrayList<FeedItem> items;

    public FeedsListAdapter() {
        items = new ArrayList<>();
    }

    public void setItems(ArrayList<FeedItem> items) {
        this.items.clear();
        this.items.addAll(items);
        notifyDataSetChanged();
    }

    public Observable<FeedItem> getPositionClicks() {
        return onClickSubject;
    }

    @Override
    public FeedViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        FeedItemLayoutBinding binding = DataBindingUtil.inflate(inflater,
                R.layout.feed_item_layout, parent, false);
        return new FeedViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(FeedViewHolder holder, int position) {
        FeedItem item = getItem(position);
        holder.title.setText(item.title);
        holder.description.setText(Html.fromHtml(item.description));
        holder.pubDate.setText(StringFormatter.formatPubDate(item.pubDate));
        holder.itemView.setOnClickListener(v -> onClickSubject.onNext(item));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public FeedItem getItem(int position) {
        return position < items.size() ? items.get(position) : new FeedItem();
    }

    public static class FeedViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        TextView description;
        TextView pubDate;

        public FeedViewHolder(FeedItemLayoutBinding binding) {
            super(binding.getRoot());
            title = binding.title;
            description = binding.description;
            pubDate = binding.pubDate;
        }
    }
}
