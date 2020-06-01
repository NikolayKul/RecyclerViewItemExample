package com.nikolaykul.recyclerviewitemexample.decorations

import android.graphics.Canvas
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.view.View
import androidx.core.graphics.withSave
import androidx.recyclerview.widget.RecyclerView

class DividerItemDecoration(
    private val divider: Drawable
) : RecyclerView.ItemDecoration() {

    private val childBounds = Rect()
    private val dividerPadding = Rect().also { divider.getPadding(it) }

    override fun onDraw(
        c: Canvas,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val childCountToDraw = parent.childCount - 1    // don't draw divider for the last child
        if (childCountToDraw < 1) {
            return
        }

        c.withSave {
            for (i in 0 until childCountToDraw) {
                drawDividerForChild(c, parent, parent.getChildAt(i))
            }
        }
    }

    private fun drawDividerForChild(c: Canvas, parent: RecyclerView, child: View) {
        parent.getDecoratedBoundsWithMargins(child, childBounds)

        val left = dividerPadding.left
        val right = parent.width - dividerPadding.right
        val bottom = childBounds.bottom + child.translationY.toInt()
        val top = bottom - divider.intrinsicHeight

        divider.setBounds(left, top, right, bottom)
        divider.draw(c)
    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        outRect.set(0, 0, 0, divider.intrinsicHeight)
    }
}
