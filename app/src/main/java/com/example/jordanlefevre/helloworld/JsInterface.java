package com.example.jordanlefevre.helloworld;

import android.util.Log;
import android.webkit.JavascriptInterface;

/**
 * Created by jordanlefevre on 14/03/2017.
 */

public class JsInterface {
    @JavascriptInterface
    public void test() {
        Log.d("JS", "toto");
    }
}
