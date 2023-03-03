package com.android.mychatgpt

import android.app.Application

/**
 * @time 2023/2/28
 * @author kanghongpu
 * @description:
 */

class GptApp :Application() {
    companion object{
        lateinit var appContext:GptApp
    }

    override fun onCreate() {
        super.onCreate()
        appContext = this
    }
}