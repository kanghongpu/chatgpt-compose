package com.android.mychatgpt.viewmodel

import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.mychatgpt.GptApp
import com.android.mychatgpt.model.GptModel
import com.android.mychatgpt.net.ApiFactory
import com.google.gson.JsonObject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.launch

/**
 * @time 2023/3/1
 * @author kanghongpu
 * @description:
 */

class ChatViewModel : ViewModel() {
    var chat = mutableStateListOf<ChatModel>()


    suspend fun getChatGptMsg(msg: String) {
        val jsonObject: JsonObject = JsonObject().apply {
            // params
            addProperty("model", "text-davinci-003")
            addProperty("prompt", msg)
            addProperty("temperature", 0)
            addProperty("max_tokens", 500)
            addProperty("top_p", 1)
            addProperty("frequency_penalty", 0.0)
            addProperty("presence_penalty", 0.0)
        }
        flow<GptModel> {
            emit(ApiFactory.retrofit.getChatContent(jsonObject))
        }.flowOn(Dispatchers.IO).catch { e->
            Toast.makeText(GptApp.appContext,"网络错误！",Toast.LENGTH_SHORT).show()
        }.collect {
            addChatMessage(it.choices[0].text.trim())
        }

    }


    fun addMineMessage(msg: String) {
        if (msg.isNotBlank()) {
            chat.add(ChatModel(msg, true))
            viewModelScope.launch { getChatGptMsg(msg) }
        }
    }

    fun addChatMessage(msg: String) {
        chat.add(ChatModel(msg, false))
    }

}


data class ChatModel(
    var contents: String = "",
    var isMineMsg: Boolean = false
)