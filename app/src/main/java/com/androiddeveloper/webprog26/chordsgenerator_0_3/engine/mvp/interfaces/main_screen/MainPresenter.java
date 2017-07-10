package com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.mvp.interfaces.main_screen;

import android.support.annotation.NonNull;

import com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.eventbus.MainEventsHandler;
import com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.mvp.interfaces.EventsSubscriber;

/**
 * Created by webprog on 10.07.17.
 */

public interface MainPresenter extends EventsSubscriber{

    void setView(MainView mainView);
    @NonNull
    MainEventsHandler getEventsHandler();
    void loadCurrentChordShapes(final String chordTitle);
}
