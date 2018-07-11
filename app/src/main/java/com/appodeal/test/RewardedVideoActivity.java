package com.appodeal.test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.appodeal.ads.Appodeal;

public class RewardedVideoActivity extends AppCompatActivity {
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rewarded_video);

        button = findViewById(R.id.show);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isShown = Appodeal.show(RewardedVideoActivity.this, Appodeal.REWARDED_VIDEO);
                Toast.makeText(RewardedVideoActivity.this, String.valueOf(isShown), Toast.LENGTH_SHORT).show();
            }
        });

        button = findViewById(R.id.load);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Appodeal.setRewardedVideoCallbacks(new AppodealRewardedVideoCallbacks(RewardedVideoActivity.this));
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        //       Appodeal.onResume(this, Appodeal.BANNER);
    }
}