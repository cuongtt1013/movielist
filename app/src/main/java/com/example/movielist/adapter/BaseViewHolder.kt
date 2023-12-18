package com.example.movielist.adapter

import android.content.Context
import android.content.res.Resources
import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder<in T>(itemView: View) : RecyclerView.ViewHolder(itemView) {
    protected val context: Context = itemView.context
    protected val resources: Resources = itemView.resources

    open fun bind(data: T) {}
    open fun bind(data: T, type: Int) {}
}