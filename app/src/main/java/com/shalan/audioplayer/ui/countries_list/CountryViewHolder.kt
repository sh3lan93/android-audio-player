package com.shalan.audioplayer.ui.countries_list

import com.shalan.audioplayer.base.adapter.BaseViewHolder
import com.shalan.audioplayer.databinding.CountryItemBinding
import com.shalan.audioplayer.network.respons.model.Country

class CountryViewHolder(binding: CountryItemBinding) :
    BaseViewHolder<CountryItemBinding, Country>(binding) {

    override fun bind(item: Country) {
        binding.country = item
    }
}