package com.nikolaykul.recyclerviewitemexample.decorations

import android.graphics.Rect
import android.view.View
import androidx.annotation.Px
import androidx.recyclerview.widget.RecyclerView

class SpaceItemDecoration(
    @Px space: Int,
    private val orientation: Orientation
) : RecyclerView.ItemDecoration() {

    private val halfSpace = space / 2

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val position = parent.getChildLayoutPosition(view)

        var start = halfSpace
        if (position == 0) {
            start = 0
        }

        var finish = halfSpace
        if (position == parent.adapter!!.itemCount - 1) {
            finish = 0
        }

        when (orientation) {
            Orientation.VERTICAL -> outRect.set(0, start, 0, finish)
            Orientation.HORIZONTAL -> outRect.set(start, 0, finish, 0)
        }
    }

    enum class Orientation { VERTICAL, HORIZONTAL }
}
