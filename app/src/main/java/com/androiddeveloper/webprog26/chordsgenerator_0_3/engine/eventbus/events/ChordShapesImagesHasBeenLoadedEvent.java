package com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.eventbus.events;

import android.graphics.Bitmap;
import java.util.ArrayList;

/**
 * Created by webprog on 11.07.17.
 */

public class ChordShapesImagesHasBeenLoadedEvent {

    final ArrayList<Bitmap> chordShapes;

    public ChordShapesImagesHasBeenLoadedEvent(ArrayList<Bitmap> chordShapesImages) {
        this.chordShapes = chordShapesImages;
    }

    public ArrayList<Bitmap> getChordShapesImages() {
        return chordShapes;
    }
}
