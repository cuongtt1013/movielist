package com.example.movielist.share

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class SpacesItemDecoration : RecyclerView.ItemDecoration {
    private val top: Int
    private val right: Int
    private val bottom: Int
    private val left: Int
    private var topMultiply: Int = 1
    private var bottomMultiply: Int = 1
    private var leftMultiply: Int = 1
    private var rightMultiply: Int = 1


    constructor(space: Int) : super() {
        this.top = space
        this.right = space
        this.bottom = space
        this.left = space
    }

    constructor(
        top: Int,
        right: Int,
        bottom: Int,
        left: Int,
        topMultiply: Int = 1,
        bottomMultiply: Int = 1,
        leftMultiply: Int = 1,
        rightMultiply: Int = 1,
    ) : super() {
        this.top = top
        this.right = right
        this.bottom = bottom
        this.left = left
        this.topMultiply = topMultiply
        this.bottomMultiply = bottomMultiply
        this.leftMultiply = leftMultiply
        this.rightMultiply = rightMultiply
    }


    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        outRect.right = right
        outRect.bottom = bottom
        outRect.top = top
        outRect.left = left
        val itemPosition = parent.getChildPosition(view)
        val itemCount = parent.adapter?.itemCount ?: 0
        if (itemPosition == 0) {
            outRect.top = top * topMultiply
            outRect.left = left * leftMultiply
        } else if (itemPosition == itemCount - 1) {
            outRect.bottom = bottom * bottomMultiply
            outRect.right = right * rightMultiply
        }
    }
}