package com.eightleaves.examples.splashscreen;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class SplashActivity extends AppCompatActivity {

    private static final long TIME_FOR_ANIMATION = 300;
    private AnimationDrawable rocketAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ImageView rocketImage= (ImageView) findViewById(R.id.imageView);
        rocketImage.setBackgroundResource(R.drawable.rocket_thrust);
        rocketAnimation = (AnimationDrawable) rocketImage.getBackground();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        rocketAnimation.start();
        checkIfAnimationDone();

       rocketAnimation.start();
    }

    private void checkIfAnimationDone() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (rocketAnimation.getCurrent() != rocketAnimation.getFrame(rocketAnimation.getNumberOfFrames() - 1)){
                    checkIfAnimationDone();
                } else{
                    Intent intent = new Intent(getApplicationContext(), TabbedActivity.class);
                    startActivity(intent);
                }
            }
        },TIME_FOR_ANIMATION);
    }
}
