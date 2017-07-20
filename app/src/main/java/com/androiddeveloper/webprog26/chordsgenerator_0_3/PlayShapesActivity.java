package com.androiddeveloper.webprog26.chordsgenerator_0_3;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;

import com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.App;
import com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.constants.Constants;
import com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.dagger.modules.PlayPresenterModule;
import com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.models.CurrentChordAndShapePositionInfoContainer;
import com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.mvp.interfaces.play_screen.PlayPresenter;
import com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.mvp.interfaces.play_screen.PlayView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;


public class PlayShapesActivity extends BaseActvity implements PlayView{

    private static final String TAG = "PlayView";

    @Inject
    PlayPresenter mPlayPresenter;

    @BindView(R.id.btn_next)
    Button mBtnNextChordShape;

    @BindView(R.id.btn_previous)
    Button mBtnPreviousChordShape;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_shapes);

        ButterKnife.bind(this);

        getPlayPresenter().setView(this);
        getPlayPresenter().setEventsHandler();

        getBtnNextChordShape().setOnClickListener(this);
        getBtnPreviousChordShape().setOnClickListener(this);
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
    public void setNextChordShapeButtonEnabled(boolean enabled) {
        getBtnNextChordShape().setEnabled(enabled);
    }

    @Override
    public void setPreviousChordShapeButtonEnabled(boolean enabled) {
        getBtnPreviousChordShape().setEnabled(enabled);
    }

    private Button getBtnNextChordShape() {
        return mBtnNextChordShape;
    }

    private Button getBtnPreviousChordShape() {
        return mBtnPreviousChordShape;
    }

    @Override
    public void onClick(View view) {

        final PlayPresenter playPresenter = getPlayPresenter();
        final int currentChordShapePosition = playPresenter
                .getLoadedChordShapesHolder()
                .getCurrentChordShapePosition();


        if(currentChordShapePosition == Constants.UNAVAILABLE_CHORD_SHAPE_POSITION){

            return;

        }

        switch (view.getId()){

            case R.id.btn_next:

                playPresenter.showCurrentChordShape(currentChordShapePosition + 1);

                break;

            case R.id.btn_previous:

                playPresenter.showCurrentChordShape(currentChordShapePosition - 1);

                break;
        }
    }
}
