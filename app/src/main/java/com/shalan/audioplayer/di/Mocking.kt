package com.shalan.audioplayer.di

import com.shalan.audioplayer.ui.audio.model.Radio
import org.koin.dsl.module

val mockingModule = module {
    factory {
        Radio(
            name = "Nogoum FM",
            image = "https://www.nogoumfm.net/wp-content/uploads/2019/12/logo-nogoum-1.png",
            url = "https://audio.nrpstream.com/listen/nogoumfm/radio.mp3"
        )
    }
}