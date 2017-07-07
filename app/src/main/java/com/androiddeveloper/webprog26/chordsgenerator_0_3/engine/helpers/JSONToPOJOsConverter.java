package com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.helpers;

import android.util.Log;

import com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.models.Chord;
import com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.models.ChordShape;
import com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.models.MutedStringsHolder;
import com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.models.Note;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by webprog on 07.07.17.
 */

public class JSONToPOJOsConverter {

    private static final String TAG = "JSONToPOJOsConverter";

    //Chord fields titles
    private static final String CHORDS_ROOT = "chords";
    private static final String CHORD_TITLE = "chord_title";
    private static final String CHORD_SECOND_TITLE = "chord_second_title";
    private static final String CHORD_TYPE = "chord_type";
    private static final String CHORD_ALTERATION = "chord_alteration";

    private static final String CHORD_SHAPES = "chord_shapes";

    //Shape fields titles
    private static final String SHAPE_POSITION = "shape_position";
    private static final String SHAPE_START_FRET_POSITION = "shape_start_fret_position";
    private static final String SHAPE_IMAGE_TITLE = "shape_image_title";
    private static final String SHAPE_NOTES = "shape_notes";

    private static final String HAS_BAR = "has_bar";
    private static final String START_BAR_PLACE = "start_bar_place";
    private static final String END_BAR_PLACE = "end_bar_place";

    private static final String HAS_MUTED_STRINGS = "has_muted_strings";
    private static final String SIXTH_STRING_MUTED = "sixth_string_muted";
    private static final String FIFTH_STRING_MUTED = "fifth_string_muted";
    private static final String FOURTH_STRING_MUTED = "fourth_string_muted";
    private static final String THIRD_STRING_MUTED = "third_string_muted";
    private static final String SECOND_STRING_MUTED = "second_string_muted";
    private static final String FIRST_STRING_MUTED = "first_string_muted";

    //Note fields titles
    private static final String NOTE_TITLE = "note_title";
    private static final String NOTE_FRET = "note_fret";
    private static final String NOTE_FINGER_INDEX = "note_finger_index";
    private static final String NOTE_PLACE = "note_place";
    private static final String NOTE_SOUND_PATH = "note_sound_path";


    public static ArrayList<Chord> getChordArrayList(final String jsonString){
        Log.i(TAG, "transformDataToPOJOs()");
        ArrayList<Chord> chords = new ArrayList<>();

        if(jsonString != null){
            JSONObject chordsJsonObject = null;

            try {
                chordsJsonObject = new JSONObject(jsonString);
            } catch (JSONException jse){
                jse.printStackTrace();
            }

            if(chordsJsonObject != null){
                JSONArray chordsJsonArray = null;

                try {
                    chordsJsonArray = chordsJsonObject.getJSONArray(CHORDS_ROOT);
                } catch (JSONException jse){
                    jse.printStackTrace();
                }

                if(chordsJsonArray != null){
                    JSONObject singleChordsJsonObject;

                    try {
                        for(int i = 0; i < chordsJsonArray.length(); i++){
                            singleChordsJsonObject = chordsJsonArray.getJSONObject(i);

                            Chord chord = null;


                            if(singleChordsJsonObject != null){
                                JSONArray chordShapesJsonArray = singleChordsJsonObject.getJSONArray(CHORD_SHAPES);

                                ArrayList<ChordShape> chordShapes = new ArrayList<>();

                                for(int j = 0; j < chordShapesJsonArray.length(); j++){

                                    JSONObject chordShapeJsonObject = chordShapesJsonArray.getJSONObject(j);

                                    ChordShape chordShape;

                                    JSONArray shapeNotesJsonArray = chordShapeJsonObject.getJSONArray(SHAPE_NOTES);
                                    ArrayList<Note> notes = new ArrayList<>();

                                    for(int k = 0; k < shapeNotesJsonArray.length(); k++){
                                        JSONObject noteJsonObject = shapeNotesJsonArray.getJSONObject(k);
                                        Note note = new Note(
                                                noteJsonObject.getString(NOTE_TITLE),
                                                noteJsonObject.getInt(NOTE_FRET),
                                                noteJsonObject.getInt(NOTE_FINGER_INDEX),
                                                noteJsonObject.getInt(NOTE_PLACE),
                                                noteJsonObject.getString(NOTE_SOUND_PATH)
                                        );
                                        notes.add(note);
                                    }

                                    final String shapeImageTitle = chordShapeJsonObject.getString(SHAPE_IMAGE_TITLE);
                                    final int shapePosition = chordShapeJsonObject.getInt(SHAPE_POSITION);
                                    final int shapeStartFretPosition = chordShapeJsonObject.getInt(SHAPE_START_FRET_POSITION);
                                    final boolean hasMutedStrings = Boolean.parseBoolean(chordShapeJsonObject.getString(HAS_MUTED_STRINGS));

                                    final MutedStringsHolder mutedStringsHolder = new MutedStringsHolder(
                                            Boolean.
                                                    parseBoolean(chordShapeJsonObject.getString(FIRST_STRING_MUTED)),
                                            Boolean.
                                                    parseBoolean(chordShapeJsonObject.getString(SECOND_STRING_MUTED)),
                                            Boolean.
                                                    parseBoolean(chordShapeJsonObject.getString(THIRD_STRING_MUTED)),
                                            Boolean.
                                                    parseBoolean(chordShapeJsonObject.getString(FOURTH_STRING_MUTED)),
                                            Boolean.
                                                    parseBoolean(chordShapeJsonObject.getString(FIFTH_STRING_MUTED)),
                                            Boolean.
                                                    parseBoolean(chordShapeJsonObject.getString(SIXTH_STRING_MUTED))
                                    );

                                    chordShape = new ChordShape(shapePosition,
                                            shapeStartFretPosition,
                                            shapeImageTitle,
                                            notes,
                                            hasMutedStrings,
                                            mutedStringsHolder,
                                            Boolean.parseBoolean(chordShapeJsonObject.getString(HAS_BAR)),
                                            chordShapeJsonObject.getInt(START_BAR_PLACE),
                                            chordShapeJsonObject.getInt(END_BAR_PLACE)
                                    );

                                    Log.i(TAG, chordShape.toString());
                                    chordShapes.add(chordShape);
                                }



                                chord = new Chord(singleChordsJsonObject.getString(CHORD_TITLE),
                                        singleChordsJsonObject.getString(CHORD_SECOND_TITLE),
                                        singleChordsJsonObject.getString(CHORD_TYPE),
                                        singleChordsJsonObject.getString(CHORD_ALTERATION),
                                        chordShapes
                                );
                            }
                            if(chord != null){
                                Log.i(TAG, chord.toString());
                                chords.add(chord);
                            } else {
                                Log.i(TAG, "chord is null");
                            }
                        }


                    } catch (JSONException jse){
                        jse.printStackTrace();
                    }
                }
            }
        }
        return chords;
    }
}
