package com.appodeal.test.activities;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.appodeal.ads.Appodeal;
import com.appodeal.ads.Native;
import com.appodeal.ads.NativeAd;
import com.appodeal.test.HorizontalNumberPicker;
import com.appodeal.test.NativeListAdapter;
import com.appodeal.test.R;
import com.appodeal.test.callbacks.AppodealNativeCallbacks;
import com.appodeal.test.utils.Utils;

import java.util.List;

public class AppodealNativeActivity extends AppCompatActivity {
    private String APP_KEY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_native);
        APP_KEY = getString(R.string.app_key);
        Spinner nativeTemplateSpinner = findViewById(R.id.native_template_list);
        ArrayAdapter<String> nativeTemplateAdapter = new ArrayAdapter<>(AppodealNativeActivity.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.nativeTemplates));
        nativeTemplateSpinner.setAdapter(nativeTemplateAdapter);

        Spinner nativeTypeSpinner = findViewById(R.id.native_type_list);
        ArrayAdapter<String> nativeTypeAdapter = new ArrayAdapter<>(AppodealNativeActivity.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.nativeTypes));
        nativeTypeSpinner.setAdapter(nativeTypeAdapter);
        nativeTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        Appodeal.setNativeAdType(Native.NativeAdType.Auto);
                        break;
                    case 1:
                        Appodeal.setNativeAdType(Native.NativeAdType.NoVideo);
                        break;
                    case 2:
                        Appodeal.setNativeAdType(Native.NativeAdType.Video);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

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

    public void nativeShowButton(View v) {
        hideNativeAds();
        HorizontalNumberPicker numberPicker = findViewById(R.id.nativeAdsCountPicker);
        List<NativeAd> nativeAds = Appodeal.getNativeAds(numberPicker.getNumber());

        LinearLayout nativeAdsListView = findViewById(R.id.nativeAdsListView);
        Spinner nativeTemplateSpinner = findViewById(R.id.native_template_list);
        NativeListAdapter nativeListViewAdapter = new NativeListAdapter(nativeAdsListView, nativeTemplateSpinner.getSelectedItemPosition());
        for (NativeAd nativeAd : nativeAds) {
            nativeListViewAdapter.addNativeAd(nativeAd);
        }
        nativeAdsListView.setTag(nativeListViewAdapter);
        nativeListViewAdapter.rebuild();
    }

    public void nativeHideButton(View v) {
        hideNativeAds();
    }

    public void hideNativeAds() {
        LinearLayout nativeListView = findViewById(R.id.nativeAdsListView);
        nativeListView.removeAllViews();
        NativeListAdapter nativeListViewAdapter = (NativeListAdapter) nativeListView.getTag();
        if (nativeListViewAdapter != null) {
            for (int i = 0; i < nativeListViewAdapter.getCount(); i++) {
                NativeAd nativeAd = (NativeAd) nativeListViewAdapter.getItem(i);
                nativeAd.unregisterViewForInteraction();
            }
            nativeListViewAdapter.clear();
        }
    }

}
