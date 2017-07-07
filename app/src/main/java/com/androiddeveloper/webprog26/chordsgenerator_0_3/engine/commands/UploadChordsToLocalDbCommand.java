package com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.commands;

import com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.eventbus.events.UploadChordsToLocalDbEvent;
import com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.models.Chord;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

/**
 * Created by webprog on 07.07.17.
 */

public class UploadChordsToLocalDbCommand implements Command {

    private final ArrayList<Chord> chords;

    public UploadChordsToLocalDbCommand(ArrayList<Chord> chords) {
        this.chords = chords;
    }

    @Override
    public void execute() {
        EventBus.getDefault().post(new UploadChordsToLocalDbEvent(getChords()));
    }

    private ArrayList<Chord> getChords() {
        return chords;
    }
}
