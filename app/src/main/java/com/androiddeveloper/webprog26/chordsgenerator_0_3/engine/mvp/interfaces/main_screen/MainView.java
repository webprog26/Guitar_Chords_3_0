package com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.mvp.interfaces.main_screen;

import com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.models.ChordShape;

import java.util.ArrayList;

/**
 * Created by webprog on 10.07.17.
 */

public interface MainView {

    void updateRecyclerViewAdapterWithNewChordShapes(final ArrayList<ChordShape> chordShapes);
}
