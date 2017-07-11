package com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.mvp.impls;

import android.support.annotation.NonNull;

import com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.commands.LoadChordShapesFromLocalDbCommand;
import com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.eventbus.MainEventsHandler;
import com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.models.ChordShape;
import com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.mvp.interfaces.main_screen.MainPresenter;
import com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.mvp.interfaces.main_screen.MainView;

import java.util.ArrayList;

/**
 * Created by webprog on 10.07.17.
 */

public class MainPresenterImpl implements MainPresenter {

    private MainView mainView;

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
        new LoadChordShapesFromLocalDbCommand(chordTitle).execute();
    }

    @Override
    public void notifyMainViewOfNewChordShapesHasBeenLoaded(ArrayList<ChordShape> chordShapes) {
        getMainView().updateRecyclerViewAdapterWithNewChordShapes(chordShapes);
    }

    @NonNull
    @Override
    public MainEventsHandler getEventsHandler() {
        return new MainEventsHandler(this);
    }

    private MainView getMainView() {
        return mainView;
    }
}
