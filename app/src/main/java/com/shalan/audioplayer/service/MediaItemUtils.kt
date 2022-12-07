package com.shalan.audioplayer.service

import android.net.Uri
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.MediaMetadata

const val ROOT_MEDIA_ITEM_ID = "Radios"

const val ROOT_MEDIA_ITEM_TITLE = "Radios"

const val NOGOUM_FM_MEDIA_ITEM_ID = "NOGOUM-FM"

const val NOGOUM_FM_MEDIA_ITEM_TITLE = "Nogoum FM"

const val MEDIA_ITEM_ICON_URI = "https://www.nogoumfm.net/wp-content/uploads/2019/12/logo-nogoum-1.png"



val radioMediaItem = MediaItem.Builder()
    .setUri(Uri.parse("https://audio.nrpstream.com/listen/nogoumfm/radio.mp3"))
    .setMediaId(NOGOUM_FM_MEDIA_ITEM_ID)
    .setMediaMetadata(MediaMetadata.Builder()
        .setArtworkUri(Uri.parse(MEDIA_ITEM_ICON_URI))
        .setDescription(NOGOUM_FM_MEDIA_ITEM_TITLE)
        .setIsPlayable(true)
        .setTitle(NOGOUM_FM_MEDIA_ITEM_TITLE)
        .setSubtitle("Radio")
        .build())
    .build()