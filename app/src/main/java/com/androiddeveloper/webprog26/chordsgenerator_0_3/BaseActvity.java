package com.androiddeveloper.webprog26.chordsgenerator_0_3;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by webprog on 06.07.17.
 */

public abstract class BaseActvity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setupActivityComponent();
    }

    /**
     * Sets up dagger component with lifecycle similar to current activity
     */
    protected abstract void setupActivityComponent();
}
