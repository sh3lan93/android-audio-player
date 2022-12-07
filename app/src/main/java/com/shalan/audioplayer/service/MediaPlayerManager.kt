package com.shalan.audioplayer.service

import android.content.Context
import android.util.Log
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.MediaMetadata
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.metadata.Metadata

class MediaPlayerManager(private val context: Context) {

    companion object {
        val TAG = MediaPlayerManager::class.java.simpleName
    }

    private lateinit var exoPlayer: ExoPlayer

    fun start(mediaItem: MediaItem) {
        if (::exoPlayer.isInitialized.not()) {
            exoPlayer = ExoPlayer.Builder(context)
                .build()
        }
        exoPlayer.addListener(object : Player.Listener {
            override fun onMetadata(metadata: Metadata) {
                Log.d(TAG, "onMetadata: ")
            }

            override fun onMediaMetadataChanged(mediaMetadata: MediaMetadata) {
                Log.d(TAG, "onMediaMetadataChanged: ${mediaMetadata.title}")
            }

            override fun onPlaybackStateChanged(playbackState: Int) {
                Log.d(TAG, "onPlaybackStateChanged: $playbackState")
            }


        })
        exoPlayer.setMediaItem(mediaItem)
        exoPlayer.setForegroundMode(true)
        exoPlayer.prepare()
        exoPlayer.play()
    }

    fun pause() {
        if (::exoPlayer.isInitialized)
            exoPlayer.pause()
    }

    fun stop() {
        if (::exoPlayer.isInitialized)
            exoPlayer.stop()
    }

}