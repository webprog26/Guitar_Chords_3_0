package com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.mvp.interfaces.start_screen;

import android.support.annotation.NonNull;

import com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.eventbus.EventsHandler;
import com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.models.Chord;
import com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.mvp.interfaces.EventsSubscriber;

import java.util.ArrayList;

/**
 * Created by webprog on 06.07.17.
 */

public interface StartPresenter extends EventsSubscriber{

    void setView(StartView startView);
    void readJSON(final StartView startView);
    void setSharedPreferencesMarkerJSONStringHasBeenRead();
    void convertJSONtoPOJOs(final String jsonString);
    void notifyStartViewOfChordsUploadingToDbStarted();
    void notifyStartViewOfSingleChordHasBeenUploadedToDb(final String unpackedChordTitle);
    void notifyStartViewOfAllTheChordsWereUploadedToDb();
    void notifyStartViewOfChordsToBeUploadedCount(final int count);
    void addChordsToLocalDb(ArrayList<Chord> chords);
    @NonNull
    EventsHandler getEventsHandler();
}
