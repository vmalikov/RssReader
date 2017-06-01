package com.justforfun.rssreader.feature.shared;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Vladimir on 5/16/17.
 */

public class BaseActivity extends AppCompatActivity {

    protected void addFragment(int containerViewId, Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .add(containerViewId, fragment)
                .addToBackStack(fragment.getClass().getName())
                .commit();
    }
}
