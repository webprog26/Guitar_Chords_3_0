package com.androiddeveloper.webprog26.chordsgenerator_0_3;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.KeyEvent;

import com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.App;
import com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.constants.Constants;
import com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.dagger.modules.PlayPresenterModule;
import com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.models.CurrentChordAndShapePositionInfoContainer;
import com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.mvp.interfaces.play_screen.PlayPresenter;
import com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.mvp.interfaces.play_screen.PlayView;

import javax.inject.Inject;


public class PlayShapesActivity extends BaseActvity implements PlayView{

    private static final String TAG = "PlayView";

    @Inject
    PlayPresenter mPlayPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_shapes);

        getPlayPresenter().setView(this);
        getPlayPresenter().setEventsHandler();
    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onResume() {
        super.onResume();
        getPlayPresenter().onStart();
            Log.i(TAG, "onResume");
            getPlayPresenter()
                    .loadChordShapesFromLocalDataBase(
                            ((CurrentChordAndShapePositionInfoContainer) getIntent()
                    .getSerializableExtra(Constants.CURRENT_CHORD_AND_SHAPE_POSITION_INFO))
                            .getChordTitle());

    }

    @Override
    protected void onStop() {

        super.onStop();
    }

    @Override
    protected void onPause() {
        super.onPause();
        getPlayPresenter().onStop();
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
        Log.i(TAG, "getCurrentChordAndShapePositionInfoContainer");
        return (CurrentChordAndShapePositionInfoContainer) getIntent()
                .getSerializableExtra(Constants.CURRENT_CHORD_AND_SHAPE_POSITION_INFO);
    }

    @Override
    public void forcePlayPresenterToShowCurrentChordShape() {
        Log.i(TAG, "forcePlayPresenterToShowCurrentChordShape");
        getPlayPresenter().showCurrentChordShape(
                ((CurrentChordAndShapePositionInfoContainer) getIntent()
                        .getSerializableExtra(Constants.CURRENT_CHORD_AND_SHAPE_POSITION_INFO))
                        .getCurrentChordShapePosition());
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getRepeatCount() == 0) {
            event.startTracking();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.isTracking()
                && !event.isCanceled()) {
            finish();
            return true;
        }
        return super.onKeyUp(keyCode, event);
    }


    @Override
    public Intent getIntent() {
        Log.i(TAG, "getIntent");
        return super.getIntent();
    }
}
