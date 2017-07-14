package com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.models;

import java.io.Serializable;

/**
 * Created by webprog on 14.07.17.
 */

public class CurrentChordAndShapePositionInfoContainer implements Serializable {

    private final String mChordTitle;
    private final int mCurrentChordShapePosition;

    public CurrentChordAndShapePositionInfoContainer(String chordTitle, int currentChordShapePosition) {
        this.mChordTitle = chordTitle;
        this.mCurrentChordShapePosition = currentChordShapePosition;
    }

    public String getChordTitle() {
        return mChordTitle;
    }

    public int getCurrentChordShapePosition() {
        return mCurrentChordShapePosition;
    }
}
