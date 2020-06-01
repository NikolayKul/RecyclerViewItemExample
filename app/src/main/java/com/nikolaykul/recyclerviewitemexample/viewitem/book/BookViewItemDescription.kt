package com.nikolaykul.recyclerviewitemexample.viewitem.book

import android.view.View
import androidx.annotation.ColorInt
import com.nikolaykul.recyclerviewitemexample.Book
import com.nikolaykul.recyclerviewitemexample.R
import com.nikolaykul.recyclerviewitemexample.databinding.BookItemDescriptionBinding
import com.nikolaykul.recyclerviewitemexample.utils.ColorUtils
import com.nikolaykul.recyclerviewitemexample.viewitem.base.ViewItem
import com.nikolaykul.recyclerviewitemexample.viewitem.base.ViewItemViewHolder

class BookViewItemDescription(
    private val book: Book
) : ViewItem<ViewItemViewHolder<BookItemDescriptionBinding>> {
    @ColorInt
    private val color = ColorUtils.random()

    override val layoutId = R.layout.book_item_description

    override fun createViewHolder(itemView: View): ViewItemViewHolder<BookItemDescriptionBinding> {
        return ViewItemViewHolder(
            BookItemDescriptionBinding.bind(itemView)
        )
    }

    override fun bind(holder: ViewItemViewHolder<BookItemDescriptionBinding>) {
        with(holder.binding) {
            title.text = book.title
            subtitle.text = book.subtitle
            colorLoadingView.loadColor(color)
        }
    }

    override fun unbind(holder: ViewItemViewHolder<BookItemDescriptionBinding>) {
        holder.binding.colorLoadingView.cancelLoading()
    }

    override fun isTheSameItem(other: ViewItem<*>): Boolean {
        return other is BookViewItemDescription && book.id == other.book.id
    }

    override fun isTheSameContent(other: ViewItem<*>): Boolean {
        return other is BookViewItemDescription && book == other.book
    }
}