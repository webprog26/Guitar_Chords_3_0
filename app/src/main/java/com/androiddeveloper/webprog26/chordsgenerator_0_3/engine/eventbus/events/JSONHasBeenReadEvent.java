package com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.eventbus.events;

/**
 * Created by webprog on 06.07.17.
 */

public class JSONHasBeenReadEvent {

    private final String mReadString;

    public JSONHasBeenReadEvent(String readString) {
        this.mReadString = readString;
    }

    public String getReadString() {
        return mReadString;
    }
}
