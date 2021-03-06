package id.indosw.uploadfile.service.data

import android.app.PendingIntent
import android.os.Parcelable
import androidx.core.app.NotificationCompat
import kotlinx.android.parcel.Parcelize

/**
 * Class which represents a notification action.
 * It is necessary because NotificationCompat.Action is not serializable or Parcelable, thus it's
 * not possible to pass it directly in the intents.
 */
@Parcelize
data class UploadNotificationAction(
    val icon: Int,
    val title: CharSequence,
    val intent: PendingIntent
) : Parcelable {
    fun asAction(): NotificationCompat.Action {
        return NotificationCompat.Action.Builder(icon, title, intent).build()
    }
}
