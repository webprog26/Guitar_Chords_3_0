package com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.local_db.provider.interfaces;

import com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.models.Note;

import org.json.JSONObject;

/**
 * Created by webprog on 07.07.17.
 */

public interface NoteFromJsonObjectGetter {

    Note getNoteFromJSONObject(JSONObject noteJsonObject);
}
