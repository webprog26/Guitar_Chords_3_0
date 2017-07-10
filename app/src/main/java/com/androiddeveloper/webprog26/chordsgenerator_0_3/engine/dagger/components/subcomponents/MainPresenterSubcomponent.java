package com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.dagger.components.subcomponents;

import com.androiddeveloper.webprog26.chordsgenerator_0_3.MainActivity;
import com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.dagger.modules.MainPresenterModule;
import com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.dagger.scopes.ActivityScope;

import dagger.Subcomponent;

/**
 * Created by webprog on 10.07.17.
 */
@Subcomponent(modules = {MainPresenterModule.class})
@ActivityScope
public interface MainPresenterSubcomponent {

    void inject(MainActivity target);
}
