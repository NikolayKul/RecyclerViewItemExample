package com.nikolaykul.recyclerviewitemexample.viewitem.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

typealias ITEM = ViewItem<ViewItemViewHolder<ViewBinding>>
typealias VIEW_HOLDER = ViewItemViewHolder<ViewBinding>

class ViewItemRecyclerAdapter : RecyclerView.Adapter<VIEW_HOLDER>() {
    private val differ = AsyncListDiffer<ITEM>(this, DeepComparableDiffItemCallback())

    private var lastItemForViewTypeLookup: ITEM? = null

    var items: List<ITEM>
        get() = differ.currentList
        set(value) {
            differ.submitList(value)
        }

    fun setCovariantItems(newItems: List<ViewItem<out ViewItemViewHolder<out ViewBinding>>>) {
        // we guarantee that `ViewItem` will always match its `ViewHolder` in `onBindViewHolder`
        items = newItems as List<ITEM>
    }

    override fun onCreateViewHolder(parent: ViewGroup, layoutId: Int): VIEW_HOLDER {
        val itemView = LayoutInflater.from(parent.context).inflate(layoutId, parent, false)
        val item = getItemForViewType(layoutId)
        return item.createViewHolder(itemView)
    }

    override fun getItemViewType(position: Int): Int {
        lastItemForViewTypeLookup = items[position]
        return lastItemForViewTypeLookup!!.layoutId
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: VIEW_HOLDER, position: Int) {
        holder.bind(items[position])
    }

    override fun onViewRecycled(holder: VIEW_HOLDER) {
        holder.unbind()
    }

    /**
     * This idea was copied from Groupie/Epoxy :)
     */
    private fun getItemForViewType(layoutId: Int): ITEM {
        // Must have 100% hit rate
        val lookup = lastItemForViewTypeLookup?.takeIf { it.layoutId == layoutId }
        return lookup ?: items.first { it.layoutId == layoutId }
    }
}