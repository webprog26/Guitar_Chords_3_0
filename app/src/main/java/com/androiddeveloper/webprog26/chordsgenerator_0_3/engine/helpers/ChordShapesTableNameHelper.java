package com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.helpers;

import com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.constants.Constants;

/**
 * Created by webprog on 10.07.17.
 */

public class ChordShapesTableNameHelper {

    private final static String C_MAJOR = "C";

    public static String getChordShapesTableName(final String chordTitle){

        switch (chordTitle){

            case C_MAJOR:
                return Constants.C_MAJ_ORDINARY_SHAPES;

            default:
                return null;

        }

    }
}
