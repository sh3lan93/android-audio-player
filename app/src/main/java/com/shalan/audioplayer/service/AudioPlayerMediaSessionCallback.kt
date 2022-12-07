package com.shalan.audioplayer.service

import android.content.Context
import android.support.v4.media.session.MediaSessionCompat
import android.util.Log

class AudioPlayerMediaSessionCallback(
    private val context: Context,
    private val mediaSessionCompat: MediaSessionCompat?,
    private val onPlay: () -> Unit,
    private val onStop: (audioManager: MediaAudioManager, audioFocusChangeListener: MediaAudioManager.MediaAudioFocusChangeListener, playerManager: MediaPlayerManager) -> Unit
) :
    MediaSessionCompat.Callback() {

    companion object {
        val TAG = AudioPlayerMediaSessionCallback::class.java.simpleName
    }

    private val mediaAudioManager: MediaAudioManager by lazy {
        MediaAudioManager(context = context)
    }

    private val mediaPlayerManager: MediaPlayerManager by lazy {
        MediaPlayerManager(context = context)
    }


    private val audioFocusChangeListener: MediaAudioManager.MediaAudioFocusChangeListener by lazy {
        MediaAudioManager.MediaAudioFocusChangeListener()
    }

    override fun onPlay() {
        Log.d(TAG, "onPlay: ")
        mediaAudioManager.requestAudioFocus(audioFocusChangeListener) {
            MediaService.start(context)

            mediaSessionCompat?.isActive = true

            mediaPlayerManager.start(radioMediaItem)

            onPlay.invoke()
        }

    }

    override fun onPause() {
        Log.d(TAG, "onPause: ")

    }

    override fun onStop() {
        Log.d(TAG, "onStop: ")
        onStop.invoke(mediaAudioManager, audioFocusChangeListener, mediaPlayerManager)

    }

}
