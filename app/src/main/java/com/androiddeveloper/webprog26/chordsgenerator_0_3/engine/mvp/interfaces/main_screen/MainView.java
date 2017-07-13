package com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.mvp.interfaces.main_screen;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by webprog on 10.07.17.
 */

public interface MainView extends View.OnClickListener{

    @NonNull
    AssetManager getAssetManager();
    void initChordShapesRecyclerView();
    void updateRecyclerViewAdapterWithNewChordShapesImages(final ArrayList<Bitmap> chordShapesImages);

    @Override
    void onClick(View v);
}
