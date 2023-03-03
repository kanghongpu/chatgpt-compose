package com.android.mychatgpt.net

import com.android.mychatgpt.model.GptModel
import com.google.gson.JsonObject
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

/**
 * @time 2023/3/1
 * @author kanghongpu
 * @description:
 */

interface ApiService {


    @Headers(
        "Content-Type:application/json",
        "Authorization:Bearer chatgpt生成的api")
    @POST("/v1/completions")
    suspend fun getChatContent(@Body json:JsonObject): GptModel
}