package com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.local_db.provider.interfaces;

import android.database.sqlite.SQLiteOpenHelper;

import com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.models.Chord;

/**
 * Created by webprog on 07.07.17.
 */

public interface ChordInserter {

    long insertChord(final Chord chord, SQLiteOpenHelper sqLiteOpenHelper);
}
