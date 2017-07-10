package com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.dagger.modules;

import android.content.Context;
import android.support.annotation.NonNull;

import com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.local_db.helpers.ChordsDBHelper;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by webprog on 07.07.17.
 */
@Module
public class DbHelperModule {

    @Provides
    @NonNull
    @Singleton
    ChordsDBHelper provideChordsDBHelper(Context context){
        return new ChordsDBHelper(context);
    }
}
