package com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.dagger.components.subcomponents;

import com.androiddeveloper.webprog26.chordsgenerator_0_3.PlayShapesActivity;
import com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.dagger.modules.PlayPresenterModule;
import com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.dagger.scopes.ActivityScope;

import dagger.Subcomponent;

/**
 * Created by webprog on 14.07.17.
 */
@Subcomponent(modules = {PlayPresenterModule.class})
@ActivityScope
public interface PlayPresenterSubcomponent {

    void inject(PlayShapesActivity target);
}
