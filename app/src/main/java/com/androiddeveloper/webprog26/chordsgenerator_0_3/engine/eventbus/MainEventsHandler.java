package com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.eventbus;

import android.util.Log;

import com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.App;
import com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.eventbus.events.LoadChordShapesFromLocalDbEvent;
import com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.local_db.provider.interfaces.DbProvider;
import com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.models.ChordShape;
import com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.mvp.interfaces.main_screen.MainPresenter;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

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
    public void onLoadChordShapesFromLocalDbEvent(LoadChordShapesFromLocalDbEvent loadChordShapesFromLocalDbEvent){
        Log.i(TAG, "onLoadChordShapesFromLocalDbEvent");
        Log.i(TAG, loadChordShapesFromLocalDbEvent.getChordShapesTableName());
        Log.i(TAG, "size = " + getDbProvider().getChordShapes(loadChordShapesFromLocalDbEvent.getChordShapesTableName()).size());

        for(ChordShape chordShape: getDbProvider().getChordShapes(loadChordShapesFromLocalDbEvent.getChordShapesTableName())){
            Log.i(TAG, chordShape.toString());
        }
    }
}
