package com.shalan.audioplayer.ui.audio

import android.content.ComponentName
import android.media.AudioManager
import android.os.Bundle
import android.support.v4.media.MediaBrowserCompat
import android.support.v4.media.MediaMetadataCompat
import android.support.v4.media.session.MediaControllerCompat
import android.support.v4.media.session.PlaybackStateCompat
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import coil.load
import com.shalan.audioplayer.R
import com.shalan.audioplayer.databinding.FragmentAudioBinding
import com.shalan.audioplayer.service.MediaService
import com.shalan.audioplayer.ui.audio.model.Radio
import org.koin.android.ext.android.inject


class AudioFragment : AppCompatActivity() {

    companion object {
        val TAG = AudioFragment::class.java.simpleName
    }

    private lateinit var binding: FragmentAudioBinding

    private val radio by inject<Radio>()

    private lateinit var mediaBrowser: MediaBrowserCompat

    private val connectionCallback: MediaBrowserCompat.ConnectionCallback by lazy {
        object : MediaBrowserCompat.ConnectionCallback() {
            override fun onConnected() {
                super.onConnected()
                Log.d(TAG, "onConnected: ")
                mediaBrowser.sessionToken.also { token ->
                    val mediaController = MediaControllerCompat(this@AudioFragment, token)
                    MediaControllerCompat.setMediaController(this@AudioFragment, mediaController)
                }

                buildTransportControls()
            }

            override fun onConnectionFailed() {
                super.onConnectionFailed()
                Log.d(TAG, "onConnectionFailed: ")
            }

            override fun onConnectionSuspended() {
                super.onConnectionSuspended()
                Log.d(TAG, "onConnectionSuspended: ")
            }


        }
    }

    private val mediaControllerCallback: MediaControllerCompat.Callback by lazy {
        object : MediaControllerCompat.Callback() {


            override fun onSessionEvent(event: String?, extras: Bundle?) {
                Log.d(TAG, "onSessionEvent: $event")
            }
            override fun onSessionReady() {
                super.onSessionReady()
                Log.d(TAG, "onSessionReady: ")
            }


            override fun onMetadataChanged(metadata: MediaMetadataCompat?) {
                super.onMetadataChanged(metadata)
                Log.d(TAG, "onMetadataChanged: ")
            }

            override fun onPlaybackStateChanged(state: PlaybackStateCompat?) {
                super.onPlaybackStateChanged(state)
                Log.d(TAG, "onPlaybackStateChanged: ${state?.state}")
            }

            override fun onSessionDestroyed() {
                super.onSessionDestroyed()
                Log.d(TAG, "onSessionDestroyed: ")
                mediaBrowser.disconnect()
            }
        }
    }

    private fun buildTransportControls() {
        val mediaController = MediaControllerCompat.getMediaController(this)

        if (::binding.isInitialized){
            binding.ivPlayPauseIcon.setOnClickListener {
                val playbackState = mediaController.playbackState.state
                if (playbackState == PlaybackStateCompat.STATE_PLAYING)
                    mediaController?.transportControls?.pause()
                else
                    mediaController?.transportControls?.play()
            }

            binding.cvRadio.setOnClickListener {
                val playbackState = mediaController.playbackState.state
                if (playbackState != PlaybackStateCompat.STATE_PLAYING)
                    mediaController?.transportControls?.play()
                binding.ivPlayPauseIcon.setImageDrawable(
                    ContextCompat.getDrawable(
                        this,
                        R.drawable.ic_baseline_pause_circle_24
                    )
                )
                binding.tvNowPlaying.text = binding.tvRadioName.text
                binding.ivNowPlaying.load(binding.radio?.image)
                binding.gNowPlaying.isVisible = true
            }
        }


        val metadata = mediaController.metadata
        val playbackState = mediaController.playbackState


        mediaController.registerCallback(mediaControllerCallback)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.fragment_audio)
        binding.radio = radio

        mediaBrowser = MediaBrowserCompat(
            this,
            ComponentName(this, MediaService::class.java),
            connectionCallback,
            null
        )
    }

    override fun onStart() {
        super.onStart()
        mediaBrowser.connect()
    }

    override fun onResume() {
        super.onResume()
        volumeControlStream = AudioManager.STREAM_MUSIC
    }

    override fun onStop() {
        super.onStop()
        MediaControllerCompat.getMediaController(this)
            ?.unregisterCallback(mediaControllerCallback)
        mediaBrowser.disconnect()
    }
}