package com.nikolaykul.recyclerviewitemexample.viewitem.base

import androidx.annotation.CallSuper
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

open class ViewItemViewHolder<T : ViewBinding>(
    val binding: T
) : RecyclerView.ViewHolder(binding.root) {

    var bindedViewItem: ViewItem<ViewItemViewHolder<T>>? = null

    @CallSuper
    open fun bind(viewItem: ViewItem<ViewItemViewHolder<T>>) {
        bindedViewItem = viewItem
        viewItem.bind(this)
    }

    @CallSuper
    open fun unbind() {
        bindedViewItem?.unbind(this)
        bindedViewItem = null
    }
}
