package com.nikolaykul.recyclerviewitemexample.viewitem.base

import androidx.recyclerview.widget.DiffUtil

interface DeepComparable<in T> {
    fun isTheSameItem(other: T): Boolean

    fun isTheSameContent(other: T): Boolean
}

class DeepComparableDiffItemCallback<T : DeepComparable<T>> : DiffUtil.ItemCallback<T>() {
    override fun areItemsTheSame(oldItem: T, newItem: T) = oldItem.isTheSameItem(newItem)

    override fun areContentsTheSame(oldItem: T, newItem: T) = oldItem.isTheSameContent(newItem)
}

class DeepComparableDiffCallback<T : DeepComparable<T>>(
    private val oldList: List<T>,
    private val newList: List<T>
) : DiffUtil.Callback() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].isTheSameItem(newList[newItemPosition])
    }

    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].isTheSameContent(newList[newItemPosition])
    }
}
