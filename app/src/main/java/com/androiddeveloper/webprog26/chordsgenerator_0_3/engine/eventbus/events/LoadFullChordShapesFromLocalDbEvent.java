package com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.eventbus.events;

/**
 * Created by webprog on 14.07.17.
 */

public class LoadFullChordShapesFromLocalDbEvent {

    private final String mChordShapesTableName;

    public LoadFullChordShapesFromLocalDbEvent(String chordShapesTableName) {
        this.mChordShapesTableName = chordShapesTableName;
    }

    public String getChordShapesTableName() {
        return mChordShapesTableName;
    }
}
