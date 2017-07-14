package com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.mvp.interfaces.play_screen;

import android.support.annotation.NonNull;

import com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.models.CurrentChordAndShapePositionInfoContainer;

/**
 * Created by webprog on 14.07.17.
 */

public interface PlayView {

    @NonNull
    CurrentChordAndShapePositionInfoContainer getCurrentChordAndShapePositionInfoContainer();
    void forcePlayPresenterToshowCurrentChordShape();
}
