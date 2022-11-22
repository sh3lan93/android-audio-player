package com.shalan.audioplayer.base.adapter

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView.ViewHolder

abstract class BaseViewHolder<Binding : ViewDataBinding, Item : Any>(protected val binding: Binding) :
    ViewHolder(binding.root) {


    fun init(item: Item) {
        bind(item)
        binding.executePendingBindings()
    }

    abstract fun bind(item: Item)


}