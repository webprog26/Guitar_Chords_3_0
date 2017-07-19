package com.androiddeveloper.webprog26.chordsgenerator_0_3;

import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.App;
import com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.adapters.ChordShapesRecyclerViewAdapter;
import com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.constants.Constants;
import com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.dagger.modules.MainPresenterModule;
import com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.models.CurrentChordAndShapePositionInfoContainer;
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
        getMainPresenter().setEventsHandler();

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
        App.getAppComponent().plus(new MainPresenterModule(this, this)).inject(this);
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
    public void updateRecyclerViewAdapterWithNewChordShapesImages(ArrayList<Bitmap> chordShapesImages) {
        for(Bitmap chordShapeBitmap: chordShapesImages){

            if(chordShapeBitmap != null){
                Log.i(TAG, chordShapeBitmap.toString());
            } else {
                Log.i(TAG, "chordShapeBitmap is null");
            }
        }
        getChordShapesRecyclerViewAdapter().updateData(chordShapesImages);
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
    private RecyclerView getRvChordShapes(){
        return mRvChordShapes;
    }

    @Override
    public void onClick(View v) {
        Log.i(TAG, "clicked item position is " + (int)v.getTag());
        Intent playShapesActivityIntent = new Intent(this, PlayShapesActivity.class);
        playShapesActivityIntent.putExtra(Constants.CURRENT_CHORD_AND_SHAPE_POSITION_INFO,
                new CurrentChordAndShapePositionInfoContainer(C_MAJOR, (int)v.getTag()));
        startActivity(playShapesActivityIntent);
    }
}
