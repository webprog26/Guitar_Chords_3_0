package com.androiddeveloper.webprog26.chordsgenerator_0_3.engine;

import android.app.Application;

import com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.dagger.components.AppComponent;
import com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.dagger.components.DaggerAppComponent;
import com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.dagger.modules.AppModule;

/**
 * Created by webpr on 06.07.2017.
 */

public class App extends Application {

    private static AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = buildAppComponent();
    }

    @SuppressWarnings("deprecation")
    protected AppComponent buildAppComponent(){
        return DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    public static AppComponent getAppComponent() {
        return appComponent;
    }
}
