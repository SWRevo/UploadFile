package id.indosw.uploadfile.service.observer.request

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import id.indosw.uploadfile.service.UploadService
import id.indosw.uploadfile.service.UploadServiceConfig.broadcastNotificationAction
import id.indosw.uploadfile.service.UploadServiceConfig.broadcastNotificationActionIntentFilter
import id.indosw.uploadfile.service.extensions.uploadIdToCancel
import id.indosw.uploadfile.service.logger.UploadServiceLogger
import id.indosw.uploadfile.service.logger.UploadServiceLogger.NA

open class NotificationActionsObserver(
    private val context: Context
) : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent?.action != broadcastNotificationAction) return
        onActionIntent(intent)
    }

    open fun onActionIntent(intent: Intent) {
        intent.uploadIdToCancel?.let {
            UploadServiceLogger.info(NotificationActionsObserver::class.java.simpleName, it) {
                "requested upload cancellation"
            }
            UploadService.stopUpload(it)
        }
    }

    fun register() {
        context.registerReceiver(this, broadcastNotificationActionIntentFilter)
        UploadServiceLogger.debug(NotificationActionsObserver::class.java.simpleName, NA) {
            "registered"
        }
    }

    fun unregister() {
        context.unregisterReceiver(this)
        UploadServiceLogger.debug(NotificationActionsObserver::class.java.simpleName, NA) {
            "unregistered"
        }
    }
}
