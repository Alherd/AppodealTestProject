package com.appodeal.test.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.appodeal.ads.Appodeal;
import com.appodeal.test.R;
import com.appodeal.test.callbacks.AppodealBannerCallbacks;

public class AppodealBannerActivity extends AppCompatActivity {
    private String APP_KEY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner);
        APP_KEY = getString(R.string.app_key);

        final Button initBannerButton = findViewById(R.id.init_banner);
        initBannerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initBannerSdkButton(v);
            }
        });

        final Button isLoadBannerButton = findViewById(R.id.is_load_banner);
        isLoadBannerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Appodeal.isLoaded(Appodeal.BANNER)) {
                    Toast.makeText(AppodealBannerActivity.this, "true", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(AppodealBannerActivity.this, "false", Toast.LENGTH_SHORT).show();
                }
            }
        });

        final Button showBannerButton = findViewById(R.id.show_banner);
        showBannerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bannerShowButton(v);
            }
        });

        final Button hideBannerButton = findViewById(R.id.hide_banner);
        hideBannerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Appodeal.hide(AppodealBannerActivity.this, Appodeal.BANNER);
            }
        });
    }

    private void initBannerSdkButton(View v) {
        Appodeal.setBannerViewId(R.id.appodealBannerView);
        Appodeal.initialize(this, APP_KEY, Appodeal.BANNER);
        Appodeal.setBannerCallbacks(new AppodealBannerCallbacks(AppodealBannerActivity.this));
    }

    public void bannerShowButton(View v) {
        boolean isShown = Appodeal.show(this, Appodeal.BANNER_TOP);
        Toast.makeText(this, String.valueOf(isShown), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResume() {
        super.onResume();
        Appodeal.onResume(this, Appodeal.BANNER_TOP);
    }
}
