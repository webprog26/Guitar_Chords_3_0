package com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.commands;

import com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.eventbus.events.LoadChordShapesImagesFromLocalDbEvent;
import com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.helpers.ChordShapesTableNameHelper;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by webprog on 10.07.17.
 */

public class LoadChordShapesImagesFromLocalDbCommand implements Command {

    private final String chordTitle;

    public LoadChordShapesImagesFromLocalDbCommand(String chordTitle) {
        this.chordTitle = chordTitle;
    }

    @Override
    public void execute() {
        EventBus.getDefault()
                .post(new LoadChordShapesImagesFromLocalDbEvent(ChordShapesTableNameHelper
                        .getChordShapesTableName(getChordTitle())));
    }

    private String getChordTitle() {
        return chordTitle;
    }
}
