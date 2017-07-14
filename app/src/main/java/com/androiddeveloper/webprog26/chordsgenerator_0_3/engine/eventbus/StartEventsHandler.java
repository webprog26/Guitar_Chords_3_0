package com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.eventbus;

import android.util.Log;

import com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.App;
import com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.eventbus.events.ChordsUploadedToDatabaseEvent;
import com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.eventbus.events.JSONHasBeenReadEvent;
import com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.eventbus.events.JSONStringHasBeenConvertedToPOJOsEvent;
import com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.eventbus.events.ReadJSONEvent;
import com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.eventbus.events.SingleChordLoadedToLocalDBEvent;
import com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.eventbus.events.UploadChordsToLocalDbEvent;
import com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.local_db.provider.interfaces.DbProvider;
import com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.models.Chord;
import com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.models.ChordShape;
import com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.mvp.interfaces.EventsSubscriber;
import com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.mvp.interfaces.start_screen.StartPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

import javax.inject.Inject;

/**
 * Created by webprog on 06.07.17.
 */

public class StartEventsHandler extends EventsHandler {

    private static final String TAG = "StartEventsHandler";

    @Inject
    DbProvider dbProvider;

    public StartEventsHandler(StartPresenter startPresenter) {
        super(startPresenter);
        App.getAppComponent().inject(this);
    }

    private StartPresenter getStartPresenter() {
        return (StartPresenter) getEventsSubscriber();
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
        ArrayList<Chord> chords = jsonStringHasBeenConvertedToPOJOsEvent.getChords();
        for(Chord chord: chords){

            for(ChordShape chordShape: chord.getChordShapes()){
                Log.i(TAG, chordShape.toString());
            }
        }

        if(chords != null && chords.size() > 0){
            getStartPresenter().notifyStartViewOfChordsUploadingToDbStarted();
            getStartPresenter().notifyStartViewOfChordsToBeUploadedCount(chords.size());
            getStartPresenter().addChordsToLocalDb(chords);
        }

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onSingleChordLoadedToLocalDBEvent(SingleChordLoadedToLocalDBEvent singleChordLoadedToLocalDBEvent){
        Log.i(TAG, singleChordLoadedToLocalDBEvent.getChordTitle() + " added to local db");
        getStartPresenter().notifyStartViewOfSingleChordHasBeenUploadedToDb(singleChordLoadedToLocalDBEvent.getChordTitle());
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onChordsUploadedToDatabaseEvent(ChordsUploadedToDatabaseEvent chordsUploadedToDatabaseEvent){
        Log.i(TAG, "All the chords uploaded to local db");
        getStartPresenter().notifyStartViewOfAllTheChordsWereUploadedToDb();
    }

    @Subscribe(threadMode = ThreadMode.ASYNC)
    public void onUploadChordsToLocalDbEvent(UploadChordsToLocalDbEvent uploadChordsToLocalDbEvent){
        try {
            getDbProvider().insertChordsToDb(uploadChordsToLocalDbEvent.getChords());
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private DbProvider getDbProvider() {
        return dbProvider;
    }

}
