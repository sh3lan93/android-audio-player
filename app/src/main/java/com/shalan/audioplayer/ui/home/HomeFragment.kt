package com.shalan.audioplayer.ui.home

import android.os.Bundle
import androidx.core.view.isVisible
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shalan.audioplayer.R
import com.shalan.audioplayer.base.fragment.BasePaginatedListFragment
import com.shalan.audioplayer.base.recyclerview.GridSpacingItemDecoration
import com.shalan.audioplayer.databinding.FragmentHomeBinding
import com.shalan.audioplayer.network.respons.model.radios.Radio
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment :
    BasePaginatedListFragment<FragmentHomeBinding, Radio, HomeViewModel, RadiosAdapter>(layoutId = R.layout.fragment_home) {

    private val radioAdapter: RadiosAdapter by lazy {
        RadiosAdapter()
    }

    override fun isLastPage(): Boolean = viewmodel.isLastPageReached()

    override val viewmodel: HomeViewModel by viewModel()

    override fun getRecyclerView(): RecyclerView = binding.rvRadios

    override fun getAdapter(): RadiosAdapter = radioAdapter

    override fun showLoading() {
        binding.loading.isVisible = true
        binding.rvRadios.isVisible = false
    }

    override fun hideLoading() {
        binding.loading.isVisible = false
        binding.rvRadios.isVisible = true
    }

    override fun onCreateInit(savedInstanceState: Bundle?) {
        super.onCreateInit(savedInstanceState)
        getRecyclerView().layoutManager = GridLayoutManager(requireContext(), 3)
        getRecyclerView().addItemDecoration(
            GridSpacingItemDecoration(
                spanCount = 3,
                spacing = 50,
                includeEdge = true
            )
        )
    }

}