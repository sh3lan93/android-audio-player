package com.shalan.audioplayer.ui.countries_list

import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.shalan.audioplayer.R
import com.shalan.audioplayer.base.fragment.BaseSingleListFragment
import com.shalan.audioplayer.databinding.FragmentCountriesListBinding
import com.shalan.audioplayer.network.respons.model.Country
import org.koin.androidx.viewmodel.ext.android.viewModel


class CountriesListFragment :
    BaseSingleListFragment<FragmentCountriesListBinding, Country, CountriesListViewModel, CountriesAdapter>(
        layoutId = R.layout.fragment_countries_list
    ) {

    private val countriesAdapter: CountriesAdapter by lazy {
        CountriesAdapter()
    }

    override val viewmodel: CountriesListViewModel by viewModel()

    override fun onCreateInit(savedInstanceState: Bundle?) {

    }

    override fun getRecyclerView(): RecyclerView = binding.rvCountry

    override fun getAdapter(): CountriesAdapter = countriesAdapter

    override fun showLoading() {

    }


}