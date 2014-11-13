package com.mustafasevgi.socialcaps;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.mustafasevgi.socialcaps.provider.BusProvider;
import com.squareup.otto.Bus;

public abstract class BaseFragment extends Fragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getBus().register(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getBus().unregister(this);
    }

    protected Bus getBus() {
        return BusProvider.getInstance();
    }
}
