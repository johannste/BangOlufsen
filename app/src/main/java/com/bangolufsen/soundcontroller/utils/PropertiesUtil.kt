package com.bangolufsen.soundcontroller.utils

import android.content.Context
import android.util.Log
import java.io.IOException
import java.util.*

object PropertiesUtil {

    fun getProperty(context: Context, key: String): String {
        var result = ""
        val properties = Properties()
        try {
            val inputStream = context.assets.open("application.properties")
            properties.load(inputStream)
            result = properties.getProperty(key)
            Log.i("Config", result)
        } catch (e: IOException) {
            Log.e("Conf error", "读取不到配置文件")
        }
        return result
    }

}