package com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.dagger.modules;

import android.support.annotation.NonNull;

import com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.dagger.scopes.ActivityScope;
import com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.mvp.impls.MainPresenterImpl;
import com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.mvp.interfaces.main_screen.MainPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by webprog on 10.07.17.
 */
@Module
public class MainPresenterModule {

    @Provides
    @NonNull
    @ActivityScope
    MainPresenter provideMainPresenter(){
        return new MainPresenterImpl();
    }
}
