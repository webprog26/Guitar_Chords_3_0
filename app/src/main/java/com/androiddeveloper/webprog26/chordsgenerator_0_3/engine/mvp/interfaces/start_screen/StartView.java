package com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.mvp.interfaces.start_screen;

import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.view.View;

/**
 * Created by webprog on 06.07.17.
 */

public interface StartView {

    void startIntentService();
    @NonNull
    SharedPreferences getSharedPreferences();


    void initInflatedView();
    void setStartButtonEnabledAfterChordsWereUploadedToDb();
    void initChordsUnpackingProgressBarMax(final int chordsUnpackingProgressBarMax);
    void increaseChordsUnPackingProgressBarProgress();
    void updateUnpackingChordsText(final String unpackedChordTitle);

}
