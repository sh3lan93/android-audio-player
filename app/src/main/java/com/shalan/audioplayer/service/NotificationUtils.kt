package com.shalan.audioplayer.service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.support.v4.media.session.MediaControllerCompat
import android.support.v4.media.session.MediaSessionCompat
import android.support.v4.media.session.PlaybackStateCompat
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import androidx.media.session.MediaButtonReceiver
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.ui.PlayerNotificationManager
import com.shalan.audioplayer.R


const val RADIO_NOTIFICATION_CHANNEL_ID = "RADIO"

fun createRadioNotificationChannel(context: Context) {

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val radioNotificationChannel = NotificationChannel(
            RADIO_NOTIFICATION_CHANNEL_ID,
            "Playing radio",
            NotificationManager.IMPORTANCE_DEFAULT
        )
        radioNotificationChannel.description = "Notification for playing radio in background"

        (context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager).let { notificationManager ->
            notificationManager.createNotificationChannel(radioNotificationChannel)
        }
    }
}

fun buildRadioNotification(
    context: Context,
    mediaSession: MediaSessionCompat,
): NotificationCompat.Builder {


    val mediaDescription = MediaControllerCompat(context, mediaSession.sessionToken).metadata?.description

    return NotificationCompat.Builder(context, RADIO_NOTIFICATION_CHANNEL_ID).apply {
        mediaDescription?.let {
            with(mediaDescription) {
                setContentTitle(title)
                setContentText(subtitle)
                setSubText(description)
                setLargeIcon(iconBitmap)
            }
        }

        setContentIntent(mediaSession.controller?.sessionActivity)

        setDeleteIntent(
            MediaButtonReceiver.buildMediaButtonPendingIntent(
                context,
                PlaybackStateCompat.ACTION_STOP
            )
        )
        setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
        setAutoCancel(false)
        setSmallIcon(R.drawable.ic_notification)
        color = ContextCompat.getColor(context, android.R.color.background_dark)
        addAction(
            NotificationCompat.Action(
                R.drawable.ic_baseline_pause_circle_24,
                context.getString(R.string.pause),
                MediaButtonReceiver.buildMediaButtonPendingIntent(
                    context,
                    PlaybackStateCompat.ACTION_PLAY_PAUSE
                )
            )
        )
        setStyle(
            androidx.media.app.NotificationCompat.MediaStyle()
                .setMediaSession(mediaSession.sessionToken)
                .setShowActionsInCompactView(0)
                .setShowCancelButton(false)
        )
    }
}

fun buildMediaNotification(
    context: Context,
    sessionToken: MediaSessionCompat.Token,
    @androidx.annotation.IntRange(from = 1) notificationId: Int,
    player: Player
): PlayerNotificationManager {
    val controller = MediaControllerCompat(context, sessionToken)
    return PlayerNotificationManager.Builder(context, notificationId, RADIO_NOTIFICATION_CHANNEL_ID)
        .setMediaDescriptionAdapter(NotificationDescriptionAdapter(controller))
        .build().apply {
            setColorized(true)
            setMediaSessionToken(sessionToken)
            setPlayer(player)
            setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
            setUseChronometer(true)
            setSmallIcon(R.drawable.ic_notification)
            this.setUseRewindAction(false)
        }
}



