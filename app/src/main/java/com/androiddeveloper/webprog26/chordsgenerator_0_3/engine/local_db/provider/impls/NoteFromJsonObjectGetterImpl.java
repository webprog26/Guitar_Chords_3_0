package com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.local_db.provider.impls;

import com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.local_db.provider.interfaces.NoteFromJsonObjectGetter;
import com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.models.Note;

import org.json.JSONException;
import org.json.JSONObject;

import static com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.constants.Constants.*;

/**
 * Created by webprog on 07.07.17.
 */

public class NoteFromJsonObjectGetterImpl implements NoteFromJsonObjectGetter {

    @Override
    public Note getNoteFromJSONObject(JSONObject noteJsonObject) {
        Note note = null;
        if(noteJsonObject != null){
            try {
                note = new Note(
                        noteJsonObject.getString(SHAPE_NOTE_TITLE),
                        noteJsonObject.getInt(SHAPE_NOTE_FRET),
                        noteJsonObject.getInt(SHAPE_NOTE_FINGER_INDEX),
                        noteJsonObject.getInt(SHAPE_NOTE_PLACE),
                        noteJsonObject.getString(SHAPE_NOTE_SOUND_PATH)
                );
            } catch (JSONException e){
                e.printStackTrace();
            }
        }
        return note;
    }
}
