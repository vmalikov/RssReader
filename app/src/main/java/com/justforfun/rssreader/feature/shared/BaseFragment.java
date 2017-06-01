package com.justforfun.rssreader.feature.shared;

import android.arch.lifecycle.LifecycleFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Toast;

/**
 * Created by Vladimir on 5/16/17.
 */

public class BaseFragment extends LifecycleFragment implements IScreen {

    protected IRouter router;
    protected IToolbarableView toolbarableView;

    protected void showToastMessage(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if(getActivity() instanceof IRouter) {
            router = (IRouter) getActivity();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        router = null;
    }

    @Override
    public void setToolbarableView(IToolbarableView toolbarableView) {
        if(toolbarableView == null)
            throw new IllegalArgumentException("Please provide view with toolbar!");

        this.toolbarableView = toolbarableView;
    }
}
