package com.androiddeveloper.webprog26.chordsgenerator_0_3;

import android.os.Bundle;
import android.support.annotation.NonNull;

import com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.App;
import com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.constants.Constants;
import com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.dagger.modules.PlayPresenterModule;
import com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.models.CurrentChordAndShapePositionInfoContainer;
import com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.mvp.interfaces.play_screen.PlayPresenter;
import com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.mvp.interfaces.play_screen.PlayView;

import javax.inject.Inject;


public class PlayShapesActivity extends BaseActvity implements PlayView{

    @Inject
    PlayPresenter mPlayPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_shapes);

        getPlayPresenter().setView(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        getPlayPresenter().onStart();
        getPlayPresenter()
                .loadChordShapesFromLocalDataBase(getCurrentChordAndShapePositionInfoContainer()
                        .getChordTitle());
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStop() {
        getPlayPresenter().onStop();
        super.onStop();
    }

    @Override
    protected void setupActivityComponent() {
        App.getAppComponent().plus(new PlayPresenterModule()).inject(this);
    }

    public PlayPresenter getPlayPresenter() {
        return mPlayPresenter;
    }

    @NonNull
    @Override
    public CurrentChordAndShapePositionInfoContainer getCurrentChordAndShapePositionInfoContainer() {
        return (CurrentChordAndShapePositionInfoContainer) getIntent()
                .getSerializableExtra(Constants.CURRENT_CHORD_AND_SHAPE_POSITION_INFO);
    }

    @Override
    public void forcePlayPresenterToshowCurrentChordShape() {
        getPlayPresenter().showCurrentChordShape(getCurrentChordAndShapePositionInfoContainer().getCurrentChordShapePosition());
    }
}
