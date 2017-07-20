package com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.models;

import android.support.annotation.NonNull;

import com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.constants.Constants;

import java.util.ArrayList;

/**
 * Created by webprog on 14.07.17.
 */

public class LoadedChordShapesHolderImpl implements LoadedChordShapesHolder{

    private final ArrayList<ChordShape> mChordShapes = new ArrayList<>();
    private int mCurrentChordShapePosition = Constants.UNAVAILABLE_CHORD_SHAPE_POSITION;

    @Override
    public void addChordShape(ChordShape chordShape) {
        getChordShapes().add(chordShape);
    }

    @NonNull
    @Override
    public ChordShape getChordShape(int position) {
        setCurrentChordShapePosition(position);
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

    private void setCurrentChordShapePosition(int currentChordShapePosition) {
        this.mCurrentChordShapePosition = currentChordShapePosition;
    }

    @Override
    public int getCurrentChordShapePosition() {
        return mCurrentChordShapePosition;
    }
}
