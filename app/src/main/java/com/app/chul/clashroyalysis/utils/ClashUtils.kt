package com.app.chul.clashroyalysis.utils

import android.content.Context
import android.content.pm.PackageManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import java.io.IOException
import java.io.InputStream

fun getInflatedView(resourceId: Int, parent: ViewGroup, viewType: Int): View {
    return LayoutInflater.from(parent.context).inflate(resourceId, parent, false)
}

fun isInstalled(context: Context, packageName: String): Boolean {
    val packageManager = context.packageManager
    try {
        packageManager.getApplicationInfo(packageName, PackageManager.GET_META_DATA)
    }catch (e: PackageManager.NameNotFoundException) {
        return false
    }
    return true
}

fun readAssetJson(context: Context, fileName: String): String {
    var inputStream: InputStream ?= null
    var json = ""
    try {
        inputStream = context.assets.open(fileName)
        val size = inputStream.available()
        val buffer = ByteArray(size)

        inputStream.read(buffer)
        json = String(buffer)
    } catch (e: IOException) {

    } finally {
        if(inputStream != null) {
            inputStream.close()
        }
    }

    return json
}
