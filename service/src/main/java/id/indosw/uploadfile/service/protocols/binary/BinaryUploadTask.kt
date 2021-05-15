package id.indosw.uploadfile.service.protocols.binary

import id.indosw.uploadfile.service.HttpUploadTask
import id.indosw.uploadfile.service.extensions.addHeader
import id.indosw.uploadfile.service.network.BodyWriter
import java.util.*

/**
 * Implements a binary file upload task.
 */
class BinaryUploadTask : HttpUploadTask() {
    private val file by lazy { params.files.first().handler }

    override val bodyLength: Long
        get() = file.size(context)

    override fun performInitialization() {
        with(httpParams.requestHeaders) {
            if (none { it.name.toLowerCase(Locale.getDefault()) == "content-type" }) {
                addHeader("Content-Type", file.contentType(context))
            }
        }
    }

    override fun onWriteRequestBody(bodyWriter: BodyWriter) {
        bodyWriter.writeStream(file.stream(context))
    }
}
