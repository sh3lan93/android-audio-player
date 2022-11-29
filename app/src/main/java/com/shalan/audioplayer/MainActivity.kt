package com.shalan.audioplayer

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.shalan.audioplayer.ui.auth.AuthFragmentDirections

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        (supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment).let {
            navController = it.navController
        }
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        val accessToken = getAccessToken(intent?.data?.fragment ?: "")
        navController.navigate(AuthFragmentDirections.actionAuthFragmentSelf(token = accessToken))
    }

    private fun getAccessToken(fragment: String): String? =
        fragment.split("&").firstOrNull()?.split("=")?.lastOrNull()
}