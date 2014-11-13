package com.mustafasevgi.socialcaps.provider;

import com.squareup.otto.Bus;

public final class BusProvider {

    private static Bus mBus = new Bus();

    public static Bus getInstance() {
        return mBus;
    }

    private BusProvider() {
    }
}