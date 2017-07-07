package com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.eventbus;

import android.util.Log;

import com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.eventbus.events.JSONHasBeenReadEvent;
import com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.eventbus.events.JSONStringHasBeenConvertedToPOJOsEvent;
import com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.eventbus.events.ReadJSONEvent;
import com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.models.Chord;
import com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.models.ChordShape;
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
            final String jsonString = jsonHasBeenReadEvent.getReadString();

            if(jsonString != null){

                Log.i(TAG, jsonString);
                getStartPresenter().setSharedPreferencesMarkerJSONStringHasBeenRead();

                getStartPresenter().convertJSONtoPOJOs(jsonString);
            }
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onJSONStringHasBeenConvertedToPOJOsEvent(JSONStringHasBeenConvertedToPOJOsEvent jsonStringHasBeenConvertedToPOJOsEvent){

        for(Chord chord: jsonStringHasBeenConvertedToPOJOsEvent.getChords()){

            for(ChordShape chordShape: chord.getChordShapes()){
                Log.i(TAG, chordShape.toString());
            }
        }

    }
}
