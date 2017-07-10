package com.androiddeveloper.webprog26.chordsgenerator_0_3;

import android.os.Bundle;

import com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.App;
import com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.dagger.modules.MainPresenterModule;
import com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.mvp.interfaces.main_screen.MainPresenter;
import com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.mvp.interfaces.main_screen.MainView;

import javax.inject.Inject;

public class MainActivity extends BaseActvity implements MainView{

    private static final String C_MAJOR = "C";

    @Inject
    MainPresenter mainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getMainPresenter().setView(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        getMainPresenter().onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getMainPresenter().loadCurrentChordShapes(C_MAJOR);
    }

    @Override
    protected void onStop() {
        getMainPresenter().onStop();
        super.onStop();
    }

    @Override
    protected void setupActivityComponent() {
        App.getAppComponent().plus(new MainPresenterModule()).inject(this);
    }

    private MainPresenter getMainPresenter() {
        return mainPresenter;
    }
}
