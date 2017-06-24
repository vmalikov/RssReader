package com.justforfun.rssreader.feature.search_feed.adapter;

import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;
import com.justforfun.rssreader.R;
import com.justforfun.rssreader.databinding.SourceItemLayoutBinding;
import com.justforfun.rssreader.feature.search_feed.model.ResourceRepository;
import java.util.ArrayList;

/**
 * Created by Vladimir on 6/22/17.
 */

public class SourceSelectionAdapter extends BaseSelectionAdapter<SourceSelectionAdapter.SourceSelectionViewHolder, ResourceRepository> {

    private ArrayList<ResourceRepository> items;

    public SourceSelectionAdapter() {
        items = new ArrayList<>();
    }

    public void setItems(@Nullable ArrayList<ResourceRepository> items) {
        this.items.clear();
        if(items != null) {
            this.items.addAll(items);
        }
        notifyDataSetChanged();
    }

    @Override
    public SourceSelectionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        SourceItemLayoutBinding binding = DataBindingUtil.inflate(inflater,
                R.layout.source_item_layout, parent, false);
        return new SourceSelectionViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(SourceSelectionViewHolder holder, int position) {
        ResourceRepository item = getItem(position);
        holder.itemView.setOnClickListener(v -> onClickSubject.onNext(item));
        holder.bind(item);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    private ResourceRepository getItem(int position) {
        return position < items.size() ? items.get(position) : null;
    }

    static class SourceSelectionViewHolder extends RecyclerView.ViewHolder {

        TextView title;

        SourceSelectionViewHolder(SourceItemLayoutBinding binding) {
            super(binding.getRoot());
            title = binding.title;
        }

        <T> void bind(T item) {
            ResourceRepository repo = (ResourceRepository) item;
            title.setText(repo.getTitle());
        }
    }
}
