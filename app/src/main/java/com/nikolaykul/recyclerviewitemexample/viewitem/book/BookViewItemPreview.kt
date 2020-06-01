package com.nikolaykul.recyclerviewitemexample.viewitem.book

import android.view.View
import androidx.annotation.ColorInt
import com.nikolaykul.recyclerviewitemexample.Book
import com.nikolaykul.recyclerviewitemexample.R
import com.nikolaykul.recyclerviewitemexample.databinding.BookItemPreviewBinding
import com.nikolaykul.recyclerviewitemexample.utils.ColorUtils
import com.nikolaykul.recyclerviewitemexample.viewitem.base.ViewItem
import com.nikolaykul.recyclerviewitemexample.viewitem.base.ViewItemViewHolder

class BookViewItemPreview(
    private val book: Book
) : ViewItem<ViewItemViewHolder<BookItemPreviewBinding>> {
    @ColorInt
    private val color = ColorUtils.random()

    override val layoutId = R.layout.book_item_preview

    override fun createViewHolder(itemView: View): ViewItemViewHolder<BookItemPreviewBinding> {
        return ViewItemViewHolder(
            BookItemPreviewBinding.bind(itemView)
        )
    }

    override fun bind(holder: ViewItemViewHolder<BookItemPreviewBinding>) {
        with(holder.binding) {
            title.text = book.title
            colorLoadingView.loadColor(color)
        }
    }

    override fun unbind(holder: ViewItemViewHolder<BookItemPreviewBinding>) {
        holder.binding.colorLoadingView.cancelLoading()
    }

    override fun isTheSameItem(other: ViewItem<*>): Boolean {
        return other is BookViewItemPreview && book.id == other.book.id
    }

    override fun isTheSameContent(other: ViewItem<*>): Boolean {
        return other is BookViewItemPreview && book == book
    }
}