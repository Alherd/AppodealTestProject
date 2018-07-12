package com.appodeal.test.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.appodeal.ads.Appodeal;
import com.appodeal.test.R;
import com.appodeal.test.callbacks.AppodealRewardedVideoCallbacks;

public class RewardedVideoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rewarded_video);
        Appodeal.disableLocationPermissionCheck();
        Appodeal.initialize(this, getString(R.string.app_key), Appodeal.REWARDED_VIDEO);

        Button showButton = findViewById(R.id.show);
        showButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isShown = Appodeal.show(RewardedVideoActivity.this, Appodeal.REWARDED_VIDEO);
                Toast.makeText(RewardedVideoActivity.this, String.valueOf(isShown), Toast.LENGTH_SHORT).show();
            }
        });

        Button initButton = findViewById(R.id.init);
        initButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Appodeal.setRewardedVideoCallbacks(new AppodealRewardedVideoCallbacks(RewardedVideoActivity.this));
            }
        });

        Button isLoadButton = findViewById(R.id.is_load);
        isLoadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isRewardedVideoLoadedButton(v);
            }
        });
    }

    public void isRewardedVideoLoadedButton(View v) {
        if (Appodeal.isLoaded(Appodeal.REWARDED_VIDEO)) {
            Toast.makeText(this, "true", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "false", Toast.LENGTH_SHORT).show();
        }
    }
}