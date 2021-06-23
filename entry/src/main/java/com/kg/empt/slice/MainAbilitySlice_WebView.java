package com.kg.empt.slice;

import com.kg.empt.ResourceTable;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.agp.components.Component;
import ohos.agp.components.ProgressBar;
import ohos.agp.components.webengine.*;
import ohos.agp.utils.Color;
import ohos.media.image.PixelMap;

public class MainAbilitySlice_WebView extends AbilitySlice {
    WebView webView;
    ProgressBar progressBar;

    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_ability_main_web);
        webView = (WebView) findComponentById(ResourceTable.Id_web_view);
        progressBar = (ProgressBar) findComponentById(ResourceTable.Id_progressBar);

        progressBar.setMaxValue(100);
        progressBar.setMinValue(0);

        progressBar.setProgressColor(Color.RED);
        progressBar.setDividerLineColor(Color.BLUE);
        progressBar.setProgressHintTextColor(Color.GREEN);

        webView.getWebConfig().setJavaScriptPermit(true);//支持js
        WebConfig webConfig = webView.getWebConfig();
        webView.load("https://www.tie1www.com/");
        webView.setWebAgent(new WebAgent() {
            @Override
            public ResourceResponse processResourceRequest(WebView webView, ResourceRequest request) {
                return super.processResourceRequest(webView, request);
                //访问本地文件
            }

            @Override
            public void onLoadingPage(WebView webView, String url, PixelMap favicon) {
                super.onLoadingPage(webView, url, favicon);
                // 页面开始加载时自定义处理
            }

            @Override
            public void onPageLoaded(WebView webView, String url) {
                super.onPageLoaded(webView, url);
                // 页面加载结束后自定义处理
            }

            @Override
            public void onLoadingContent(WebView webView, String url) {
                super.onLoadingContent(webView, url);
                // 加载资源时自定义处理
            }

            @Override
            public void onError(WebView webView, ResourceRequest request, ResourceError error) {
                super.onError(webView, request, error);
                // 发生错误时自定义处理
            }

        });


        webView.setBrowserAgent(new BrowserAgent(this) {
            @Override
            public void onTitleUpdated(WebView webView, String title) {
                super.onTitleUpdated(webView, title);
                // 标题变更时自定义处理

            }

            @Override
            public void onProgressUpdated(WebView webView, int newProgress) {
                super.onProgressUpdated(webView, newProgress);
                    progressBar.setDividerLinesNumber(newProgress);
                    progressBar.setProgressHintText(newProgress+"");
                    progressBar.setProgressValue(newProgress);
                // 加载进度变更时自定义处理
                if (newProgress < 100) {
                    progressBar.setVisibility(Component.VISIBLE);
                }
            }
        });

    }

    @Override
    protected void onBackPressed() {
        Navigator navigator = webView.getNavigator();
        if (navigator.canGoBack()) {//上一页
            navigator.goBack();
            return;
        }
//        if(navigator.canGoForward()){ //记录中的下一页
//            navigator.goForward();
//            return;
//        }
        super.onBackPressed();
    }

    @Override
    public void onActive() {
        super.onActive();
    }

    @Override
    public void onForeground(Intent intent) {
        super.onForeground(intent);
    }

    @Override
    protected void onResult(int requestCode, Intent resultIntent) {
        super.onResult(requestCode, resultIntent);
    }
}

