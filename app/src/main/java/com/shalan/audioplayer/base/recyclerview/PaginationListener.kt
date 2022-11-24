package com.shalan.audioplayer.base.recyclerview

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

abstract class PaginationListener(private val loadMoreListener: () -> Unit) :
    RecyclerView.OnScrollListener() {

    abstract var isLoading: Boolean

    abstract val pageSize: Long

    abstract fun isLastPage(): Boolean

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        (recyclerView.layoutManager as LinearLayoutManager).let { layoutManager ->

            val visibleItemsCount = layoutManager.childCount
            val totalItemsCount = layoutManager.itemCount
            val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()

            if (isLoading.not() && isLastPage().not()) {
                if ((visibleItemsCount + firstVisibleItemPosition) >= totalItemsCount - 5
                    && firstVisibleItemPosition >= 0
                    && totalItemsCount >= pageSize
                ) {
                    loadMoreListener.invoke()
                }
            }
        }
    }
}