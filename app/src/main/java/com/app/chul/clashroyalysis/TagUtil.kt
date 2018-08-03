package com.app.chul.clashroyalysis

import android.text.TextUtils

fun isAvailableTag(tag: String): Boolean {
    return if(!TextUtils.isEmpty(tag)){
        tag.length == 8
    }else{
        false
    }
}

fun convertListToString(list: ArrayList<String>): String {
    val stringBuilder: StringBuilder = StringBuilder("")

    for(tag in list) {
        stringBuilder.append(tag)
        stringBuilder.append(",")
    }

    stringBuilder.deleteCharAt(list.size - 1)

    return stringBuilder.toString()
}