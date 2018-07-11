package com.appodeal.test.callbacks;

import android.app.Activity;

import com.appodeal.ads.BannerCallbacks;
import com.appodeal.test.utils.Utils;

public class AppodealBannerCallbacks implements BannerCallbacks {
    private final Activity mActivity;

    public AppodealBannerCallbacks(Activity activity) {
        mActivity = activity;
    }

    @Override
    public void onBannerLoaded(int height, boolean isPrecache) {
        Utils.showToast(mActivity, String.format("onBannerLoaded, %sdp, isPrecache: %s", height, isPrecache));
    }

    @Override
    public void onBannerFailedToLoad() {
        Utils.showToast(mActivity, "onBannerFailedToLoad");
    }

    @Override
    public void onBannerShown() {
        Utils.showToast(mActivity, "onBannerShown");
    }

    @Override
    public void onBannerClicked() {
        Utils.showToast(mActivity, "onBannerClicked");
    }
}
