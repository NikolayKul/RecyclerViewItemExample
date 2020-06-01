package com.nikolaykul.recyclerviewitemexample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nikolaykul.recyclerviewitemexample.databinding.ActivityMainBinding
import com.nikolaykul.recyclerviewitemexample.decorations.DividerItemDecoration
import com.nikolaykul.recyclerviewitemexample.decorations.SpaceItemDecoration
import com.nikolaykul.recyclerviewitemexample.viewitem.base.ViewItemRecyclerAdapter

class MainActivity : AppCompatActivity() {

    private val bookListAdapter = ViewItemRecyclerAdapter()
    private val booksProvider = BooksProvider()

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initBookList()
        showBooks()
    }

    private fun initBookList() {
        with(binding.bookList) {
            adapter = bookListAdapter
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)

            getItemDecorations().forEach(::addItemDecoration)
        }
    }

    private fun getItemDecorations(): List<RecyclerView.ItemDecoration> {
        return listOf(
            run {
                val space = resources.getDimensionPixelSize(R.dimen.margin_normal)
                SpaceItemDecoration(space, SpaceItemDecoration.Orientation.VERTICAL)
            },
            run {
                val divider = ContextCompat.getDrawable(this, R.drawable.vertical_divider)!!
                DividerItemDecoration(divider)
            }
        )
    }

    private fun showBooks() {
        val books = booksProvider.getBookViewItems(
            singleBooksCount = 30,
            galleryBooksCount = 15,
            galleryPosition = 10
        )
        bookListAdapter.setCovariantItems(books)
    }
}
