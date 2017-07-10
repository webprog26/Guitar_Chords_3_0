package com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.local_db.provider.impls;

import android.database.Cursor;
import android.util.Log;

import com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.App;
import com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.eventbus.events.ChordsUploadedToDatabaseEvent;
import com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.eventbus.events.SingleChordLoadedToLocalDBEvent;
import com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.local_db.helpers.ChordsDBHelper;
import com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.local_db.provider.interfaces.ChordInserter;
import com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.local_db.provider.interfaces.DbProvider;
import com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.local_db.provider.interfaces.NoteFromJsonObjectGetter;
import com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.local_db.provider.interfaces.NotesJsonObjectGetter;
import com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.models.Chord;
import com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.models.ChordShape;
import com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.models.Note;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import javax.inject.Inject;

import static com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.constants.Constants.SHAPE_NOTES;

/**
 * Created by webprog on 07.07.17.
 */

public class DbProviderImpl implements DbProvider {

    private static final String TAG = "DbProvider";

    @Inject
    ChordsDBHelper mChordsDBHelper;

    public DbProviderImpl() {
        App.getAppComponent().inject(this);
    }

    @Override
    public void insertChordsToDb(ArrayList<Chord> chords) throws Exception {

        ChordInserter chordInserter = new ChordInserterImpl();
        ChordsDBHelper chordsDBHelper = getChordsDBHelper();

        for(Chord chord: chords){
            long result = chordInserter.insertChord(chord, chordsDBHelper);
            Log.i(TAG, "result = " + result);

            for(ChordShape chordShape: chord.getChordShapes()){
                Log.i(TAG, chordShape.toString());
            }

            if(result == -1){

                throw new Exception("Something went wrong while uploading chord " + chord.getChordTitle() + " to local db");
            }

            EventBus.getDefault().post(new SingleChordLoadedToLocalDBEvent(chord.getChordTitle()));
        }

        EventBus.getDefault().post(new ChordsUploadedToDatabaseEvent());
    }

    private ChordsDBHelper getChordsDBHelper() {
        return mChordsDBHelper;
    }

    @Override
    public ArrayList<ChordShape> getChordShapes(String chordShapesTableTitle) {

        Log.i(TAG, "getChordShapes()");

        ArrayList<ChordShape> chordShapes = new ArrayList<>();
        ChordShape chordShape;

        MutedStringsHolderGetter mutedStringsHolderGetter = new MutedStringsHolderGetterImpl();

        Cursor cursor = getChordsDBHelper().getReadableDatabase().query(chordShapesTableTitle,
                null,
                null,
                null,
                null,
                null,
                ChordsDBHelper.SHAPE_ID);

        Log.i(TAG, "cursor.getCount() " + cursor.getCount());

        while(cursor.moveToNext()){
            boolean hasBar = Boolean.parseBoolean(cursor.getString(cursor.getColumnIndex(ChordsDBHelper.SHAPE_HAS_BAR)));
            int shapePosition = cursor.getInt(cursor.getColumnIndex(ChordsDBHelper.SHAPE_POSITION));
            int startFretPosition = cursor.getInt(cursor.getColumnIndex(ChordsDBHelper.SHAPE_START_FRET_POSITON));


            String imageTitle = cursor.getString(cursor.getColumnIndex(ChordsDBHelper.SHAPE_IMAGE_TITLE));

            boolean hasMutedStrings = getBoolean(cursor.getString(cursor.getColumnIndex(ChordsDBHelper.SHAPE_HAS_MUTED_STRINGS)));

            int barStartPlace = cursor.getInt(cursor.getColumnIndex(ChordsDBHelper.SHAPE_BAR_START_PLACE));
            int barEndPlace = cursor.getInt(cursor.getColumnIndex(ChordsDBHelper.SHAPE_BAR_END_PLACE));

            chordShape = new ChordShape(shapePosition,
                    startFretPosition,
                    imageTitle,
                    getNotes(chordShapesTableTitle, shapePosition),
                    hasMutedStrings,
                    mutedStringsHolderGetter.getMutedStringsHolder(cursor),
                    hasBar,
                    barStartPlace,
                    barEndPlace

            );
            Log.i(TAG, chordShape.toString());
            chordShapes.add(chordShape);
        }
        cursor.close();
        return chordShapes;
    }

    @Override
    public boolean getBoolean(String s) {
        return Boolean.parseBoolean(s);
    }

    @Override
    public ArrayList<Note> getNotes(String tableTitle, int shapePosition) {
        ArrayList<Note> notes = new ArrayList<>();

        NotesJsonObjectGetter notesJsonObjectGetter = new NotesJsonObjectGetterImpl();
        NoteFromJsonObjectGetter noteFromJsonObjectGetter = new NoteFromJsonObjectGetterImpl();


        String whereClause = ChordsDBHelper.SHAPE_POSITION + " = ?";
        String[] whereArgs = new String[]{String.valueOf(shapePosition)};

        Cursor cursor = getChordsDBHelper().getReadableDatabase().query(tableTitle,
                null,
                whereClause,
                whereArgs,
                null,
                null,
                null);

        while(cursor.moveToNext()){
            JSONObject jsonNotes = notesJsonObjectGetter.getJSONNotesObject(cursor.getString(cursor.getColumnIndex(ChordsDBHelper.SHAPE_NOTES)));

            if(jsonNotes != null){
                Log.i(TAG, jsonNotes.toString());
                JSONArray jsonArray = jsonNotes.optJSONArray(SHAPE_NOTES);
                for(int i = 0; i < jsonArray.length(); i++){
                    Note note = null;
                    JSONObject arrayObject = null;

                    try {
                        arrayObject = jsonArray.getJSONObject(i);
                    } catch (JSONException e){
                        e.printStackTrace();
                    }

                    if(arrayObject != null){
                        note = noteFromJsonObjectGetter.getNoteFromJSONObject(arrayObject);
                    }


                    if(note != null){
                        notes.add(note);
                    }
                }
            }
        }
        cursor.close();
        for(Note note: notes){
            Log.i(TAG, "getNote " + note);
        }
        return notes;
    }
}
