package com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.dagger.components.subcomponents;

import com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.dagger.modules.LoadedChordShapesHolderModule;
import com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.dagger.scopes.ActivityScope;
import com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.mvp.impls.PlayPresenterImpl;

import dagger.Subcomponent;

/**
 * Created by webprog on 14.07.17.
 */
@Subcomponent(modules = {LoadedChordShapesHolderModule.class})
@ActivityScope
public interface LoadedChordShapesHolderSubcomponent {

    void inject(PlayPresenterImpl target);
}
