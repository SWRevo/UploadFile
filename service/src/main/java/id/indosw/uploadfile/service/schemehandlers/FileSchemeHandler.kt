package id.indosw.uploadfile.service.schemehandlers

import android.content.Context
import id.indosw.uploadfile.service.extensions.autoDetectMimeType
import id.indosw.uploadfile.service.logger.UploadServiceLogger
import id.indosw.uploadfile.service.logger.UploadServiceLogger.NA
import java.io.File
import java.io.FileInputStream
import java.io.IOException

internal class FileSchemeHandler : SchemeHandler {
    private lateinit var file: File

    override fun init(path: String) {
        file = File(path)
    }

    override fun size(context: Context) = file.length()

    override fun stream(context: Context) = FileInputStream(file)

    override fun contentType(context: Context) = file.absolutePath.autoDetectMimeType()

    override fun name(context: Context) = file.name
        ?: throw IOException("Can't get file name for ${file.absolutePath}")

    override fun delete(context: Context) = try {
        file.delete()
    } catch (exc: Throwable) {
        UploadServiceLogger.error(javaClass.simpleName, NA, exc) { "File deletion error" }
        false
    }
}
