package com.shalan.audioplayer.base.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter

abstract class BaseAdapter<Item : Any, Binding : ViewDataBinding, ViewHolder : BaseViewHolder<*, Item>>(
    diffUtil: DiffUtil.ItemCallback<Item>
) :
    ListAdapter<Item, ViewHolder>(diffUtil) {

    abstract val layoutId: Int

    abstract fun getViewHolder(binding: Binding): ViewHolder

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = DataBindingUtil.inflate<Binding>(
            LayoutInflater.from(parent.context),
            layoutId,
            parent,
            false
        )
        return getViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.init(getItem(position))
    }
}