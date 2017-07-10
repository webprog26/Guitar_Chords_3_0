package com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.local_db.provider.impls;

import android.content.ContentValues;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.helpers.ChordShapesTableNameHelper;
import com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.local_db.helpers.ChordsDBHelper;
import com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.local_db.provider.interfaces.ChordInserter;
import com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.local_db.provider.interfaces.ChordShapeInserter;
import com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.models.Chord;
import com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.models.ChordShape;

/**
 * Created by webprog on 07.07.17.
 */

public class ChordInserterImpl implements ChordInserter {

    private static final String TAG = "ChordInserter";

    public ChordInserterImpl() {
        Log.i(TAG, "ChordInserterImpl()");
    }

    @Override
    public long insertChord(Chord chord, SQLiteOpenHelper sqLiteOpenHelper) {
        Log.i(TAG, "insertChord()");
        ContentValues contentValues = new ContentValues();
        contentValues.put(ChordsDBHelper.CHORD_TITLE, chord.getChordTitle());
        contentValues.put(ChordsDBHelper.CHORD_SECOND_TITLE, chord.getChordSecondTitle());
        contentValues.put(ChordsDBHelper.CHORD_TYPE, chord.getChordType());
        contentValues.put(ChordsDBHelper.CHORD_ALTERATION, chord.getChordAlteration());

        String chordShapesTableName = ChordShapesTableNameHelper.getChordShapesTableName(chord.getChordTitle());

        if(chordShapesTableName != null){

            contentValues.put(ChordsDBHelper.CHORD_SHAPES_TABLE_NAME, chordShapesTableName);

            ChordShapeInserter chordShapeInserter = new ChordShapeInserterImpl();

            for(ChordShape chordShape: chord.getChordShapes()){
                long result = chordShapeInserter.insertChordShape(chordShapesTableName, chordShape, sqLiteOpenHelper);
                Log.i(TAG, "result = " + result);

            }
        } else {
            Log.i(TAG, "chordShapesTableName is null");
        }
        return sqLiteOpenHelper.getWritableDatabase().insert(ChordsDBHelper.CHORDS_TABLE, null, contentValues);
    }
}
