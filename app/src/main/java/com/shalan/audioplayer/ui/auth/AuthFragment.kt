package com.shalan.audioplayer.ui.auth

import android.os.Bundle
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.net.toUri
import com.shalan.audioplayer.R
import com.shalan.audioplayer.base.fragment.BaseFragment
import com.shalan.audioplayer.databinding.FragmentAuthBinding
import com.shalan.audioplayer.network.NetworkingConstants
import org.koin.androidx.viewmodel.ext.android.viewModel


class AuthFragment :
    BaseFragment<FragmentAuthBinding, AuthViewModel>(layoutId = R.layout.fragment_auth) {

    override val viewModel: AuthViewModel by viewModel()

    override fun onCreateInit(savedInstanceState: Bundle?) {

        binding.btnAuth.setOnClickListener {
            authUser()
        }
    }

    private fun authUser() {
        CustomTabsIntent.Builder().build()
            .launchUrl(requireContext(), NetworkingConstants.AUTHENTICATION_URL.toUri())
    }

}