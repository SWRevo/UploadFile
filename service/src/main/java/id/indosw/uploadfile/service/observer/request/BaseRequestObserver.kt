package id.indosw.uploadfile.service.observer.request

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import id.indosw.uploadfile.service.UploadServiceConfig
import id.indosw.uploadfile.service.data.BroadcastData
import id.indosw.uploadfile.service.data.UploadInfo
import id.indosw.uploadfile.service.data.UploadStatus

open class BaseRequestObserver(
    private val context: Context,
    internal val delegate: RequestObserverDelegate,
    internal var shouldAcceptEventsFrom: (uploadInfo: UploadInfo) -> Boolean
) : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent?) {
        val safeIntent = intent ?: return
        if (safeIntent.action != UploadServiceConfig.broadcastStatusAction) return
        val data = BroadcastData.fromIntent(safeIntent) ?: return

        val uploadInfo = data.uploadInfo

        if (!shouldAcceptEventsFrom(uploadInfo)) {
            return
        }

        when (data.status) {
            UploadStatus.InProgress -> delegate.onProgress(context, uploadInfo)
            UploadStatus.Error -> delegate.onError(context, uploadInfo, data.exception!!)
            UploadStatus.Success -> delegate.onSuccess(context, uploadInfo, data.serverResponse!!)
            UploadStatus.Completed -> delegate.onCompleted(context, uploadInfo)
        }
    }

    open fun register() {
        context.registerReceiver(this, UploadServiceConfig.broadcastStatusIntentFilter)
    }

    open fun unregister() {
        context.unregisterReceiver(this)
    }
}
