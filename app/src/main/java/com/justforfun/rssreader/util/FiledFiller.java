package com.justforfun.rssreader.util;

import com.justforfun.rssreader.model.ChannelImage;

import java.lang.reflect.Field;

/**
 * Created by Vladimir on 6/7/17.
 */

public class FiledFiller {

    public static void setValueForFiled(Object instance, String name, String value) {
        for (Field field : instance.getClass().getDeclaredFields()) {
            field.setAccessible(true); // You might want to set modifier to public first.
            if(field.getName().equals(name)) {
                try {
                    field.set(instance, value);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void setValueForFiled(Object instance, String name, ChannelImage value) {
        for (Field field : instance.getClass().getDeclaredFields()) {
            field.setAccessible(true); // You might want to set modifier to public first.
            if(field.getName().equals(name)) {
                try {
                    field.set(instance, value);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
