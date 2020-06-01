package com.nikolaykul.recyclerviewitemexample

import androidx.viewbinding.ViewBinding
import com.nikolaykul.recyclerviewitemexample.viewitem.base.ViewItem
import com.nikolaykul.recyclerviewitemexample.viewitem.base.ViewItemViewHolder
import com.nikolaykul.recyclerviewitemexample.viewitem.book.BookViewItemDescription
import com.nikolaykul.recyclerviewitemexample.viewitem.book.BookViewItemGallery

typealias CovariantBookViewItem = ViewItem<out ViewItemViewHolder<out ViewBinding>>

class BooksProvider {

    fun getBookViewItems(
        singleBooksCount: Int,
        galleryBooksCount: Int,
        galleryPosition: Int
    ): List<CovariantBookViewItem> {
        val items: MutableList<CovariantBookViewItem> =
            (1..singleBooksCount).mapTo(mutableListOf(), this::createSingleViewItem)

        if (galleryPosition in items.indices) {
            items.add(galleryPosition, createGalleryViewItems(galleryBooksCount))
        }

        return items
    }

    private fun createSingleViewItem(position: Int): BookViewItemDescription {
        val book = createBookDescription(position)
        return BookViewItemDescription(book)
    }

    private fun createGalleryViewItems(count: Int): BookViewItemGallery {
        val books = (1..count).map(this::createBookPreview)
        return BookViewItemGallery(books)
    }

    private fun createBookDescription(id: Int) = createBook(id, "My shiny new title: $id")

    private fun createBookPreview(id: Int) = createBook(id, "My $id\nShiny title")

    private fun createBook(id: Int, title: String): Book {
        return Book(
            "$id",
            title,
            "And this is lorem ipsum for my shiny $id new extra title subtitle"
        )
    }
}
