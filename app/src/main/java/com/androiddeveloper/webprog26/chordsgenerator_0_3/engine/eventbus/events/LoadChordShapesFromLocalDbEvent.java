package com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.eventbus.events;

/**
 * Created by webprog on 10.07.17.
 */

public class LoadChordShapesFromLocalDbEvent {

    private final String mChordShapesTableName;

    public LoadChordShapesFromLocalDbEvent(String chordShapesTableName) {
        this.mChordShapesTableName = chordShapesTableName;
    }

    public String getChordShapesTableName() {
        return mChordShapesTableName;
    }
}
