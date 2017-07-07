package com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.local_db.provider.impls;

import android.content.ContentValues;
import android.database.sqlite.SQLiteOpenHelper;

import com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.local_db.ChordsDBHelper;
import com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.local_db.provider.interfaces.ChordInserter;
import com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.local_db.provider.interfaces.ChordShapeInserter;
import com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.models.Chord;
import com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.models.ChordShape;

/**
 * Created by webprog on 07.07.17.
 */

public class ChordInserterImpl implements ChordInserter {

    @Override
    public long insertChord(Chord chord, SQLiteOpenHelper sqLiteOpenHelper) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(ChordsDBHelper.CHORD_TITLE, chord.getChordTitle());
        contentValues.put(ChordsDBHelper.CHORD_SECOND_TITLE, chord.getChordSecondTitle());
        contentValues.put(ChordsDBHelper.CHORD_TYPE, chord.getChordType());
        contentValues.put(ChordsDBHelper.CHORD_ALTERATION, chord.getChordAlteration());

        String chordShapesTableName = chord.getChordShapesTableName();

        if(chordShapesTableName != null){

            contentValues.put(ChordsDBHelper.CHORD_SHAPES_TABLE_NAME, chordShapesTableName);

            ChordShapeInserter chordShapeInserter = new ChordShapeInserterImpl();

            for(ChordShape chordShape: chord.getChordShapes()){
                chordShapeInserter.insertChordShape(chordShapesTableName, chordShape, sqLiteOpenHelper);
            }
        }
        return sqLiteOpenHelper.getWritableDatabase().insert(ChordsDBHelper.CHORDS_TABLE, null, contentValues);
    }
}
