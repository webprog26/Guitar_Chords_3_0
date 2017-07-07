package com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.eventbus.events;

import com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.models.Chord;

import java.util.ArrayList;

/**
 * Created by webprog on 07.07.17.
 */

public class UploadChordsToLocalDbEvent {

    private final ArrayList<Chord> chords;

    public UploadChordsToLocalDbEvent(ArrayList<Chord> chords) {
        this.chords = chords;
    }

    public ArrayList<Chord> getChords() {
        return chords;
    }
}
