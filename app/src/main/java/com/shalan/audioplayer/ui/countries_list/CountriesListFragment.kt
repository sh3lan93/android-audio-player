package com.shalan.audioplayer.ui.countries_list

import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.core.widget.addTextChangedListener
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
        CountriesAdapter(countryClickListener)
    }

    override val viewmodel: CountriesListViewModel by viewModel()

    private val countryClickListener: (country: Country) -> Unit = { country ->
        viewmodel.saveUserSelectedCountry(country)
    }

    override fun onCreateInit(savedInstanceState: Bundle?) {

        binding.etSearch.addTextChangedListener(onTextChanged = { text, _, _, _ ->
            viewmodel.listResult.value?.data?.let {
                when {
                    text?.toString()
                        ?.isNotEmpty() == true -> it.filter {
                        it.countryName.lowercase().contains(text.toString().lowercase())
                    }.also {
                        countriesAdapter.submitList(it)
                    }
                    else ->
                        countriesAdapter.submitList(it)
                }
            }
        })

        viewmodel.navigateToHome.observe(viewLifecycleOwner) {

        }
    }

    override fun getRecyclerView(): RecyclerView = binding.rvCountry

    override fun getAdapter(): CountriesAdapter = countriesAdapter

    override fun showLoading() {
        binding.gContent.visibility = GONE
        binding.loading.visibility = VISIBLE
    }

    override fun hideLoading() {
        binding.gContent.visibility = VISIBLE
        binding.loading.visibility = GONE
    }


}