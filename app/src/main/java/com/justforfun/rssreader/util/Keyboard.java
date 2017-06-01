package com.justforfun.rssreader.util;

import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/**
 * Created by Vladimir on 6/1/17.
 */

public class Keyboard {
    public static void hideKeyboard(View v) {
        if(v == null) return;
        InputMethodManager imm = (InputMethodManager)
                v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);

        imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
    }
}
