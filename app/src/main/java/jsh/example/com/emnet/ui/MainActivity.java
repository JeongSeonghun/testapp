package jsh.example.com.emnet.ui;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import jsh.example.com.emnet.JavaScriptHandler;
import jsh.example.com.emnet.R;
import jsh.example.com.emnet.model.NetworkListener;

public class MainActivity extends AppCompatActivity {

    String filePath;
    Context context;
    String middlePath;
    String endPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WebView wv_main = (WebView)findViewById(R.id.wv_main);

        // xss 취약점 주의하라고... -> 다른 웹사이트 및 게시판(외부 올리는 상황, 입력 등) 등 미사용이므로 상관 없음.
        wv_main.getSettings().setJavaScriptEnabled(true);
        wv_main.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        wv_main.setWebViewClient(webViewClient);

        // viewport 속성변경으로 인한 추가 kitkat 이상 버전 이슈
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){ //19
            wv_main.getSettings().setUseWideViewPort(true);
            wv_main.getSettings().setLoadWithOverviewMode(true);
            wv_main.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.TEXT_AUTOSIZING);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {  //16
            wv_main.getSettings().setAllowUniversalAccessFromFileURLs(true);
        }
        // dooyong 2016.10.26 시스템 폰트크기에 따라 웹뷰 폰트도 변경되는 현상 수정 (webview 54 이상버전)
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.ICE_CREAM_SANDWICH){    //14
            wv_main.getSettings().setTextZoom(100);
        }

        filePath = "file://" + context.getFilesDir() + middlePath + endPath + "/index.html";

        wv_main.loadUrl(filePath);

        // 보안 문제로 17아래로는 호출 하지 말라고... reflection(xss 관련)
        wv_main.addJavascriptInterface(new JavaScriptHandler(this), "JSHandler");

        wv_main.setWebChromeClient(webChromeClient);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void sessionCheck(){

    }

    NetworkListener sessionCheckListener = new NetworkListener() {
        @Override
        public void onComplete(Object response, int retCode, String eMsg) {
            if(retCode == 0){

            }
        }

        @Override
        public void onNetComplete(Object response, int retCode, String eMsg) {

        }
    };

    public void callNativeFromJSHandler(){

    }

    public void callLocalDataFromJSHandler(){

    }

    public void callSiteFromJSHandler(){

    }

    WebViewClient webViewClient = new WebViewClient(){
        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            //return super.shouldOverrideUrlLoading(view, request);

            String url = request.getUrl().toString();

            if (url.startsWith("tel:")) {
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse(url));
                //startActivity(intent);
            }else {
                view.loadUrl(url);
            }

            return true;
        }

        //deprecate
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            //return super.shouldOverrideUrlLoading(view, url);

            if(Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP){
                if (url.startsWith("tel:")) {
                    Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse(url));
                    //startActivity(intent);
                }else {
                    view.loadUrl(url);
                }
            }

            return true;
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);

        }
    };

    WebChromeClient webChromeClient = new WebChromeClient(){

    };
}
