package com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.models;

import android.support.annotation.NonNull;

import java.util.ArrayList;

/**
 * Created by webprog on 14.07.17.
 */

public class LoadedChordShapesHolderImpl implements LoadedChordShapesHolder{

    private final ArrayList<ChordShape> mChordShapes = new ArrayList<>();

    @Override
    public void addChordShape(ChordShape chordShape) {
        getChordShapes().add(chordShape);
    }

    @NonNull
    @Override
    public ChordShape getChordShape(int position) {
        return getChordShapes().get(position);
    }

    @Override
    public void clearChordShapesList() {
        getChordShapes().clear();
    }

    private ArrayList<ChordShape> getChordShapes() {
        return mChordShapes;
    }

    @Override
    public int getSize() {
        return getChordShapes().size();
    }
}
