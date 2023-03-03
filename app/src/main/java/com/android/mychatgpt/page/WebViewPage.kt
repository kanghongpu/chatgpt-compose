package com.android.mychatgpt.page

import android.view.ViewGroup
import android.webkit.WebView
import android.widget.FrameLayout
import androidx.compose.runtime.Composable
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.viewinterop.AndroidViewBinding
import androidx.navigation.NavController
import com.android.mychatgpt.databinding.WebviewPageBinding
import com.android.mychatgpt.util.WebUtil
import com.android.mychatgpt.view.BasePageWithTitle

/**
 * @time 2023/3/1
 * @author kanghongpu
 * @description:
 */

@Composable
fun WebViewPage(
    navController: NavController
){
    BasePageWithTitle(title = "chat聊天室web版", nav = navController) {
        AndroidViewBinding(WebviewPageBinding::inflate){
           val webUtil = WebUtil(webView = webview,progress)
            webUtil.loadUrl("https://chat.openai.com/chat")
        }

    }
}