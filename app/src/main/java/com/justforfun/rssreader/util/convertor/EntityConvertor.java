package com.justforfun.rssreader.util.convertor;

import android.support.annotation.NonNull;

import com.justforfun.rssreader.feature.feed.model.ChannelData;
import com.justforfun.rssreader.model.ChannelEntry;
import com.justforfun.rssreader.model.ChannelEntryWithAnnotations;

import java.util.HashMap;

import io.reactivex.Single;

/**
 * Created by Vladimir on 6/1/17.
 */

public abstract class EntityConvertor<T> {

    public static <T> Single<ChannelData> convertData(T entry) {
        return getConvertor(entry).convertToViewData(entry);
    }

    @NonNull public abstract Single<ChannelData> convertToViewData(T entry);

    private static HashMap<Class, EntityConvertor> map;

    static {
        map = new HashMap<>();
        map.put(ChannelEntry.class, new ChannelEntryConvertor());
        map.put(ChannelEntryWithAnnotations.class, new ChannelEntryWithAnnotationConvertor());
    }

    private static <T> EntityConvertor getConvertor(T entry) {
        return map.get(entry.getClass());
    }
}
