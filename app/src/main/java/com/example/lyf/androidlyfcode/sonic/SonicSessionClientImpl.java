package com.example.lyf.androidlyfcode.sonic;

import android.os.Bundle;
import android.webkit.WebView;

import com.tencent.sonic.sdk.SonicSessionClient;

import java.util.HashMap;

/**
 * Created by huangweizhu on 2018/1/11.
 * e-mail:huangweizhu8@163.com
 * version: 2.0
 * Describe:
 */

public class SonicSessionClientImpl extends SonicSessionClient {

    private WebView webView;
    public void bindWebView(WebView webView) {
        this.webView = webView;
    }
    /**
     * 调用webView的loadUrl
     */
    @Override
    public void loadUrl(String url, Bundle extraData) {
        webView.loadUrl(url);
    }
    /**
     * 调用webView的loadDataWithBaseUrl方法
     */
    @Override
    public void loadDataWithBaseUrl(String baseUrl, String data, String mimeType, String encoding,
                                    String historyUrl) {
        webView.loadDataWithBaseURL(baseUrl, data, mimeType, encoding, historyUrl);
    }

    @Override
    public void loadDataWithBaseUrlAndHeader(String baseUrl, String data, String mimeType, String encoding, String historyUrl, HashMap<String, String> headers) {

    }
}
