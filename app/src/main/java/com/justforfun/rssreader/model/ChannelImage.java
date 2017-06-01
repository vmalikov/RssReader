package com.justforfun.rssreader.model;

import com.google.auto.value.AutoValue;

/**
 * Created by Vladimir on 5/16/17.
 */

@AutoValue
public abstract class ChannelImage {
    public abstract String url();

    public static Builder builder() {
        return new AutoValue_ChannelImage.Builder()
                .setUrl("");
    }

    @AutoValue.Builder
    public abstract static class Builder {
        public abstract Builder setUrl(String value);
        public abstract ChannelImage build();
    }
}