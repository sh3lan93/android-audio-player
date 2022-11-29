package com.shalan.audioplayer.ui.home

import androidx.recyclerview.widget.DiffUtil
import com.shalan.audioplayer.R
import com.shalan.audioplayer.base.adapter.BaseAdapter
import com.shalan.audioplayer.databinding.RadioItemBinding
import com.shalan.audioplayer.network.respons.model.radios.Radio

class RadiosAdapter : BaseAdapter<Radio, RadioItemBinding, RadioViewHolder>(diffUtil = diffUtil) {

    companion object {

        val diffUtil = object : DiffUtil.ItemCallback<Radio>() {
            override fun areItemsTheSame(oldItem: Radio, newItem: Radio): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Radio, newItem: Radio): Boolean =
                oldItem == newItem

        }
    }

    override val layoutId: Int
        get() = R.layout.radio_item

    override fun getViewHolder(binding: RadioItemBinding): RadioViewHolder =
        RadioViewHolder(binding)
}