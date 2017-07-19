package com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.mvp.impls;

import android.support.annotation.NonNull;
import android.util.Log;

import com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.commands.ConvertJSONStringToPOJOsCommand;
import com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.commands.UploadChordsToLocalDbCommand;
import com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.constants.Constants;
import com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.eventbus.EventsHandler;
import com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.eventbus.StartEventsHandler;
import com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.models.Chord;
import com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.mvp.interfaces.start_screen.StartPresenter;
import com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.mvp.interfaces.start_screen.StartView;
import java.util.ArrayList;


/**
 * Created by webprog on 06.07.17.
 */

public class StartPresenterImpl implements StartPresenter {

    private static final String TAG = "StartPresenter";

    private StartView mStartView;

    private StartEventsHandler startEventsHandler;

    @Override
    public void setEventsHandler() {
        this.startEventsHandler = new StartEventsHandler(this);
    }

    @Override
    public void setView(StartView startView) {
        this.mStartView = startView;
    }

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
    public void setSharedPreferencesMarkerJSONStringHasBeenRead() {
        StartView startView = getStartView();

        if(startView != null){
            startView.getSharedPreferences()
                    .edit()
                    .putBoolean(Constants.JSON_STRING_HAS_BEEN_READ_MARKER, true)
                    .apply();
        }
    }

    @Override
    public void convertJSONtoPOJOs(final String jsonString) {
        new ConvertJSONStringToPOJOsCommand(jsonString).execute();
    }

    @Override
    public void addChordsToLocalDb(ArrayList<Chord> chords) {
        Log.i(TAG, "StartPresenter addChordsToLocalDb(ArrayList<Chord> chords)");
        new UploadChordsToLocalDbCommand(chords).execute();
    }

    @NonNull
    @Override
    public EventsHandler getEventsHandler() {
        return startEventsHandler;
    }

    @Override
    public void notifyStartViewOfChordsUploadingToDbStarted() {
        getStartView().initInflatedView();
    }

    @Override
    public void notifyStartViewOfSingleChordHasBeenUploadedToDb(final String unpackedChordTitle) {
        getStartView().updateUnpackingChordsText(unpackedChordTitle);
        getStartView().increaseChordsUnPackingProgressBarProgress();

    }

    @Override
    public void notifyStartViewOfAllTheChordsWereUploadedToDb() {
        getStartView().getSharedPreferences().edit().putBoolean(Constants.CHORDS_WERE_UPLOADED_TO_DB_NARKER, true).apply();
        getStartView().setStartButtonEnabledAfterChordsWereUploadedToDb();
    }

    @Override
    public void notifyStartViewOfChordsToBeUploadedCount(int count) {
        Log.i(TAG, "notifyStartViewOfChordsToBeUploadedCount");
        getStartView().initChordsUnpackingProgressBarMax(count);
    }

    private StartView getStartView() {
        return mStartView;
    }

}
