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

public class AppodealRewardedVideoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rewarded_video);

        Button showButton = findViewById(R.id.show);
        showButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isShown = Appodeal.show(AppodealRewardedVideoActivity.this, Appodeal.REWARDED_VIDEO);
                Toast.makeText(AppodealRewardedVideoActivity.this, String.valueOf(isShown), Toast.LENGTH_SHORT).show();
            }
        });

        Button initButton = findViewById(R.id.init);
        initButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Appodeal.setRewardedVideoCallbacks(new AppodealRewardedVideoCallbacks(AppodealRewardedVideoActivity.this));
            }
        });

        Button isLoadButton = findViewById(R.id.is_load);
        isLoadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isRewardedVideoLoadedButton(v);
            }
        });

        Button toNativeButton = findViewById(R.id.to_native_right);
        toNativeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AppodealRewardedVideoActivity.this, AppodealNativeActivity.class);
                startActivity(intent);
            }
        });

        Button toBannerButton = findViewById(R.id.to_banner_left);
        toBannerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AppodealRewardedVideoActivity.this, AppodealBannerActivity.class);
                startActivity(intent);
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