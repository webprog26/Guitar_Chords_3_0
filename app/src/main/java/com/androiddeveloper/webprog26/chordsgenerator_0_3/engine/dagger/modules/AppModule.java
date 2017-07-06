package com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.dagger.modules;

import android.content.Context;
import android.support.annotation.NonNull;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by webprog on 06.07.17.
 */

@Module
public class AppModule {

    private Context appContext;

    public AppModule(Context appContext) {
        this.appContext = appContext;
    }

    @Provides
    @NonNull
    @Singleton
    Context provideAppContext(){
        return appContext;
    }
}
