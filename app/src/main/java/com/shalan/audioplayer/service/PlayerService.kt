package com.shalan.audioplayer.service

import android.content.Intent
import android.media.browse.MediaBrowser
import android.media.session.MediaSession
import android.media.session.PlaybackState
import android.os.Bundle
import android.service.media.MediaBrowserService

class PlayerService : MediaBrowserService() {

    private var mediaSession: MediaSession? = null
    private val playerStateBuilder: PlaybackState.Builder by lazy {
        PlaybackState.Builder()
            .setActions(PlaybackState.ACTION_PLAY or PlaybackState.ACTION_PLAY_PAUSE)
    }
    private val mediaSessionCallback: PlayMediaSessionCallback by lazy {
        PlayMediaSessionCallback()
    }

    companion object {
        const val ROOT_ID = "root"
        const val EMPTY_ROOT_ID = ""
        val TAG = PlayerService::class.java.simpleName
    }

    override fun onCreate() {
        super.onCreate()

        mediaSession = MediaSession(baseContext, TAG).apply {
            isActive = true
            setPlaybackState(playerStateBuilder.build())
            setCallback(mediaSessionCallback)
        }

        mediaSession?.let {
            sessionToken = it.sessionToken
        }
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onGetRoot(
        clientPackageName: String,
        clientUid: Int,
        rootHints: Bundle?
    ): BrowserRoot = BrowserRoot(ROOT_ID, null)

    override fun onLoadChildren(
        parentId: String,
        result: Result<MutableList<MediaBrowser.MediaItem>>
    ) {
        result.sendResult(mutableListOf())
    }
}