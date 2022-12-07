package com.shalan.audioplayer.service

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.ServiceInfo
import android.media.AudioManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.support.v4.media.MediaBrowserCompat
import android.support.v4.media.MediaDescriptionCompat
import android.support.v4.media.session.MediaSessionCompat
import android.support.v4.media.session.PlaybackStateCompat
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.media.MediaBrowserServiceCompat
import androidx.media.utils.MediaConstants


private const val MY_MEDIA_ROOT_ID = "media_root_id"
private const val EMPTY_MEDIA_ROOT_ID = "empty_root_id"

class MediaService : MediaBrowserServiceCompat() {


    companion object {
        val TAG = MediaService::class.java.simpleName
        val FOREGROUND_SERVICE_ID = 101

        @JvmStatic
        fun start(context: Context) {
            context.startService(Intent(context, MediaService::class.java))
        }
    }

    private val noisyReceiver: BecomingNoisyReceiver = BecomingNoisyReceiver()

    private val onStop: (
        audioManager: MediaAudioManager,
        audioFocusChangeListener: MediaAudioManager.MediaAudioFocusChangeListener,
        playerManager: MediaPlayerManager
    ) -> Unit = { audioManager, audioFocusChangeListener, playerManager ->
        audioManager.abandonAudioFocus(audioFocusChangeListener)
        unregisterReceiver(noisyReceiver)
        stopSelf()
        mediaSession?.isActive = false
        playerManager.stop()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            stopForeground(STOP_FOREGROUND_DETACH)
        } else {
            stopForeground(false)
        }
    }

    private val onPlay: () -> Unit = {
        radioNotificationBuilder = buildRadioNotification(this, mediaSession!!)
        registerReceiver(
            noisyReceiver,
            IntentFilter(AudioManager.ACTION_AUDIO_BECOMING_NOISY)
        )

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            startForeground(
                FOREGROUND_SERVICE_ID,
                radioNotificationBuilder.build(),
                ServiceInfo.FOREGROUND_SERVICE_TYPE_MEDIA_PLAYBACK
            )
        } else {
            startForeground(
                FOREGROUND_SERVICE_ID,
                radioNotificationBuilder.build()
            )
        }
    }

    private var mediaSession: MediaSessionCompat? = null

    private val stateBuilder: PlaybackStateCompat.Builder by lazy {
        PlaybackStateCompat.Builder()
            .setActions(
                PlaybackStateCompat.ACTION_PLAY
                        or PlaybackStateCompat.ACTION_PLAY_PAUSE
            )
    }

    private val sessionCallback: AudioPlayerMediaSessionCallback by lazy {
        AudioPlayerMediaSessionCallback(
            context = baseContext,
            mediaSessionCompat = mediaSession,
            onPlay = onPlay,
            onStop = onStop
        )
    }

    private lateinit var radioNotificationBuilder: NotificationCompat.Builder

    override fun onCreate() {
        super.onCreate()
        val sessionActivityPendingIntent =
            packageManager?.getLaunchIntentForPackage(packageName)?.let { sessionIntent ->
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    PendingIntent.getActivity(this, 0, sessionIntent, PendingIntent.FLAG_IMMUTABLE)
                } else {
                    PendingIntent.getActivity(this, 0, sessionIntent, 0)
                }
            }

        mediaSession = MediaSessionCompat(baseContext, TAG).apply {
            setSessionActivity(sessionActivityPendingIntent)
            isActive = true

            setFlags(
                MediaSessionCompat.FLAG_HANDLES_MEDIA_BUTTONS
                        or MediaSessionCompat.FLAG_HANDLES_TRANSPORT_CONTROLS
            )

            setPlaybackState(stateBuilder.build())

            setCallback(sessionCallback)
        }

        sessionToken = mediaSession?.sessionToken

        createRadioNotificationChannel(baseContext)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return super.onStartCommand(intent, flags, startId)
    }


    override fun onGetRoot(
        clientPackageName: String,
        clientUid: Int,
        rootHints: Bundle?
    ): BrowserRoot {
        val extras = Bundle()
        extras.putInt(
            MediaConstants.DESCRIPTION_EXTRAS_KEY_CONTENT_STYLE_BROWSABLE,
            MediaConstants.DESCRIPTION_EXTRAS_VALUE_CONTENT_STYLE_GRID_ITEM
        )
        extras.putInt(
            MediaConstants.DESCRIPTION_EXTRAS_KEY_CONTENT_STYLE_PLAYABLE,
            MediaConstants.DESCRIPTION_EXTRAS_VALUE_CONTENT_STYLE_LIST_ITEM
        )
        return if (allowBrowsing(clientPackageName, clientUid)) BrowserRoot(
            MY_MEDIA_ROOT_ID,
            extras
        ) else BrowserRoot(
            EMPTY_MEDIA_ROOT_ID, null
        )
    }

    override fun onLoadChildren(
        parentId: String,
        result: Result<MutableList<MediaBrowserCompat.MediaItem>>
    ) {
        if (parentId == EMPTY_MEDIA_ROOT_ID) {
            result.sendResult(null)
            return
        }

        if (parentId == MY_MEDIA_ROOT_ID) {
            Log.d(TAG, "onLoadChildren: root media id")
            result.sendResult(mutableListOf(getRootBrowsableMediaItem()))
        } else {
            Log.d(TAG, "onLoadChildren: not root media id")
            result.sendResult(mutableListOf(getMediaItem()))
        }
    }

    //TODO: refactor this logic
    private fun allowBrowsing(clientPackageName: String, clientUid: Int): Boolean {
        Log.d(TAG, "allowBrowsing: $clientPackageName, $clientUid")
        return true
    }

    private fun getRootBrowsableMediaItem(id: String = ROOT_MEDIA_ITEM_ID): MediaBrowserCompat.MediaItem {
        return MediaDescriptionCompat.Builder()
            .setMediaId(id)
            .setTitle(ROOT_MEDIA_ITEM_TITLE)
            .setIconUri(Uri.parse(MEDIA_ITEM_ICON_URI))
            .let { builder ->
                return@let MediaBrowserCompat.MediaItem(
                    builder.build(),
                    MediaBrowserCompat.MediaItem.FLAG_BROWSABLE
                )
            }
    }

    private fun getMediaItem(): MediaBrowserCompat.MediaItem {
        return MediaDescriptionCompat.Builder()
            .setMediaId(NOGOUM_FM_MEDIA_ITEM_ID)
            .setTitle(NOGOUM_FM_MEDIA_ITEM_TITLE)
            .setIconUri(Uri.parse(MEDIA_ITEM_ICON_URI))
            .let { builder ->
                return@let MediaBrowserCompat.MediaItem(
                    builder.build(),
                    MediaBrowserCompat.MediaItem.FLAG_PLAYABLE
                )
            }
    }

}