package id.indosw.uploadfile.service.observer.task

import id.indosw.uploadfile.service.data.UploadInfo
import id.indosw.uploadfile.service.data.UploadNotificationConfig
import id.indosw.uploadfile.service.network.ServerResponse

interface UploadTaskObserver {
    fun onStart(info: UploadInfo, notificationId: Int, notificationConfig: UploadNotificationConfig)

    fun onProgress(
        info: UploadInfo,
        notificationId: Int,
        notificationConfig: UploadNotificationConfig
    )

    fun onSuccess(
        info: UploadInfo,
        notificationId: Int,
        notificationConfig: UploadNotificationConfig,
        response: ServerResponse
    )

    fun onError(
        info: UploadInfo,
        notificationId: Int,
        notificationConfig: UploadNotificationConfig,
        exception: Throwable
    )

    fun onCompleted(
        info: UploadInfo,
        notificationId: Int,
        notificationConfig: UploadNotificationConfig
    )
}
