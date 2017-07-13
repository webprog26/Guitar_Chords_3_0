package com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.local_db.provider.interfaces;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;

import com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.models.Chord;
import com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.models.ChordShape;
import com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.models.Note;

import java.util.ArrayList;

/**
 * Created by webprog on 07.07.17.
 */

public interface DbProvider {

    void insertChordsToDb(final ArrayList<Chord> chords) throws Exception;
    @NonNull
    ArrayList<ChordShape> getChordShapes(final String chordShapesTableTitle);
    boolean getBoolean(String s);
    @NonNull
    ArrayList<Note> getNotes(String tableTitle, int shapePosition);
    @NonNull
    ArrayList<String> getChordShapesImagesTitlesList(final String chordShapesTableTitle);

}
