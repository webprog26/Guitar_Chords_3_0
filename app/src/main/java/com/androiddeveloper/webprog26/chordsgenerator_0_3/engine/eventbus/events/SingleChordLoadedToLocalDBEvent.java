package com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.eventbus.events;

/**
 * Created by webprog on 07.07.17.
 */

public class SingleChordLoadedToLocalDBEvent {

    private final String chordTitle;

    public SingleChordLoadedToLocalDBEvent(String chordTitle) {
        this.chordTitle = chordTitle;
    }

    public String getChordTitle() {
        return chordTitle;
    }
}
