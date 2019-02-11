package com.app.chul.clashroyalysis.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.RelativeLayout
import com.app.chul.clashroyalysis.R

class SubTaskView @JvmOverloads constructor(context: Context?, attrs: AttributeSet? = null, defStyleAttr: Int = 0):
        RelativeLayout(context, attrs, defStyleAttr){

    private val backgroundTask: LinearLayout
    private val foregroundTask: LinearLayout

    init {
        LayoutInflater.from(context).inflate(R.layout.view_sub_task, this, true)
        backgroundTask = findViewById(R.id.background_task)
        foregroundTask = findViewById(R.id.foreground_task)
    }

    /**
     * Task Info
     */
    fun getBackgroundTaskWidth() = backgroundTask.width

    fun getForegroundTaskWidth() = foregroundTask.width

    /**
     *  Add task
     */
    fun addBackgroundTask(task: View) {
        backgroundTask.addView(task)
    }

    fun addBackgroundTask(task: View, params: LinearLayout.LayoutParams) {
        backgroundTask.addView(task, params)
    }

    fun addForegroundTask(task: View) {
        foregroundTask.addView(task)
    }

    fun addForegroundTask(task: View, params: LinearLayout.LayoutParams) {
        foregroundTask.addView(task, params)
    }

    /**
     *  Background default orientation is Horizontal
     */
    fun setBackgroundOrientation(orientation: Int) {
        backgroundTask.orientation = orientation
    }

    /**
     *  Foreground default orientation is Horizontal
     */
    fun setForegroundOrientaion(orientation: Int) {
        foregroundTask.orientation = orientation
    }

    fun setBackgroundTaskColor(color: Int) {
        backgroundTask.setBackgroundColor(color)
    }

    fun setForegroundTaskColor(color: Int) {
        foregroundTask.setBackgroundColor(color)
    }

    fun setBackgroundTaskMargin(left: Int, top: Int, right: Int, bottom: Int) {
        val marginParams = backgroundTask.layoutParams as MarginLayoutParams
        marginParams.setMargins(left, top, right, bottom)
        backgroundTask.layoutParams = marginParams
    }

    fun setForegroundTaskMargin(left: Int, top: Int, right: Int, bottom: Int) {
        val marginParams = foregroundTask.layoutParams as MarginLayoutParams
        marginParams.setMargins(left, top, right, bottom)
        foregroundTask.layoutParams = marginParams
    }
}