package com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.dagger.components;

import com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.dagger.components.subcomponents.MainPresenterSubcomponent;
import com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.dagger.components.subcomponents.StartPresenterSubcomponent;
import com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.dagger.modules.AppModule;
import com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.dagger.modules.DbHelperModule;
import com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.dagger.modules.DbProviderModule;
import com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.dagger.modules.MainPresenterModule;
import com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.dagger.modules.StartPresenterModule;
import com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.eventbus.StartEventsHandler;
import com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.eventbus.MainEventsHandler;
import com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.local_db.provider.impls.DbProviderImpl;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by webprog on 06.07.17.
 */

@Component(modules = {AppModule.class, DbProviderModule.class, DbHelperModule.class})
@Singleton
public interface AppComponent {

    StartPresenterSubcomponent plus(StartPresenterModule module);
    MainPresenterSubcomponent plus(MainPresenterModule module);
    void inject(StartEventsHandler target);
    void inject(MainEventsHandler target);
    void inject(DbProviderImpl target);
}
