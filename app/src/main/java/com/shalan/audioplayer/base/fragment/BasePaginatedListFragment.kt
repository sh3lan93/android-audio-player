package com.shalan.audioplayer.base.fragment

import android.os.Bundle
import androidx.annotation.CallSuper
import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding
import com.shalan.audioplayer.App
import com.shalan.audioplayer.base.adapter.BaseAdapter
import com.shalan.audioplayer.base.recyclerview.PaginationListener
import com.shalan.audioplayer.base.ui.Loading
import com.shalan.audioplayer.base.viewmodel.BasePaginatedListViewModel

abstract class BasePaginatedListFragment<Binding : ViewDataBinding, Model : Any, ViewModel : BasePaginatedListViewModel<Model>, Adapter : BaseAdapter<Model, *, *>>(
    @LayoutRes layoutId: Int
) :
    BaseSingleListFragment<Binding, Model, ViewModel, Adapter>(layoutId = layoutId) {

    private val loadMoreListener: () -> Unit = {
        viewmodel.loadMoreData()
    }

    @CallSuper
    override fun onCreateInit(savedInstanceState: Bundle?) {
        getRecyclerView().addOnScrollListener(object : PaginationListener(loadMoreListener) {
            override var isLoading: Boolean = viewmodel.listResult.value is Loading

            override val pageSize: Long
                get() = App.PAGINATION_PAGE_SIZE

            override fun isLastPage(): Boolean = this@BasePaginatedListFragment.isLastPage()

        })
    }

    abstract fun isLastPage(): Boolean

}