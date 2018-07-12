package com.appodeal.test.utils;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

public class Utils {
    public static void showToast(Context context, String text) {
        Log.d("Appodeal", text);
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
    }
}
