package com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.local_db.provider.interfaces;

import android.database.Cursor;
import android.support.annotation.NonNull;

import java.util.ArrayList;

/**
 * Created by webprog on 13.07.17.
 */

public interface ChordShapeImagesTitlesGetter {

    ArrayList<String> getChordShapeImagesTitles(@NonNull Cursor cursor);
}
