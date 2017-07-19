package com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.eventbus.events;

/**
 * Created by webprog on 10.07.17.
 */

public class LoadChordShapesImagesFromLocalDbEvent {

    private final String mChordShapesTableName;

    public LoadChordShapesImagesFromLocalDbEvent(String chordShapesTableName) {
        this.mChordShapesTableName = chordShapesTableName;
    }

    public String getChordShapesTableName() {
        return mChordShapesTableName;
    }
}
