package com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.local_db.provider.impls;

import android.database.Cursor;
import android.support.annotation.NonNull;

import com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.local_db.helpers.ChordsDBHelper;
import com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.local_db.provider.interfaces.ChordShapeImagesTitlesGetter;

import java.util.ArrayList;

/**
 * Created by webprog on 13.07.17.
 */

public class ChordShapeImagesTitlesGetterImpl implements ChordShapeImagesTitlesGetter {

    @Override
    public ArrayList<String> getChordShapeImagesTitles(@NonNull Cursor cursor) {
        ArrayList<String> chordShapesImagesTitles = new ArrayList<>();

        while(cursor.moveToNext()){

            String chordShapeImageTitle = cursor.getString(cursor.getColumnIndex(ChordsDBHelper.SHAPE_IMAGE_TITLE));

            if(chordShapeImageTitle != null){

                chordShapesImagesTitles.add(chordShapeImageTitle);
            }
        }

        cursor.close();
        return chordShapesImagesTitles;
    }
}
