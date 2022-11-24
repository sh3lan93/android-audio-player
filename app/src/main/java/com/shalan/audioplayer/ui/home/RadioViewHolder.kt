package com.shalan.audioplayer.ui.home

import com.shalan.audioplayer.base.adapter.BaseViewHolder
import com.shalan.audioplayer.databinding.RadioItemBinding
import com.shalan.audioplayer.network.respons.model.radios.Radio

class RadioViewHolder(binding: RadioItemBinding) :
    BaseViewHolder<RadioItemBinding, Radio>(binding) {

    override fun bind(item: Radio) {
        binding.radio = item
    }
}