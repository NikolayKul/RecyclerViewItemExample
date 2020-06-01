package com.nikolaykul.recyclerviewitemexample.viewitem.book

import androidx.recyclerview.widget.LinearLayoutManager
import com.nikolaykul.recyclerviewitemexample.R
import com.nikolaykul.recyclerviewitemexample.databinding.BookItemGalleryBinding
import com.nikolaykul.recyclerviewitemexample.decorations.SpaceItemDecoration
import com.nikolaykul.recyclerviewitemexample.viewitem.base.ViewItemRecyclerAdapter
import com.nikolaykul.recyclerviewitemexample.viewitem.base.ViewItemViewHolder

class BookViewItemGalleryViewHolder(binding: BookItemGalleryBinding) :
    ViewItemViewHolder<BookItemGalleryBinding>(binding) {

    val galleryAdapter = ViewItemRecyclerAdapter()

    init {
        with(binding.bookGalley) {
            adapter = galleryAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

            val space = resources.getDimensionPixelSize(R.dimen.margin_normal)
            addItemDecoration(
                SpaceItemDecoration(space, SpaceItemDecoration.Orientation.HORIZONTAL)
            )
        }
    }
}