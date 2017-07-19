package com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.models;

import android.support.annotation.NonNull;

import com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.models.ChordShape;

/**
 * Created by webprog on 14.07.17.
 */

public interface LoadedChordShapesHolder {

    void addChordShape(final ChordShape chordShape);
    @NonNull
    ChordShape getChordShape(final int position);
    void clearChordShapesList();
    int getSize();
}
