package com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.models;

/**
 * Created by webprog on 06.07.17.
 */

public class MutedStringsHolder {

    private final boolean isFirstStringMuted;
    private final boolean isSecondStringMuted;
    private final boolean isThirdStringMuted;
    private final boolean isFourthStringMuted;
    private final boolean isFifthStringMuted;
    private final boolean isSixthStringMuted;

    public MutedStringsHolder(boolean isFirstStringMuted,
                              boolean isSecondStringMuted,
                              boolean isThirdStringMuted,
                              boolean isFourthStringMuted,
                              boolean isFifthStringMuted,
                              boolean isSixthStringMuted) {
        this.isFirstStringMuted = isFirstStringMuted;
        this.isSecondStringMuted = isSecondStringMuted;
        this.isThirdStringMuted = isThirdStringMuted;
        this.isFourthStringMuted = isFourthStringMuted;
        this.isFifthStringMuted = isFifthStringMuted;
        this.isSixthStringMuted = isSixthStringMuted;
    }

    public boolean isFirstStringMuted() {
        return isFirstStringMuted;
    }

    public boolean isSecondStringMuted() {
        return isSecondStringMuted;
    }

    public boolean isThirdStringMuted() {
        return isThirdStringMuted;
    }

    public boolean isFourthStringMuted() {
        return isFourthStringMuted;
    }

    public boolean isFifthStringMuted() {
        return isFifthStringMuted;
    }

    public boolean isSixthStringMuted() {
        return isSixthStringMuted;
    }
}
