package com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.local_db.provider.impls;

import android.util.Log;

import com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.local_db.provider.interfaces.NotesArrayToStringConverter;
import com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.models.Note;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.constants.Constants.*;

/**
 * Created by webprog on 07.07.17.
 */

public class NotesArrayToStringConverterImpl implements NotesArrayToStringConverter {

    @Override
    public String getStringConverted(ArrayList<Note> notes) {
        JSONObject json = new JSONObject();
        JSONArray jsonArray = new JSONArray();

        for(Note note: notes){
            JSONObject singleNoteJsonObject = new JSONObject();

            try {
                singleNoteJsonObject.put(SHAPE_NOTE_TITLE, note.getNoteTitle());
                singleNoteJsonObject.put(SHAPE_NOTE_FRET, note.getNoteFret());
                singleNoteJsonObject.put(SHAPE_NOTE_FINGER_INDEX, note.getNoteFingerIndex());
                singleNoteJsonObject.put(SHAPE_NOTE_PLACE, note.getNotePlace());
                singleNoteJsonObject.put(SHAPE_NOTE_SOUND_PATH, note.getNoteSoundPath());
            } catch (JSONException e){
                e.printStackTrace();
            }

            jsonArray.put(singleNoteJsonObject);

        }

        try {
            json.put(SHAPE_NOTES, jsonArray);

        } catch (JSONException e){
            e.printStackTrace();
        }

        return json.toString();
    }
}
