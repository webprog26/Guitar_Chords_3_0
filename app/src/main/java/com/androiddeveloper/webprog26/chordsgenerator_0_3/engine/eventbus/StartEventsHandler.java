package com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.eventbus;

import android.util.Log;

import com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.eventbus.events.JSONHasBeenReadEvent;
import com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.eventbus.events.ReadJSONEvent;
import com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.mvp.StartPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by webprog on 06.07.17.
 */

public class StartEventsHandler extends EventsHandler {

    private static final String TAG = "StartEventsHandler";

    private final StartPresenter mStartPresenter;

    public StartEventsHandler(StartPresenter startPresenter) {
        this.mStartPresenter = startPresenter;
    }

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

    private StartPresenter getStartPresenter() {
        return mStartPresenter;
    }

    @Subscribe(threadMode = ThreadMode.ASYNC)
    public void onReadJSONEvent(ReadJSONEvent readJSONEvent){

    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onJSONHasBeenReadEvent(JSONHasBeenReadEvent jsonHasBeenReadEvent){
        JSONHasBeenReadEvent readEvent = EventBus.getDefault().removeStickyEvent(JSONHasBeenReadEvent.class);

        if(readEvent != null){
            Log.i(TAG, jsonHasBeenReadEvent.getReadString());
        }
    }
}
