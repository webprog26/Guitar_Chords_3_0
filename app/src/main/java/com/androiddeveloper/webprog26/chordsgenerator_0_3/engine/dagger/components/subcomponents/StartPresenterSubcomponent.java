package com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.dagger.components.subcomponents;

import com.androiddeveloper.webprog26.chordsgenerator_0_3.StartActivity;
import com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.dagger.modules.StartPresenterModule;
import com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.dagger.scopes.ActivityScope;

import dagger.Subcomponent;

/**
 * Created by webprog on 06.07.17.
 */
@Subcomponent(modules = {StartPresenterModule.class})
@ActivityScope
public interface StartPresenterSubcomponent {

    void inject(StartActivity target);
}
