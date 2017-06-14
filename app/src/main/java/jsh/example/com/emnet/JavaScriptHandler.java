package jsh.example.com.emnet;

import android.content.Context;
import android.webkit.JavascriptInterface;

import jsh.example.com.emnet.ui.MainActivity;

/**
 * Created by EMGRAM on 2017-05-23.
 */

public class JavaScriptHandler {

    private MainActivity mainActivity;

    public JavaScriptHandler(Context context){
        mainActivity = (MainActivity)context;
    }

    @JavascriptInterface
    public void scriptMethod1(){

    }

    @JavascriptInterface
    public void callNativeMethod(){
        mainActivity.callNativeFromJSHandler();
    }
}
