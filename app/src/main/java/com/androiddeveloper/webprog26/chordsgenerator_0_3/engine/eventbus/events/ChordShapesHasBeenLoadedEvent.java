package com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.eventbus.events;

import com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.models.ChordShape;

import java.util.ArrayList;

/**
 * Created by webprog on 11.07.17.
 */

public class ChordShapesHasBeenLoadedEvent {

    final ArrayList<ChordShape> chordShapes;

    public ChordShapesHasBeenLoadedEvent(ArrayList<ChordShape> chordShapes) {
        this.chordShapes = chordShapes;
    }

    public ArrayList<ChordShape> getChordShapes() {
        return chordShapes;
    }
}
