package com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.commands;

import com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.eventbus.events.JSONStringHasBeenConvertedToPOJOsEvent;
import com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.helpers.JSONToPOJOsConverter;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by webprog on 07.07.17.
 */

public class ConvertJSONStringToPOJOsCommand implements Command {

    private final String jsonString;

    public ConvertJSONStringToPOJOsCommand(String jsonString) {
        this.jsonString = jsonString;
    }

    @Override
    public void execute() {
        EventBus.getDefault()
                .postSticky(new JSONStringHasBeenConvertedToPOJOsEvent(JSONToPOJOsConverter
                                                                        .getChordArrayList(getJsonString())));
    }

    private String getJsonString() {
        return jsonString;
    }
}
