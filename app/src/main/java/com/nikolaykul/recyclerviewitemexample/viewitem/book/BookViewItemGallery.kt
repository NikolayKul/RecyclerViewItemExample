package com.nikolaykul.recyclerviewitemexample.viewitem.book

import android.view.View
import com.nikolaykul.recyclerviewitemexample.Book
import com.nikolaykul.recyclerviewitemexample.R
import com.nikolaykul.recyclerviewitemexample.databinding.BookItemGalleryBinding
import com.nikolaykul.recyclerviewitemexample.viewitem.base.ViewItem

class BookViewItemGallery(
    private val books: List<Book>
) : ViewItem<BookViewItemGalleryViewHolder> {

    override val layoutId = R.layout.book_item_gallery

    override fun createViewHolder(itemView: View): BookViewItemGalleryViewHolder {
        return BookViewItemGalleryViewHolder(
            BookItemGalleryBinding.bind(itemView)
        )
    }

    override fun bind(holder: BookViewItemGalleryViewHolder) {
        val previews = books.map(::BookViewItemPreview)
        holder.galleryAdapter.setCovariantItems(previews)
    }

    override fun unbind(holder: BookViewItemGalleryViewHolder) {
        holder.galleryAdapter.items = emptyList()
    }

    override fun isTheSameItem(other: ViewItem<*>): Boolean {
        return other is BookViewItemGallery && hasSameIdsInOrder(books, other.books)
    }

    private fun hasSameIdsInOrder(list1: List<Book>, list2: List<Book>): Boolean {
        if (list1.size != list2.size) {
            return false
        }
        return list1.asSequence()
            .zip(list2.asSequence())
            .all { (lhs, rhs) -> lhs.id == rhs.id }
    }

    override fun isTheSameContent(other: ViewItem<*>): Boolean {
        return other is BookViewItemGallery && books == other.books
    }
}