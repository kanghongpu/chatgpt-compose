package com.android.mychatgpt.page

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.android.mychatgpt.util.RouterUtil
import com.android.mychatgpt.view.BasePageWithTitle

/**
 * @time 2023/2/28
 * @author kanghongpu
 * @description:
 */

@Composable
fun IndexPage(
    navController: NavController
) {
    BasePageWithTitle(title = "MyChatGpt", showBack = false) {
        Column(modifier = Modifier.padding(horizontal = 10.dp, vertical = 10.dp)) {
            Text(text = "按钮1是调用chatgpt的api实现的聊天页面，按钮2是打开了chatgpt的网页，使用web版本的时候需要链接VPN")
            Spacer(modifier = Modifier.height(10.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp)
                    .background(color = Color.Gray, shape = RoundedCornerShape(10.dp))
                    .clickable {
                        navController.navigate(RouterUtil.chatPage)
                    },
                contentAlignment = Alignment.Center
            ) {
                Text(text = "跳转到chat聊天室", color = Color.White)
            }
            Spacer(modifier = Modifier.height(10.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp)
                    .background(color = Color.Gray, shape = RoundedCornerShape(10.dp))
                    .clickable {
                        navController.navigate(RouterUtil.webPage)
                    },
                contentAlignment = Alignment.Center
            ) {
                Text(text = "跳转到chat聊天室web版", color = Color.White)
            }
        }
    }


}
