package com.shalan.audioplayer.base.fragment

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.shalan.audioplayer.R
import com.shalan.audioplayer.base.adapter.BaseAdapter
import com.shalan.audioplayer.base.ui.Failure
import com.shalan.audioplayer.base.ui.Loading
import com.shalan.audioplayer.base.ui.Success
import com.shalan.audioplayer.base.viewmodel.BaseSingleListViewModel

abstract class BaseSingleListFragment<Binding : ViewDataBinding, Model : Any, ViewModel : BaseSingleListViewModel<Model>, Adapter : BaseAdapter<Model, *, *>>(
    @LayoutRes layoutId: Int
) : Fragment(layoutId), IListFragment<Model> {

    abstract val viewmodel: ViewModel

    lateinit var binding: Binding

    @Suppress("UNCHECKED_CAST")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = DataBindingUtil.bind(view)!!
        onCreateInit(savedInstanceState)
        viewmodel.startLogic()
        getRecyclerView().adapter = getAdapter()

        viewmodel.listResult.observe(viewLifecycleOwner) { uiState ->
            when (uiState) {
                is Failure -> showError(uiState.error ?: getString(R.string.something_wen_wrong))
                is Loading -> showLoading()
                is Success -> showData(uiState.data ?: emptyList())
            }
        }
    }

    abstract fun onCreateInit(savedInstanceState: Bundle?)

    abstract fun getRecyclerView(): RecyclerView

    abstract fun getAdapter(): Adapter

    override fun showData(data: List<Model>) {
        hideLoading()
        getAdapter().submitList(data)
    }

    override fun showError(error: String) {
        hideLoading()
        Toast.makeText(requireContext(), error, Toast.LENGTH_SHORT).show()
    }

}