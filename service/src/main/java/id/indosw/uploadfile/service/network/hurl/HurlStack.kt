package id.indosw.uploadfile.service.network.hurl

import id.indosw.uploadfile.service.UploadServiceConfig
import id.indosw.uploadfile.service.network.HttpRequest
import id.indosw.uploadfile.service.network.HttpStack
import java.io.IOException

class HurlStack @JvmOverloads constructor(
    private val userAgent: String = UploadServiceConfig.defaultUserAgent,
    private val followRedirects: Boolean = true,
    private val useCaches: Boolean = false,
    private val connectTimeoutMillis: Int = 15000,
    private val readTimeoutMillis: Int = 30000
) : HttpStack {

    @Throws(IOException::class)
    override fun newRequest(uploadId: String, method: String, url: String): HttpRequest {
        return HurlStackRequest(
            userAgent, uploadId, method, url, followRedirects, useCaches,
            connectTimeoutMillis, readTimeoutMillis
        )
    }
}
