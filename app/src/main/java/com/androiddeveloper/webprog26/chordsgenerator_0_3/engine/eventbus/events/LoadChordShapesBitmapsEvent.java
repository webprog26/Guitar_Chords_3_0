package com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.eventbus.events;

import java.util.ArrayList;

/**
 * Created by webprog on 11.07.17.
 */

public class LoadChordShapesBitmapsEvent {

    private final ArrayList<String> chordShapesImagesTitles;

    public LoadChordShapesBitmapsEvent(ArrayList<String> chordShapesImagesTitles) {
        this.chordShapesImagesTitles = chordShapesImagesTitles;
    }

    public ArrayList<String> getChordShapesImagesTitles() {
        return chordShapesImagesTitles;
    }
}
