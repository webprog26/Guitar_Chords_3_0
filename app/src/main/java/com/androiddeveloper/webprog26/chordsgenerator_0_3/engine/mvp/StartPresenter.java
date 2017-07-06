package com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.mvp;

import android.content.Intent;
import android.content.res.AssetManager;
import android.support.annotation.NonNull;

import com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.eventbus.EventsHandler;
import com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.eventbus.StartEventsHandler;
import com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.models.Chord;

import java.util.ArrayList;

/**
 * Created by webprog on 06.07.17.
 */

public interface StartPresenter {

    void onStart();
    void onStop();
    void readJSON(final StartView startView);
    void convertJSONtoPOJOs();
    void addChordsToLocalDb(ArrayList<Chord> chords);
    @NonNull
    StartEventsHandler getEventsHandler();
}
