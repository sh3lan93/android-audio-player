package com.shalan.audioplayer.ui.countries_list

import androidx.recyclerview.widget.DiffUtil
import com.shalan.audioplayer.R
import com.shalan.audioplayer.base.adapter.BaseAdapter
import com.shalan.audioplayer.databinding.CountryItemBinding
import com.shalan.audioplayer.network.respons.model.Country

class CountriesAdapter :
    BaseAdapter<Country, CountryItemBinding, CountryViewHolder>(diffUtil = diffUtil) {

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<Country>() {
            override fun areItemsTheSame(oldItem: Country, newItem: Country): Boolean =
                oldItem.countryId == newItem.countryId

            override fun areContentsTheSame(oldItem: Country, newItem: Country): Boolean =
                oldItem == newItem

        }
    }

    override val layoutId: Int
        get() = R.layout.country_item

    override fun getViewHolder(binding: CountryItemBinding): CountryViewHolder =
        CountryViewHolder(binding)
}