package com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.commands;

import com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.eventbus.events.LoadFullChordShapesFromLocalDbEvent;
import com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.helpers.ChordShapesTableNameHelper;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by webprog on 14.07.17.
 */

public class LoadFullChordShapesFromLocalDbCommand implements Command{

    private final String chordTitle;

    public LoadFullChordShapesFromLocalDbCommand(String chordTitle) {
        this.chordTitle = chordTitle;
    }

    @Override
    public void execute() {
        EventBus.getDefault().post(new LoadFullChordShapesFromLocalDbEvent(ChordShapesTableNameHelper
                .getChordShapesTableName(getChordTitle())));
    }

    private String getChordTitle() {
        return chordTitle;
    }
}
