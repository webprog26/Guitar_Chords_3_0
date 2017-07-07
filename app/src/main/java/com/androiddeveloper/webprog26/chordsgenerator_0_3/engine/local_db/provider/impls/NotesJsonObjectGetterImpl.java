package com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.local_db.provider.impls;

import com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.local_db.provider.interfaces.NotesJsonObjectGetter;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by webprog on 07.07.17.
 */

public class NotesJsonObjectGetterImpl implements NotesJsonObjectGetter {

    @Override
    public JSONObject getJSONNotesObject(String jsonNotesString) {
        JSONObject jsonNotes = null;
        try {
            jsonNotes = new JSONObject(jsonNotesString);
        } catch (JSONException e){
            e.printStackTrace();
        }
        return jsonNotes;
    }
}


