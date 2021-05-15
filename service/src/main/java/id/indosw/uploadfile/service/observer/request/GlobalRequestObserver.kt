package id.indosw.uploadfile.service.observer.request

import android.app.Application
import id.indosw.uploadfile.service.data.UploadInfo

class GlobalRequestObserver @JvmOverloads constructor(
    application: Application,
    delegate: RequestObserverDelegate,
    shouldAcceptEventsFrom: (uploadInfo: UploadInfo) -> Boolean = { true }
) : BaseRequestObserver(application, delegate, shouldAcceptEventsFrom) {
    init {
        register()
    }
}
