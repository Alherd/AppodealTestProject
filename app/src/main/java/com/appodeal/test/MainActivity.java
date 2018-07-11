package com.appodeal.test;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.appodeal.ads.Appodeal;

public class MainActivity extends AppCompatActivity {
    private Button bannerButton;
    private Button nativeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Appodeal.disableLocationPermissionCheck();
        String APP_KEY = "fee50c333ff3825fd6ad6d38cff78154de3025546d47a84f";
        Appodeal.initialize(this, APP_KEY, Appodeal.REWARDED_VIDEO | Appodeal.BANNER | Appodeal.NATIVE);

        Button rewardedVideoButton = findViewById(R.id.rewarded_video);
        rewardedVideoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RewardedVideoActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}
