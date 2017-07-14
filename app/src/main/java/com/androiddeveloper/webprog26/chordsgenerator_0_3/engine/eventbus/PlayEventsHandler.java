package com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.eventbus;

import android.util.Log;

import com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.App;
import com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.eventbus.events.FullChordShapesLoadedFromLocalDbEvent;
import com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.eventbus.events.LoadFullChordShapesFromLocalDbEvent;
import com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.local_db.provider.interfaces.DbProvider;
import com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.models.ChordShape;
import com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.mvp.interfaces.play_screen.PlayPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import javax.inject.Inject;

/**
 * Created by webprog on 14.07.17.
 */

public class PlayEventsHandler extends EventsHandler {

    private static final String TAG = "PlayEventsHandler";

    @Inject
    DbProvider mDbProvider;

    public PlayEventsHandler(PlayPresenter playPresenter) {
        super(playPresenter);
        App.getAppComponent().inject(this);
    }

    @Subscribe(threadMode = ThreadMode.ASYNC)
    public void onLoadFullChordShapesFromLocalDbEvent(LoadFullChordShapesFromLocalDbEvent loadFullChordShapesFromLocalDbEvent){
        EventBus.getDefault().post(new FullChordShapesLoadedFromLocalDbEvent(getDbProvider().getChordShapes(loadFullChordShapesFromLocalDbEvent.getChordShapesTableName())));
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onFullChordShapesLoadedFromLocalDbEvent(FullChordShapesLoadedFromLocalDbEvent fullChordShapesLoadedFromLocalDbEvent){
        for(final ChordShape chordShape: fullChordShapesLoadedFromLocalDbEvent.getChordShapes()){
            getPlayPresenter().getLoadedChordShapesHolder().addChordShape(chordShape);
        }
        getPlayPresenter().notifyPlayViewOfFullChordShapesHaveBeenLoaded();
    }

    private DbProvider getDbProvider() {
        return mDbProvider;
    }

    private PlayPresenter getPlayPresenter(){
        return (PlayPresenter) getEventsSubscriber();
    }
}
