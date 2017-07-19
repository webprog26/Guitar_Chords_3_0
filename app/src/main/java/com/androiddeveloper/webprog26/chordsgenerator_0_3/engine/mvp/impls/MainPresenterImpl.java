package com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.mvp.impls;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;

import com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.commands.LoadChordShapesImagesFromLocalDbCommand;
import com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.eventbus.EventsHandler;
import com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.eventbus.MainEventsHandler;
import com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.mvp.interfaces.main_screen.MainPresenter;
import com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.mvp.interfaces.main_screen.MainView;

import java.util.ArrayList;

/**
 * Created by webprog on 10.07.17.
 */

public class MainPresenterImpl implements MainPresenter {

    private MainView mainView;
    private EventsHandler mainEventsHandler;

    @Override
    public void setEventsHandler() {
        this.mainEventsHandler = new MainEventsHandler(this);
    }

    @Override
    public void onStart() {
        getEventsHandler().subscribe();
    }

    @Override
    public void onStop() {
        getEventsHandler().unsubscribe();
    }

    @Override
    public void setView(MainView mainView) {
        this.mainView = mainView;
    }

    @Override
    public void loadCurrentChordShapes(String chordTitle) {
        new LoadChordShapesImagesFromLocalDbCommand(chordTitle).execute();
    }

    @Override
    public void notifyMainViewOfNewChordShapesImagesHasBeenLoaded(ArrayList<Bitmap> chordShapesImages) {
        getMainView().updateRecyclerViewAdapterWithNewChordShapesImages(chordShapesImages);
    }

    @NonNull
    @Override
    public AssetManager getAssetManager() {
        return getMainView().getAssetManager();
    }

    @NonNull
    @Override
    public EventsHandler getEventsHandler() {
        return mainEventsHandler;
    }

    private MainView getMainView() {
        return mainView;
    }
}
