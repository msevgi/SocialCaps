package com.mustafasevgi.socialcaps;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import com.mustafasevgi.socialcaps.provider.BusProvider;
import com.squareup.otto.Bus;

/**
 * Created by mustafasevgi on 10/11/14.
 */
public class BaseActionBarActivity extends ActionBarActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getBus().register(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        getBus().unregister(this);
    }

    protected Bus getBus() {
        return BusProvider.getInstance();
    }
}
