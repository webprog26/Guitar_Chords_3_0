package com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.models;

import java.util.ArrayList;

/**
 * Created by webprog on 14.07.17.
 */

public class LoadedChordShapesHolder {

    private final ArrayList<ChordShape> mChordShapes = new ArrayList<>();

    public LoadedChordShapesHolder() {}

    public void addChordShape(final ChordShape chordShape){
        getChordShapes().add(chordShape);
    }

    public ChordShape getChordShape(final int position){
        return getChordShapes().get(position);
    }

    private ArrayList<ChordShape> getChordShapes() {
        return mChordShapes;
    }
}
