package com.justforfun.rssreader.feature.search_feed.adapter;

import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;
import com.justforfun.rssreader.R;
import com.justforfun.rssreader.databinding.SourceItemLayoutBinding;
import com.justforfun.rssreader.feature.search_feed.model.UsernameItem;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vladimir on 6/24/17.
 */

public class UserSelectionAdapter
    extends BaseSelectionAdapter<UserSelectionAdapter.ViewHolder, UsernameItem> {

    private ArrayList<UsernameItem> items;

    public UserSelectionAdapter() {
        items = new ArrayList<>();
    }

    public void setItems(@Nullable List<UsernameItem> items) {
        this.items.clear();
        if (items != null) {
            this.items.addAll(items);
        }
        notifyDataSetChanged();
    }

    @Override public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        SourceItemLayoutBinding binding =
            DataBindingUtil.inflate(inflater, R.layout.source_item_layout, parent, false);
        return new ViewHolder(binding);
    }

    @Override public void onBindViewHolder(ViewHolder holder, int position) {
        UsernameItem item = getItem(position);
        holder.itemView.setOnClickListener(v -> onClickSubject.onNext(item));
        holder.bind(item);
    }

    @Override public int getItemCount() {
        return items.size();
    }

    private UsernameItem getItem(int position) {
        return position < items.size() ? items.get(position) : null;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView title;

        ViewHolder(SourceItemLayoutBinding binding) {
            super(binding.getRoot());
            title = binding.title;
        }

        void bind(UsernameItem item) {
            title.setText(item.getUsername());
        }
    }
}
