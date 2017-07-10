package com.androiddeveloper.webprog26.chordsgenerator_0_3;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.view.ViewStub;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.App;
import com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.constants.Constants;
import com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.dagger.modules.StartPresenterModule;
import com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.mvp.interfaces.start_screen.StartPresenter;
import com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.mvp.interfaces.start_screen.StartView;
import com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.services.REadJSONService;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StartActivity extends BaseActvity implements StartView{

    private static final String TAG = "StartView";

    @BindView(R.id.iv_start_app_logo)
    ImageView mIvStartAppLogo;

    @BindView(R.id.vs_unpacking_chords)
    ViewStub mVsUnpackingChords;

    @BindView(R.id.btn_start)
    Button mBtnStart;

    @Inject
    StartPresenter mStartPresenter;

    private View mUnpackingChordsView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        ButterKnife.bind(this);

        getStartPresenter().setView(this);

        getBtnStart().setEnabled(getSharedPreferences().getBoolean(Constants.CHORDS_WERE_UPLOADED_TO_DB_NARKER, false));

        getBtnStart().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               startActivity(new Intent(StartActivity.this, MainActivity.class));
               finish();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        getStartPresenter().onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(!getSharedPreferences()
                .getBoolean(Constants
                        .JSON_STRING_HAS_BEEN_READ_MARKER, false)){

            getStartPresenter().readJSON(this);
        }

    }

    @Override
    protected void onStop() {
        getStartPresenter().onStop();
        super.onStop();
    }

    @Override
    protected void setupActivityComponent() {
        App.getAppComponent().plus(new StartPresenterModule()).inject(this);
    }

    @Override
    public void startIntentService() {
        Intent readJSONIntent = new Intent(this, REadJSONService.class);
        startService(readJSONIntent);
    }

    @NonNull
    @Override
    public SharedPreferences getSharedPreferences() {
        return PreferenceManager.getDefaultSharedPreferences(this);
    }

    @Override
    public void updateUnpackingChordsText(String unpackedChordTitle) {
        View view = getUnpackingChordsView();

        if(view != null){
            ((TextView) view.findViewById(R.id.tv_loading))
                    .setText(getString(R.string.chord_unpacked,
                                       unpackedChordTitle));
        }
    }

    @Override
    public void initInflatedView() {
        this.mUnpackingChordsView = getVsUnpackingChords().inflate();
    }

    @Override
    public void setStartButtonEnabledAfterChordsWereUploadedToDb() {
        Button startButton = getBtnStart();

        if(startButton != null){

            if(!startButton.isEnabled()){

                startButton.setEnabled(true);
            }

        }
    }

    @Override
    public void initChordsUnpackingProgressBarMax(int chordsUnpackingProgressBarMax) {
        View view = getUnpackingChordsView();

        if(view != null){

            Log.i(TAG, "max = " + chordsUnpackingProgressBarMax);

            ((ProgressBar) view.findViewById(R.id.pb_loading))
                    .setMax(chordsUnpackingProgressBarMax);
        } else {
            Log.i(TAG, "view is null");
        }
    }

    @Override
    public void increaseChordsUnPackingProgressBarProgress() {
        View view = getUnpackingChordsView();

        if(view != null){

            ProgressBar loadingProgressBar = (ProgressBar) view.findViewById(R.id.pb_loading);

                int progress = loadingProgressBar.getProgress();
                int max = loadingProgressBar.getMax();

                Log.i(TAG, "progress = " + progress);

                if(max > 0 && progress < max){
                    int currentProgress = progress + 1;

                    loadingProgressBar.setProgress(currentProgress);

                    if(currentProgress == max){
                        getVsUnpackingChords().setVisibility(View.GONE);
                    }
                }


            }
    }

    private ImageView getIvStartAppLogo() {
        return mIvStartAppLogo;
    }

    private Button getBtnStart() {
        return mBtnStart;
    }

    private StartPresenter getStartPresenter() {
        return mStartPresenter;
    }

    private ViewStub getVsUnpackingChords(){
        return mVsUnpackingChords;
    }

    public View getUnpackingChordsView() {
        return mUnpackingChordsView;
    }
}
