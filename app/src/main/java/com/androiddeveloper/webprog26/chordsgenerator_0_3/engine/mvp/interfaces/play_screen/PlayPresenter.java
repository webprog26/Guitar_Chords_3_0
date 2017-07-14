package com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.mvp.interfaces.play_screen;

import android.support.annotation.NonNull;

import com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.eventbus.PlayEventsHandler;
import com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.models.LoadedChordShapesHolder;
import com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.mvp.interfaces.EventsSubscriber;

/**
 * Created by webprog on 14.07.17.
 */

public interface PlayPresenter extends EventsSubscriber{

    void setView (PlayView playView);
    @NonNull
    PlayEventsHandler getEventsHandler();
    @NonNull
    LoadedChordShapesHolder getLoadedChordShapesHolder();
    void notifyPlayViewOfFullChordShapesHaveBeenLoaded();

    void loadChordShapesFromLocalDataBase(final String chordTitle);
    void showCurrentChordShape(final int currentShapePosition);
}
