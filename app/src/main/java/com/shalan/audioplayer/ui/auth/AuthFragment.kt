package com.shalan.audioplayer.ui.auth

import android.os.Bundle
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.net.toUri
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.shalan.audioplayer.R
import com.shalan.audioplayer.base.fragment.BaseFragment
import com.shalan.audioplayer.databinding.FragmentAuthBinding
import com.shalan.audioplayer.network.NetworkingConstants
import org.koin.androidx.viewmodel.ext.android.viewModel


class AuthFragment :
    BaseFragment<FragmentAuthBinding, AuthViewModel>(layoutId = R.layout.fragment_auth) {

    private val args by navArgs<AuthFragmentArgs>()

    override val viewModel: AuthViewModel by viewModel()

    override fun onCreateInit(savedInstanceState: Bundle?) {

        if (args.token != null)
            viewModel.saveUserToken(args.token!!)

        binding.btnAuth.isEnabled = args.token == null

        binding.btnAuth.setOnClickListener {
            authUser()
        }

        viewModel.navigateToHome.observe(viewLifecycleOwner) {
            if (it)
                findNavController().navigate(R.id.action_authFragment_to_homeFragment)
        }

    }

    private fun authUser() {
        CustomTabsIntent.Builder().build()
            .launchUrl(requireContext(), NetworkingConstants.AUTHENTICATION_URL.toUri())
    }

}