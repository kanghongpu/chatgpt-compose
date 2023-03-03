package com.android.mychatgpt.util

import android.graphics.Bitmap
import android.net.http.SslError
import android.os.Build
import android.view.View
import android.webkit.*
import android.widget.ProgressBar
import androidx.annotation.RequiresApi

/**
 * @time 2023/3/2
 * @author kanghongpu
 * @description:
 */

class WebUtil(
    val webView:WebView,
    val progressBar:ProgressBar,
) {

    init {
        initSetting();
    }

    fun initSetting(){
        setWebSettings()
        setupWebClient()
    }

    private fun setWebSettings() {
        webView.isVerticalScrollBarEnabled = false
        val webSettings = webView.settings
        webSettings.javaScriptEnabled = true

        webSettings.loadsImagesAutomatically = true
        //运行WebView通过URI获取安卓文件
        webSettings.allowFileAccess = true
        webSettings.allowFileAccessFromFileURLs = false
        webSettings.allowUniversalAccessFromFileURLs = false
        webSettings.loadWithOverviewMode = true
        webSettings.useWideViewPort = true //这里需要设置为true，才能让Webivew支持<meta>标签的viewport属性
        webSettings.setSupportZoom(true) // 设置可以支持缩放
        webSettings.builtInZoomControls = true
        webSettings.displayZoomControls = false //不显示放大缩小按钮
        webSettings.textZoom = 100 //h5页面不随系统字体大小变化
        webSettings.domStorageEnabled = true
        // 关闭密码保存提醒功能
        webSettings.savePassword = false
    }

    private fun setupWebClient() {
        webView.webViewClient = NewWebViewClient()
        webView.webChromeClient = ProgressWebViewChromeClient()
    }

    fun loadUrl(url:String) {
        webView.loadUrl(url)
    }


    inner class ProgressWebViewChromeClient : WebChromeClient() {
        override fun onProgressChanged(view: WebView?, newProgress: Int) {
            super.onProgressChanged(view, newProgress)
            progressBar.progress = newProgress
        }

        override fun onReceivedTitle(view: WebView?, title: String?) {
            super.onReceivedTitle(view, title)
        }
    }


    inner class NewWebViewClient : WebViewClient() {

        @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
        override fun shouldOverrideUrlLoading(
            view: WebView?,
            request: WebResourceRequest?
        ): Boolean {
            return super.shouldOverrideUrlLoading(view, request)
        }

        override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
            return super.shouldOverrideUrlLoading(view, url)
        }

        override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
            progressBar.visibility = View.VISIBLE
            super.onPageStarted(view, url, favicon)
        }

        override fun onPageFinished(view: WebView?, url: String?) {
            progressBar.visibility = View.GONE
            super.onPageFinished(view, url)
        }


    }


}