package com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.eventbus;

import android.util.Log;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by webprog on 06.07.17.
 */

public abstract class EventsHandler implements Subscriber{

    private static final String TAG = "EventsHandler";

    @Override
    public void subscribe() {
        EventBus.getDefault().register(this);
    }

    @Override
    public void unsubscribe() {
        if(EventBus.getDefault().isRegistered(this)){
            Log.i(TAG, "unsubscribe");
            EventBus.getDefault().unregister(this);
        }
    }
}
