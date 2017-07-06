package com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.mvp.impls;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.util.Log;

import com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.eventbus.EventsHandler;
import com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.eventbus.StartEventsHandler;
import com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.eventbus.events.ReadJSONEvent;
import com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.models.Chord;
import com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.mvp.StartPresenter;
import com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.mvp.StartView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

/**
 * Created by webprog on 06.07.17.
 */

public class StartPresenterImpl implements StartPresenter {

    private static final String TAG = "StartPresenter";

    @Override
    public void onStart() {
        Log.i(TAG, "StartPresenter onStart()");
        getEventsHandler().subscribe();
    }

    @Override
    public void onStop() {
        Log.i(TAG, "StartPresenter onStop()");
       getEventsHandler().unsubscribe();
    }

    @Override
    public void readJSON(final StartView startView) {
        Log.i(TAG, "StartPresenter readJSON()");
        startView.startIntentService();
    }

    @Override
    public void convertJSONtoPOJOs() {

    }

    @Override
    public void addChordsToLocalDb(ArrayList<Chord> chords) {

    }

    @NonNull
    @Override
    public StartEventsHandler getEventsHandler() {
        return new StartEventsHandler(this);
    }

}
