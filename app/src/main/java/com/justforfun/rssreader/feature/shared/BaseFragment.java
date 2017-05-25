package com.justforfun.rssreader.feature.shared;

import android.arch.lifecycle.LifecycleFragment;
import android.widget.Toast;

/**
 * Created by Vladimir on 5/16/17.
 */

public class BaseFragment extends LifecycleFragment {

    protected void showToastMessage(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }
}
