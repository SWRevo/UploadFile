package id.indosw.uploadfile.service.protocols.multipart

import id.indosw.uploadfile.service.data.UploadFile
import id.indosw.uploadfile.service.extensions.setOrRemove

// properties associated to each file
private const val PROPERTY_PARAM_NAME = "multipartParamName"
private const val PROPERTY_REMOTE_FILE_NAME = "multipartRemoteFileName"
private const val PROPERTY_CONTENT_TYPE = "multipartContentType"

internal var UploadFile.parameterName: String?
    get() = properties[PROPERTY_PARAM_NAME]
    set(value) {
        properties.setOrRemove(PROPERTY_PARAM_NAME, value)
    }

internal var UploadFile.remoteFileName: String?
    get() = properties[PROPERTY_REMOTE_FILE_NAME]
    set(value) {
        properties.setOrRemove(PROPERTY_REMOTE_FILE_NAME, value)
    }

internal var UploadFile.contentType: String?
    get() = properties[PROPERTY_CONTENT_TYPE]
    set(value) {
        properties.setOrRemove(PROPERTY_CONTENT_TYPE, value)
    }
