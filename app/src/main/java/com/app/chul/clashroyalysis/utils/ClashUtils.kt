package com.app.chul.clashroyalysis.utils

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

fun getInflatedView(resourceId: Int, parent: ViewGroup, viewType: Int): View {
    return LayoutInflater.from(parent.context).inflate(resourceId, parent, false)
}