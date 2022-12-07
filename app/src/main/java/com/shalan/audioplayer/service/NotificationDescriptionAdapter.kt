package com.shalan.audioplayer.service

import android.app.PendingIntent
import android.graphics.Bitmap
import android.support.v4.media.session.MediaControllerCompat
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.ui.PlayerNotificationManager

class NotificationDescriptionAdapter(private val controller: MediaControllerCompat) :
    PlayerNotificationManager.MediaDescriptionAdapter {

    override fun getCurrentContentTitle(player: Player): CharSequence =
        controller.metadata?.description?.title ?: ""

    override fun createCurrentContentIntent(player: Player): PendingIntent? =
        controller.sessionActivity

    override fun getCurrentContentText(player: Player): CharSequence? =
        controller.metadata?.description?.description

    override fun getCurrentLargeIcon(
        player: Player,
        callback: PlayerNotificationManager.BitmapCallback
    ): Bitmap? = controller.metadata?.description?.iconBitmap
}