package com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.mvp.interfaces.play_screen;

import android.support.annotation.NonNull;

import com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.eventbus.EventsHandler;
import com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.models.LoadedChordShapesHolderImpl;
import com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.mvp.interfaces.EventsSubscriber;

/**
 * Created by webprog on 14.07.17.
 */

public interface PlayPresenter extends EventsSubscriber{

    void setView (PlayView playView);
    @NonNull
    EventsHandler getEventsHandler();
    @NonNull
    LoadedChordShapesHolderImpl getLoadedChordShapesHolder();
    void notifyPlayViewOfFullChordShapesHaveBeenLoaded();
    void clearLoadedChordShapesHolder();

    void loadChordShapesFromLocalDataBase(final String chordTitle);
    void showCurrentChordShape(final int currentShapePosition);
}
