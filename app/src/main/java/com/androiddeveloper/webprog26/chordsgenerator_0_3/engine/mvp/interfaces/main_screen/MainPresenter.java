package com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.mvp.interfaces.main_screen;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;

import com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.eventbus.EventsHandler;
import com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.mvp.interfaces.EventsSubscriber;

import java.util.ArrayList;

/**
 * Created by webprog on 10.07.17.
 */

public interface MainPresenter extends EventsSubscriber{

    void setView(MainView mainView);
    @NonNull
    EventsHandler getEventsHandler();
    void loadCurrentChordShapes(final String chordTitle);
    void notifyMainViewOfNewChordShapesImagesHasBeenLoaded(final ArrayList<Bitmap> chordShapesImages);
    @NonNull
    AssetManager getAssetManager();
}
