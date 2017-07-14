package com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.eventbus;

import android.graphics.Bitmap;
import android.util.Log;

import com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.App;
import com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.eventbus.events.ChordShapesImagesHasBeenLoadedEvent;
import com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.eventbus.events.LoadChordShapesBitmapsEvent;
import com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.eventbus.events.LoadChordShapesImagesFromLocalDbEvent;
import com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.helpers.LoadBitmapsFromAssetsHelper;
import com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.local_db.provider.interfaces.DbProvider;
import com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.mvp.interfaces.main_screen.MainPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

import javax.inject.Inject;

/**
 * Created by webprog on 10.07.17.
 */

public class MainEventsHandler extends EventsHandler {

    private static final String TAG = "MainEventsHandler";

    @Inject
    DbProvider mDbProvider;

    public MainEventsHandler(MainPresenter mainPresenter) {
        super(mainPresenter);
        App.getAppComponent().inject(this);
    }

    private MainPresenter getMainPresenter(){
        return (MainPresenter) getEventsSubscriber();
    }

    private DbProvider getDbProvider() {
        return mDbProvider;
    }

    @Subscribe(threadMode = ThreadMode.ASYNC)
    public void onLoadChordShapesFromLocalDbEvent(LoadChordShapesImagesFromLocalDbEvent loadChordShapesImagesFromLocalDbEvent){
        Log.i(TAG, "onLoadChordShapesFromLocalDbEvent");
        Log.i(TAG, loadChordShapesImagesFromLocalDbEvent.getChordShapesTableName());
        Log.i(TAG, "size = " + getDbProvider().getChordShapes(loadChordShapesImagesFromLocalDbEvent.getChordShapesTableName()).size());

        EventBus.getDefault().post(new LoadChordShapesBitmapsEvent(getDbProvider()
                        .getChordShapesImagesTitlesList(loadChordShapesImagesFromLocalDbEvent.getChordShapesTableName())));

    }

    @Subscribe(threadMode = ThreadMode.ASYNC)
    public void onLoadChordShapesBitmapsEvent(LoadChordShapesBitmapsEvent loadChordShapesBitmapsEvent){

        ArrayList<String> chordShapesImagesTitles = loadChordShapesBitmapsEvent.getChordShapesImagesTitles();
        ArrayList<Bitmap> chordShapesImages = new ArrayList<>();

        if(chordShapesImagesTitles != null && chordShapesImagesTitles.size() > 0){

            for(String chordShapeImageTitle: chordShapesImagesTitles){
                chordShapesImages.add(LoadBitmapsFromAssetsHelper.loadBitmapFromAssets(getMainPresenter().getAssetManager(), chordShapeImageTitle));
            }

                    EventBus.getDefault().post(new ChordShapesImagesHasBeenLoadedEvent(chordShapesImages));
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onChordShapesHasBeenLoadedEvent(ChordShapesImagesHasBeenLoadedEvent chordShapesImagesHasBeenLoadedEvent){
        getMainPresenter().notifyMainViewOfNewChordShapesImagesHasBeenLoaded(chordShapesImagesHasBeenLoadedEvent.getChordShapesImages());
    }

}
