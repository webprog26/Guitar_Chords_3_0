package com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.dagger.modules;

import android.support.annotation.NonNull;

import com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.dagger.scopes.ActivityScope;
import com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.mvp.impls.PlayPresenterImpl;
import com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.mvp.interfaces.play_screen.PlayPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by webprog on 14.07.17.
 */
@Module
public class PlayPresenterModule {

    @Provides
    @NonNull
    @ActivityScope
    PlayPresenter providePlayPresenter(){
        return new PlayPresenterImpl();
    }
}
