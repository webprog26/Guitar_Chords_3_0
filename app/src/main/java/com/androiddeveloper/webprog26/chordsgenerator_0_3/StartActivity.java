package com.androiddeveloper.webprog26.chordsgenerator_0_3;

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Button;
import android.widget.ImageView;

import com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.App;
import com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.dagger.modules.StartPresenterModule;
import com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.mvp.StartPresenter;
import com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.mvp.StartView;
import com.androiddeveloper.webprog26.chordsgenerator_0_3.engine.services.REadJSONService;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StartActivity extends BaseActvity implements StartView{

    @BindView(R.id.iv_start_app_logo)
    ImageView mIvStartAppLogo;

    @BindView(R.id.btn_start)
    Button mBtnStart;

    @Inject
    StartPresenter mStartPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        ButterKnife.bind(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        getStartPresenter().onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
//        if(!PreferenceManager.getDefaultSharedPreferences(this).getBoolean("H", false))
        getStartPresenter().readJSON(this);
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

    private ImageView getIvStartAppLogo() {
        return mIvStartAppLogo;
    }

    private Button getBtnStart() {
        return mBtnStart;
    }

    private StartPresenter getStartPresenter() {
        return mStartPresenter;
    }
}
