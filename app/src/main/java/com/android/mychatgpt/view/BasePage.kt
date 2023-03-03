package com.android.mychatgpt.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.imePadding
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.insets.*
import com.google.accompanist.insets.statusBarsPadding
import com.google.accompanist.systemuicontroller.rememberSystemUiController

/**
 * @time 2023/3/1
 * @author kanghongpu
 * @description:
 */

@Composable
fun BasePageWithTitle(
    title: String = "",
    showBack: Boolean = true,
    nav: NavController = rememberNavController(),
    content:@Composable ()->Unit
){
   BasePage {
        Column(modifier = Modifier
            .background(color = Color(0xffe5e5e5))
            .fillMaxSize()) {
            Box(modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .background(color = Color.White)
                .statusBarsPadding(),
                contentAlignment = Alignment.CenterStart
            ) {
              AppTitleBar(title = title,showBack = showBack,nav = nav)
            }
            content()
        }
    }
}


@Composable
fun BasePage(
    content:@Composable ()->Unit
){
    ProvideWindowInsets(
        consumeWindowInsets = true
    ) {
        rememberSystemUiController().run {
            setSystemBarsColor(color = Color.Transparent, darkIcons = true)
        }
        content.invoke()
    }
}


@Composable
fun AppTitleBar(
    title:String = "",
    showBack:Boolean = true,
    nav:NavController = rememberNavController()
){
    ConstraintLayout(constraintSet = ConstraintSet {
        val backImage = createRefFor("backImage")
        val title = createRefFor("title")
        constrain(backImage){
            start.linkTo(parent.start, margin = 5.dp)
            top.linkTo(parent.top)
            bottom.linkTo(parent.bottom)
        }
        constrain(title){
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            top.linkTo(parent.top)
            bottom.linkTo(parent.bottom)
        }
    }, modifier = Modifier
        .fillMaxWidth()
        .height(45.dp)) {
        if(showBack) {
            Image(
                painter = painterResource(id = com.android.mychatgpt.R.mipmap.img_back_arrow),
                contentDescription = null,
                modifier = Modifier
                    .layoutId("backImage")
                    .width(40.dp)
                    .height(40.dp)
                    .clickable { nav.navigateUp() }
            )
        }
        Text(text = title, modifier = Modifier.layoutId("title"))
    }
}