package com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.dagger.components;

import com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.dagger.components.subcomponents.StartPresenterSubcomponent;
import com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.dagger.modules.AppModule;
import com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.dagger.modules.StartPresenterModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by webprog on 06.07.17.
 */

@Component(modules = {AppModule.class})
@Singleton
public interface AppComponent {

    StartPresenterSubcomponent plus(StartPresenterModule module);
}
