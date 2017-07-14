package com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.mvp.impls;

import android.support.annotation.NonNull;
import android.util.Log;

import com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.App;
import com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.commands.LoadFullChordShapesFromLocalDbCommand;
import com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.dagger.modules.LoadedChordShapesHolderModule;
import com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.eventbus.PlayEventsHandler;
import com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.models.ChordShape;
import com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.models.LoadedChordShapesHolder;
import com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.mvp.interfaces.play_screen.PlayPresenter;
import com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.mvp.interfaces.play_screen.PlayView;

import javax.inject.Inject;

/**
 * Created by webprog on 14.07.17.
 */

public class PlayPresenterImpl implements PlayPresenter {

    private static final String TAG = "PlayPresenter";

    private PlayView mPlayView;

    @Inject
    LoadedChordShapesHolder mLoadedChordShapesHolder;

    public PlayPresenterImpl() {
        App.getAppComponent().plus(new LoadedChordShapesHolderModule()).inject(this);
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
    public void setView(PlayView playView) {
        this.mPlayView = playView;
    }

    @NonNull
    @Override
    public PlayEventsHandler getEventsHandler() {
        return new PlayEventsHandler(this);
    }

    @Override
    public void loadChordShapesFromLocalDataBase(String chordTitle) {

        if(chordTitle != null){
            new LoadFullChordShapesFromLocalDbCommand(chordTitle).execute();
        }
    }

    @Override
    public void showCurrentChordShape(int currentShapePosition) {
        final ChordShape currentChordShape = getLoadedChordShapesHolder().getChordShape(currentShapePosition);

        if(currentChordShape != null){
            Log.i(TAG, "currentChordShape: \n" + currentChordShape.toString());
        } else {
            Log.i(TAG, "currentChordShape is null");
        }
    }

    private PlayView getPlayView() {
        return mPlayView;
    }

    @NonNull
    @Override
    public LoadedChordShapesHolder getLoadedChordShapesHolder() {
        return mLoadedChordShapesHolder;
    }

    @Override
    public void notifyPlayViewOfFullChordShapesHaveBeenLoaded() {
        getPlayView().forcePlayPresenterToshowCurrentChordShape();
    }
}
