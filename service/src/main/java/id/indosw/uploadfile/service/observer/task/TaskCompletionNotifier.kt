package id.indosw.uploadfile.service.observer.task

import id.indosw.uploadfile.service.UploadService
import id.indosw.uploadfile.service.data.UploadInfo
import id.indosw.uploadfile.service.data.UploadNotificationConfig
import id.indosw.uploadfile.service.network.ServerResponse

class TaskCompletionNotifier(private val service: UploadService) : UploadTaskObserver {
    override fun onStart(
        info: UploadInfo,
        notificationId: Int,
        notificationConfig: UploadNotificationConfig
    ) {
    }

    override fun onProgress(
        info: UploadInfo,
        notificationId: Int,
        notificationConfig: UploadNotificationConfig
    ) {
    }

    override fun onSuccess(
        info: UploadInfo,
        notificationId: Int,
        notificationConfig: UploadNotificationConfig,
        response: ServerResponse
    ) {
    }

    override fun onError(
        info: UploadInfo,
        notificationId: Int,
        notificationConfig: UploadNotificationConfig,
        exception: Throwable
    ) {
    }

    override fun onCompleted(
        info: UploadInfo,
        notificationId: Int,
        notificationConfig: UploadNotificationConfig
    ) {
        service.taskCompleted(info.uploadId)
    }
}
