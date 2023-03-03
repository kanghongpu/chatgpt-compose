package com.android.mychatgpt.model

/**
 * @time 2023/3/2
 * @author kanghongpu
 * @description:
 */

data class GptModel(var choices:ArrayList<GptItemModel>)

data class GptItemModel(var text:String)