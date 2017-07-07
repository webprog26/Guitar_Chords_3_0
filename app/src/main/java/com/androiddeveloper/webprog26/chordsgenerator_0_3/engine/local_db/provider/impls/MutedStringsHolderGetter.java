package com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.local_db.provider.impls;

import android.database.Cursor;

import com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.models.MutedStringsHolder;

/**
 * Created by webprog on 07.07.17.
 */

public interface MutedStringsHolderGetter {

    MutedStringsHolder getMutedStringsHolder(Cursor cursor);
}
