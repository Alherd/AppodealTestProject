package com.appodeal.test.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.appodeal.ads.Appodeal;
import com.appodeal.ads.Native;
import com.appodeal.ads.NativeAd;
import com.appodeal.ads.NativeMediaView;
import com.appodeal.ads.native_ad.views.NativeAdViewAppWall;
import com.appodeal.ads.native_ad.views.NativeAdViewContentStream;
import com.appodeal.ads.native_ad.views.NativeAdViewNewsFeed;
import com.appodeal.test.R;
import com.appodeal.test.callbacks.AppodealNativeCallbacks;

public class AppodealNativeActivity extends AppCompatActivity {
    private String APP_KEY;
    LinearLayout nativeAdsListView;

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

        nativeAdsListView = findViewById(R.id.nativeAdsListView);
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
        NativeAd nativeAds = Appodeal.getNativeAds(1).get(0);

        Spinner nativeTemplateSpinner = findViewById(R.id.native_template_list);

        rebuild(nativeAds, nativeTemplateSpinner.getSelectedItemPosition());

    }

    public void nativeHideButton(View v) {
        hideNativeAds();
    }

    public void hideNativeAds() {
        nativeAdsListView.removeAllViews();

    }

    private View getView(NativeAd nativeAd, int mType) {
        ViewGroup convertView = null;
        switch (mType) {
            case 0:
                convertView = (ViewGroup) LayoutInflater.from(this).inflate(R.layout.include_native_ads, nativeAdsListView, false);
                TextView tvTitle = convertView.findViewById(R.id.tv_title);
                tvTitle.setText(nativeAd.getTitle());

                TextView tvDescription = convertView.findViewById(R.id.tv_description);
                tvDescription.setText(nativeAd.getDescription());

                RatingBar ratingBar = convertView.findViewById(R.id.rb_rating);
                if (nativeAd.getRating() == 0) {
                    ratingBar.setVisibility(View.INVISIBLE);
                } else {
                    ratingBar.setVisibility(View.VISIBLE);
                    ratingBar.setRating(nativeAd.getRating());
                    ratingBar.setStepSize(0.1f);
                }

                Button ctaButton = convertView.findViewById(R.id.b_cta);
                ctaButton.setText(nativeAd.getCallToAction());

                ((ImageView) convertView.findViewById(R.id.icon)).setImageBitmap(nativeAd.getIcon());

                View providerView = nativeAd.getProviderView(this);
                if (providerView != null) {
                    if (providerView.getParent() != null && providerView.getParent() instanceof ViewGroup) {
                        ((ViewGroup) providerView.getParent()).removeView(providerView);
                    }
                    FrameLayout providerViewContainer = convertView.findViewById(R.id.provider_view);
                    ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    providerViewContainer.addView(providerView, layoutParams);
                }

                TextView tvAgeRestrictions = convertView.findViewById(R.id.tv_age_restriction);
                if (nativeAd.getAgeRestrictions() != null) {
                    tvAgeRestrictions.setText(nativeAd.getAgeRestrictions());
                    tvAgeRestrictions.setVisibility(View.VISIBLE);
                } else {
                    tvAgeRestrictions.setVisibility(View.GONE);
                }
                NativeMediaView nativeMediaView = convertView.findViewById(R.id.appodeal_media_view_content);
                if (nativeAd.containsVideo()) {
                    nativeAd.setNativeMediaView(nativeMediaView);
                } else {
                    nativeMediaView.setVisibility(View.GONE);
                }

                nativeAd.registerViewForInteraction(convertView);
                convertView.setVisibility(View.VISIBLE);
                break;
            case 1:
                convertView = new NativeAdViewNewsFeed(this, nativeAd);
                break;
            case 2:
                convertView = new NativeAdViewAppWall(this, nativeAd);
                break;
            case 3:
                convertView = new NativeAdViewContentStream(this, nativeAd);
                break;
        }
        return convertView;
    }

    public void rebuild(NativeAd nativeAd, int mType) {
        nativeAdsListView.removeAllViews();
        nativeAdsListView
                .addView(
                        getView(nativeAd, mType));
    }

}
