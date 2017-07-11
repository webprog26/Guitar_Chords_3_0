package com.androiddeveloper.webprog26.chordsgenerator_0_3;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.App;
import com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.adapters.ChordShapesRecyclerViewAdapter;
import com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.dagger.modules.MainPresenterModule;
import com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.models.ChordShape;
import com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.mvp.interfaces.main_screen.MainPresenter;
import com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.mvp.interfaces.main_screen.MainView;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActvity implements MainView{

    private static final String TAG = "MainView";

    private static final String C_MAJOR = "C";

    @Inject
    MainPresenter mainPresenter;

    @Inject
    ChordShapesRecyclerViewAdapter mChordShapesRecyclerViewAdapter;

    @BindView(R.id.rv_chord_shapes)
    RecyclerView mRvChordShapes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        getMainPresenter().setView(this);

        initChordShapesRecyclerView();
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
        App.getAppComponent().plus(new MainPresenterModule(this)).inject(this);
    }

    @NonNull
    @Override
    public AssetManager getAssetManager() {
        return getAssets();
    }

    @Override
    public void initChordShapesRecyclerView() {
        RecyclerView chordShapesRecyclerView = getRvChordShapes();
        chordShapesRecyclerView.setItemAnimator(new DefaultItemAnimator());
        chordShapesRecyclerView.setHasFixedSize(true);
        chordShapesRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        chordShapesRecyclerView.setAdapter(getChordShapesRecyclerViewAdapter());
    }

    @Override
    public void updateRecyclerViewAdapterWithNewChordShapes(ArrayList<ChordShape> chordShapes) {
        for(ChordShape chordShape: chordShapes){

            Bitmap chordShapeBitmap = chordShape.getChordShapeBitmap();

            if(chordShapeBitmap != null){
                Log.i(TAG, chordShapeBitmap.toString());
            } else {
                Log.i(TAG, "chordShapeBitmap is null");
            }
        }
        getChordShapesRecyclerViewAdapter().updateData(chordShapes);
    }

    @NonNull
    private MainPresenter getMainPresenter() {
        return mainPresenter;
    }

    @NonNull
    private ChordShapesRecyclerViewAdapter getChordShapesRecyclerViewAdapter() {
        return mChordShapesRecyclerViewAdapter;
    }

    @NonNull
    RecyclerView getRvChordShapes(){
        return mRvChordShapes;
    }
}
