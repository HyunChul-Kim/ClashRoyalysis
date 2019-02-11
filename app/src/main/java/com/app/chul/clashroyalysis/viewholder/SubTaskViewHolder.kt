package com.app.chul.clashroyalysis.viewholder

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.app.chul.clashroyalysis.R

open class SubTaskViewHolder: RecyclerView.ViewHolder{
    private val backgroundTask: LinearLayout = itemView.findViewById(R.id.background_task)
    private val foregroundTask: LinearLayout = itemView.findViewById(R.id.foreground_task)


    constructor(parent: ViewGroup): super(LayoutInflater.from(parent.context).inflate(R.layout.view_sub_task, parent, false))

    fun getBackgroundTask() = backgroundTask

    fun getForegroundTask() = foregroundTask

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

    fun getBackgroundTaskWithTag(tag: String): View {
        return backgroundTask.findViewWithTag(tag)
    }

    fun getForegroundTaskWithTag(tag: String): View {
        return foregroundTask.findViewWithTag(tag)
    }
}