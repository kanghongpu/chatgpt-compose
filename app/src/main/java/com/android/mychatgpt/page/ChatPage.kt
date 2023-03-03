package com.android.mychatgpt.page

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.relocation.BringIntoViewRequester
import androidx.compose.foundation.relocation.bringIntoViewRequester
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.android.mychatgpt.view.BasePageWithTitle
import com.android.mychatgpt.viewmodel.ChatModel
import com.android.mychatgpt.viewmodel.ChatViewModel
import com.google.accompanist.insets.LocalWindowInsets
import com.google.accompanist.insets.imePadding
import com.google.accompanist.insets.navigationBarsWithImePadding

/**
 * @time 2023/2/28
 * @author kanghongpu
 * @description:
 */

@Composable
fun ChatPage(
    navController: NavController,
    chatViewModel: ChatViewModel = viewModel()
) {
    BasePageWithTitle(title = "GPT聊天室", nav = navController) {
        var text by remember { mutableStateOf("") }
        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(0.dp)
                    .weight(1f)

            ) {

                LazyColumn {
                    items(chatViewModel.chat.size) {
                        ListItem(model = chatViewModel.chat[it])
                    }
                }
            }
            BottomFieldView(
                value = text,
                valueChange = {
                    text = it
                },
            ) {
                chatViewModel.addMineMessage(text)
                text = ""
            }
        }
    }
}

@Composable
fun ListItem(
    model: ChatModel
) {
    if (model.isMineMsg) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(vertical = 10.dp, horizontal = 10.dp),
            contentAlignment = Alignment.CenterEnd
        ) {
            Column(
                modifier = Modifier
                    .wrapContentHeight()
                    .width(300.dp), horizontalAlignment = Alignment.End
            ) {
                Text(text = "自己", fontWeight = FontWeight(500))
                Text(
                    text = model.contents,
                    modifier = Modifier
                        .background(
                            Color.Green.copy(alpha = 0.5f),
                            shape = RoundedCornerShape(5.dp)
                        )
                        .padding(5.dp)
                )

            }
        }
    } else {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(vertical = 10.dp, horizontal = 10.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            Column(
                modifier = Modifier
                    .wrapContentHeight()
                    .width(300.dp), horizontalAlignment = Alignment.Start
            ) {
                Text(text = "GPT", fontWeight = FontWeight(500))
                Text(
                    text = model.contents,
                    modifier = Modifier
                        .background(
                            Color.Gray.copy(alpha = 0.5f),
                            shape = RoundedCornerShape(5.dp)
                        )
                        .padding(5.dp)
                )
            }
        }
    }
}


@Composable
fun BottomFieldView(
    value: String = "",
    valueChange: (String) -> Unit,
    sendMsg: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.White)
            .padding(horizontal = 10.dp), verticalAlignment = Alignment.CenterVertically
    ) {
        TextField(value = value, onValueChange = {
            valueChange.invoke(it)
        }, modifier = Modifier
            .weight(1f)

            .background(color = Color.White),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.White,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                cursorColor = Color.Black
            ),
            placeholder = {
                Text(text = "请输入问题！", color = Color.Gray)
            }
        )
        Box(
            modifier = Modifier
                .width(0.5.dp)
                .height(15.dp)
                .background(color = Color.Gray)
        )
        Text(
            text = "发送",
            modifier = Modifier
                .padding(horizontal = 10.dp, vertical = 5.dp)
                .clickable {
                    sendMsg.invoke()
                },
            color = Color.Black
        )
    }

}

