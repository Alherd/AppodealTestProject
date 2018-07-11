package com.appodeal.test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.appodeal.ads.Appodeal;

public class RewardedVideoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rewarded_video);

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

    @Override
    public void onResume() {
        super.onResume();
        //       Appodeal.onResume(this, Appodeal.BANNER);
    }

    public void isRewardedVideoLoadedButton(View v) {
        if (Appodeal.isLoaded(Appodeal.REWARDED_VIDEO)) {
            Toast.makeText(this, "true", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "false", Toast.LENGTH_SHORT).show();
        }
    }
}