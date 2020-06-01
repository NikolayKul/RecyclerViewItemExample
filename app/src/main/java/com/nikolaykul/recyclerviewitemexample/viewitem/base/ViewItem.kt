package com.nikolaykul.recyclerviewitemexample.viewitem.base

import android.view.View
import androidx.annotation.LayoutRes
import androidx.viewbinding.ViewBinding

interface ViewItem<T : ViewItemViewHolder<out ViewBinding>> : DeepComparable<ViewItem<*>> {
    @get:LayoutRes
    val layoutId: Int

    fun createViewHolder(itemView: View): T

    fun bind(holder: T)

    fun unbind(holder: T) {
        // may clear its state
    }
}
