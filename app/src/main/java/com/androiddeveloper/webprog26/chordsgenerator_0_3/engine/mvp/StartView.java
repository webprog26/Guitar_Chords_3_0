package com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.mvp;

import android.content.SharedPreferences;
import android.support.annotation.NonNull;

/**
 * Created by webprog on 06.07.17.
 */

public interface StartView {

    void startIntentService();
    @NonNull
    SharedPreferences getSharedPreferences();
}
