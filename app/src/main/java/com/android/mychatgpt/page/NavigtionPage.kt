package com.android.mychatgpt.page

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.android.mychatgpt.util.RouterUtil

/**
 * @time 2023/2/28
 * @author kanghongpu
 * @description:
 */

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable

fun NavigtionPage(){
    val navController = rememberNavController()
    Scaffold(
        modifier = Modifier.fillMaxHeight(),
    ) {
        NavHost(navController = navController, startDestination = RouterUtil.indexPage){
            composable(route = RouterUtil.indexPage){
                IndexPage(navController = navController)
            }
            composable(route = RouterUtil.chatPage){
                ChatPage(navController = navController)
            }
            composable(route = RouterUtil.webPage){
                WebViewPage(navController = navController)
            }
        }
    }
}