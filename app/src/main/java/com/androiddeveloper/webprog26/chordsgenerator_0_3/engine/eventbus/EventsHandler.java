package com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.eventbus;

import android.util.Log;

import com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.mvp.interfaces.EventsSubscriber;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by webprog on 06.07.17.
 */

public abstract class EventsHandler implements Subscriber{

    private static final String TAG = "EventsHandler";

    private final EventsSubscriber mEventsSubscriber;

    public EventsHandler(EventsSubscriber eventsSubscriber) {
        this.mEventsSubscriber = eventsSubscriber;
    }

    protected EventsSubscriber getEventsSubscriber() {
        return mEventsSubscriber;
    }

    @Override
    public void subscribe() {

        if(!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

    @Override
    public void unsubscribe() {
        if(EventBus.getDefault().isRegistered(this)){
            Log.i(TAG, "unsubscribe");
            EventBus.getDefault().unregister(this);
        }
    }
}
