package com.shalan.audioplayer.network

import com.shalan.audioplayer.BuildConfig

object NetworkingConstants {

    const val AUTHENTICATION_URL =
        "https://connect.deezer.com/oauth/auth.php?app_id=${BuildConfig.DEEZER_APP_ID}&redirect_uri=${BuildConfig.DEEZER_REDIRECTION_URL}&response_type=token"
}