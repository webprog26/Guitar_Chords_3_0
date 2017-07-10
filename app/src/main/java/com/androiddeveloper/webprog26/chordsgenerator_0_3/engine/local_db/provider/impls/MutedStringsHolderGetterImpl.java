package com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.local_db.provider.impls;

import android.database.Cursor;

import com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.local_db.helpers.ChordsDBHelper;
import com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.models.MutedStringsHolder;

/**
 * Created by webprog on 07.07.17.
 */

public class MutedStringsHolderGetterImpl implements MutedStringsHolderGetter {

    @Override
    public MutedStringsHolder getMutedStringsHolder(Cursor cursor) {
        return new MutedStringsHolder(
                Boolean.parseBoolean(cursor.getString(cursor.getColumnIndex(ChordsDBHelper.SHAPE_FIRST_STRING_MUTED))),
                Boolean.parseBoolean(cursor.getString(cursor.getColumnIndex(ChordsDBHelper.SHAPE_SECOND_STRING_MUTED))),
                Boolean.parseBoolean(cursor.getString(cursor.getColumnIndex(ChordsDBHelper.SHAPE_THIRD_STRING_MUTED))),
                Boolean.parseBoolean(cursor.getString(cursor.getColumnIndex(ChordsDBHelper.SHAPE_FOURTH_STRING_MUTED))),
                Boolean.parseBoolean(cursor.getString(cursor.getColumnIndex(ChordsDBHelper.SHAPE_FIFTH_STRING_MUTED))),
                Boolean.parseBoolean(cursor.getString(cursor.getColumnIndex(ChordsDBHelper.SHAPE_SIXTH_STRING_MUTED)))
        );
    }
}
