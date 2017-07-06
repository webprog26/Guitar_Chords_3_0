package com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.helpers;

import android.content.res.AssetManager;
import android.util.Log;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by webprog on 06.07.17.
 */

public class JSONReader {
    private static final String TAG = "JSONReader";

    private static final String PROPER_UTF_8_ENCODING = "UTF-8";

    //JSON file name in assets dir
    private static final String JSON_FILE_NAME = "shapes/shapes_demo.json";

    public static String readJson(AssetManager assetManager){
        return loadJSONFromAsset(assetManager, JSON_FILE_NAME);
    }


    /**
     * Reads .json file directly from assets directory and transform it into the {@link String}
     * @param assetManager {@link AssetManager}
     * @param jsonFilename {@link String}
     * @return {@link String}
     */
    @SuppressWarnings("ResultOfMethodCallIgnored")
    private static String loadJSONFromAsset(AssetManager assetManager, String jsonFilename) {
        Log.i(TAG, "loadJSONFromAsset()");
        String json;
        try {
            InputStream is = assetManager.open(jsonFilename);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, PROPER_UTF_8_ENCODING);
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        Log.i(TAG, json);
        return json;

    }
}
