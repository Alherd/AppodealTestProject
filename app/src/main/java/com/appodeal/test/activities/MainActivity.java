package com.appodeal.test.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.appodeal.ads.Appodeal;
import com.appodeal.test.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Appodeal.disableLocationPermissionCheck();
        String APP_KEY = getString(R.string.app_key);
        Appodeal.initialize(this, APP_KEY, Appodeal.REWARDED_VIDEO | Appodeal.BANNER | Appodeal.NATIVE);

        Button rewardedVideoButton = findViewById(R.id.rewarded_video);
        rewardedVideoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RewardedVideoActivity.class);
                startActivity(intent);
            }
        });

        Button bannerButton = findViewById(R.id.banner);
        bannerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, BannerActivity.class);
                startActivity(intent);
            }
        });

        Button nativeButton = findViewById(R.id.native_button);
        nativeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NativeActivity.class);
                startActivity(intent);
            }
        });
    }
}
