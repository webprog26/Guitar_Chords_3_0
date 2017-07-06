package com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.services;

import android.app.IntentService;
import android.content.Intent;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.util.Log;

import com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.eventbus.events.JSONHasBeenReadEvent;
import com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.helpers.JSONReader;

import org.greenrobot.eventbus.EventBus;

import java.util.concurrent.TimeUnit;

/**
 * Created by webprog on 06.07.17.
 */

public class REadJSONService extends IntentService {

    private static final String TAG = "REadJSONService";

    public static final String JSON_HAS_BEEN_READ_TAG = "json_has_been_read_tag";

    public REadJSONService() {
        super(TAG);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

        Log.i(TAG, JSONReader.readJson(getAssets()));
    }
}
