package com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.adapters;

import com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.models.ChordShape;

import java.util.ArrayList;

/**
 * Created by webprog on 11.07.17.
 */

public interface DataUpdater {

    void updateData(final ArrayList<ChordShape> chordShapes);
}
