package com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.local_db.provider.impls;

import android.content.ContentValues;
import android.database.sqlite.SQLiteOpenHelper;

import com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.local_db.ChordsDBHelper;
import com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.local_db.provider.interfaces.ChordShapeInserter;
import com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.local_db.provider.interfaces.NotesArrayToStringConverter;
import com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.models.ChordShape;
import com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.models.MutedStringsHolder;

/**
 * Created by webprog on 07.07.17.
 */

public class ChordShapeInserterImpl implements ChordShapeInserter {

    private final NotesArrayToStringConverter notesArrayToStringConverter = new NotesArrayToStringConverterImpl();

    @Override
    public void insertChordShape(String shapesTableTitle, ChordShape chordShape, SQLiteOpenHelper sqLiteOpenHelper) {
        int startBarPlace = ChordShape.NO_BAR_PLACE;
        int endBarPlace = ChordShape.NO_BAR_PLACE;

        MutedStringsHolder mutedStringsHolder = chordShape.getMutedStringsHolder();

        ContentValues contentValues = new ContentValues();
        contentValues.put(ChordsDBHelper.SHAPE_POSITION, chordShape.getShapePosition());
        contentValues.put(ChordsDBHelper.SHAPE_START_FRET_POSITON, chordShape.getStartFretPosition());



        String notesListString = notesArrayToStringConverter.getStringConverted(chordShape.getNotes());

        if(notesListString != null && notesListString.length() > 0){
            contentValues.put(ChordsDBHelper.SHAPE_NOTES, notesListString);
        }

        contentValues.put(ChordsDBHelper.SHAPE_IMAGE_TITLE, chordShape.getImageTitle());


        contentValues.put(ChordsDBHelper.SHAPE_HAS_MUTED_STRINGS, String.valueOf(chordShape.isHasMutedStrings()));

        contentValues.put(ChordsDBHelper.SHAPE_SIXTH_STRING_MUTED, String.valueOf(mutedStringsHolder.isSixthStringMuted()));
        contentValues.put(ChordsDBHelper.SHAPE_FIFTH_STRING_MUTED, String.valueOf(mutedStringsHolder.isFifthStringMuted()));
        contentValues.put(ChordsDBHelper.SHAPE_FOURTH_STRING_MUTED, String.valueOf(mutedStringsHolder.isFourthStringMuted()));
        contentValues.put(ChordsDBHelper.SHAPE_THIRD_STRING_MUTED, String.valueOf(mutedStringsHolder.isThirdStringMuted()));
        contentValues.put(ChordsDBHelper.SHAPE_SECOND_STRING_MUTED, String.valueOf(mutedStringsHolder.isSecondStringMuted()));
        contentValues.put(ChordsDBHelper.SHAPE_FIRST_STRING_MUTED, String.valueOf(mutedStringsHolder.isFirstStringMuted()));

        if(chordShape.isHasBar()){
            startBarPlace = chordShape.getStartBarPlace();
            endBarPlace = chordShape.getEndBarPlace();
        }

        contentValues.put(ChordsDBHelper.SHAPE_HAS_BAR, String.valueOf(chordShape.isHasBar()));
        contentValues.put(ChordsDBHelper.SHAPE_BAR_START_PLACE, startBarPlace);
        contentValues.put(ChordsDBHelper.SHAPE_BAR_END_PLACE, endBarPlace);

        sqLiteOpenHelper.getWritableDatabase().insert(shapesTableTitle, null, contentValues);
    }
}
