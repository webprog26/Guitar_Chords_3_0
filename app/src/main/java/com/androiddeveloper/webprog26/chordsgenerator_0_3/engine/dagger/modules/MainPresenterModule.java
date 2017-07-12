package com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.dagger.modules;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;

import com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.adapters.ChordShapesRecyclerViewAdapter;
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

    private final Context context;
    private final View.OnClickListener onClickListener;

    public MainPresenterModule(Context context, View.OnClickListener onClickListener) {
        this.context = context;
        this.onClickListener = onClickListener;
    }

    private Context getContext() {
        return context;
    }

    private View.OnClickListener getOnClickListener() {
        return onClickListener;
    }

    @Provides
    @NonNull
    @ActivityScope
    MainPresenter provideMainPresenter(){
        return new MainPresenterImpl();
    }

    @Provides
    @NonNull
    @ActivityScope
    ChordShapesRecyclerViewAdapter provideChordShapesRecyclerViewAdapter(){
        return new ChordShapesRecyclerViewAdapter(getContext(), getOnClickListener());
    }
}
