package com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.eventbus.events;

import com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.models.Chord;

import java.util.ArrayList;

/**
 * Created by webprog on 07.07.17.
 */

public class JSONStringHasBeenConvertedToPOJOsEvent {

    private final ArrayList<Chord> mChords;

    public JSONStringHasBeenConvertedToPOJOsEvent(ArrayList<Chord> chords) {
        this.mChords = chords;
    }

    public ArrayList<Chord> getChords() {
        return mChords;
    }
}
