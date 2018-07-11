package com.appodeal.test.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.appodeal.ads.Appodeal;
import com.appodeal.ads.NativeAd;
import com.appodeal.test.R;
import com.appodeal.test.callbacks.AppodealNativeCallbacks;

import java.util.List;

public class AppodealNativeActivity extends AppCompatActivity {
    private String APP_KEY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_native);
        APP_KEY = getString(R.string.app_key);

        final Button initNativeButton = findViewById(R.id.init_native);
        initNativeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initNativeSdkButton(v);
            }
        });

        final Button isLoadNativeButton = findViewById(R.id.is_load_native);
        isLoadNativeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isNativeLoadedButton(v);
            }
        });

        Button showNativeButton = findViewById(R.id.show_native);
        showNativeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                List<NativeAd> nativeAds = Appodeal.getNativeAds(1);
//                LinearLayout nativeAdsListView = findViewById(R.id.nativeAdsListView);
//                nativeAdsListView.setTag(nativeAds.get(0));
            }
        });
    }

    public void initNativeSdkButton(View v) {
        Appodeal.setNativeCallbacks(new AppodealNativeCallbacks(this));
        Appodeal.initialize(this, APP_KEY, Appodeal.NATIVE);
        Appodeal.setAutoCacheNativeIcons(true);
        Appodeal.setAutoCacheNativeMedia(true);
    }

    public void isNativeLoadedButton(View v) {
        if (Appodeal.isLoaded(Appodeal.NATIVE)) {
            Toast.makeText(this, "true", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "false", Toast.LENGTH_SHORT).show();
        }
    }
}
