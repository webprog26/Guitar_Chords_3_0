package com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.dagger.modules;

import android.content.res.AssetManager;
import android.support.annotation.NonNull;

import com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.dagger.scopes.ActivityScope;
import com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.mvp.StartPresenter;
import com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.mvp.impls.StartPresenterImpl;

import dagger.Module;
import dagger.Provides;

/**
 * Created by webprog on 06.07.17.
 */

@Module
public class StartPresenterModule {


    @Provides
    @NonNull
    @ActivityScope
    StartPresenter provideStartPresenter(){
        return new StartPresenterImpl();
    }

}
