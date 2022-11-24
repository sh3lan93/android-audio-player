package com.shalan.audioplayer.ui.countries_list

import com.shalan.audioplayer.base.adapter.BaseViewHolder
import com.shalan.audioplayer.databinding.CountryItemBinding
import com.shalan.audioplayer.network.respons.model.Country

class CountryViewHolder(
    binding: CountryItemBinding,
    val countryClickListener: (country: Country) -> Unit
) :
    BaseViewHolder<CountryItemBinding, Country>(binding) {

    override fun bind(item: Country) {
        binding.country = item
        binding.root.setOnClickListener {
            countryClickListener.invoke(item)
        }
    }
}