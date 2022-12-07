package com.shalan.audioplayer.service

import android.content.Context
import android.media.AudioAttributes
import android.media.AudioFocusRequest
import android.media.AudioManager
import android.media.AudioManager.OnAudioFocusChangeListener
import android.os.Build

class MediaAudioManager(private val context: Context) {


    private lateinit var audioManager: AudioManager

    private lateinit var audioFocusRequest: AudioFocusRequest

    fun requestAudioFocus(
        audioFocusChangeListener: OnAudioFocusChangeListener,
        onAudioFocusRequestGranted: () -> Unit
    ) {
        if (::audioManager.isInitialized.not())
            audioManager = context.getSystemService(Context.AUDIO_SERVICE) as AudioManager

        if (::audioFocusRequest.isInitialized.not())
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                audioFocusRequest =
                    AudioFocusRequest.Builder(AudioManager.AUDIOFOCUS_GAIN_TRANSIENT_MAY_DUCK)
                        .setOnAudioFocusChangeListener(audioFocusChangeListener)
                        .setAudioAttributes(
                            AudioAttributes.Builder()
                                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC).build()
                        )
                        .build()
            }

        val result = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            audioManager.requestAudioFocus(audioFocusRequest)
        } else {
            audioManager.requestAudioFocus(
                audioFocusChangeListener,
                AudioManager.STREAM_MUSIC,
                AudioManager.AUDIOFOCUS_GAIN_TRANSIENT_MAY_DUCK
            )
        }

        if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
            onAudioFocusRequestGranted.invoke()
        }
    }

    fun abandonAudioFocus(audioFocusChangeListener: OnAudioFocusChangeListener) {
        if (::audioManager.isInitialized.not())
            audioManager = context.getSystemService(Context.AUDIO_SERVICE) as AudioManager

        if (::audioFocusRequest.isInitialized) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                audioManager.abandonAudioFocusRequest(audioFocusRequest)
            }
        } else {
            audioManager.abandonAudioFocus(audioFocusChangeListener)
        }
    }

    class MediaAudioFocusChangeListener: OnAudioFocusChangeListener{

        override fun onAudioFocusChange(focusChange: Int) {
            //TODO: Implement acting upon audio focus change listener
        }

    }
}