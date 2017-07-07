package com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.dagger.modules;

import android.support.annotation.NonNull;

import com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.local_db.provider.impls.DbProviderImpl;
import com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.local_db.provider.interfaces.DbProvider;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by webprog on 07.07.17.
 */
@Module
public class DbProviderModule {

    @Provides
    @NonNull
    @Singleton
    DbProvider provideDbProvider(){
        return new DbProviderImpl();
    }
}
