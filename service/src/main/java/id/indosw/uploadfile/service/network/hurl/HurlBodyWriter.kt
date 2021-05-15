package id.indosw.uploadfile.service.network.hurl

import java.io.IOException
import java.io.OutputStream
import id.indosw.uploadfile.service.network.BodyWriter

class HurlBodyWriter(private val stream: OutputStream, listener: OnStreamWriteListener) :
    BodyWriter(listener) {
    @Throws(IOException::class)
    override fun internalWrite(bytes: ByteArray) {
        stream.write(bytes)
    }

    @Throws(IOException::class)
    override fun internalWrite(bytes: ByteArray, lengthToWriteFromStart: Int) {
        stream.write(bytes, 0, lengthToWriteFromStart)
    }

    @Throws(IOException::class)
    override fun flush() {
        stream.flush()
    }

    @Throws(IOException::class)
    override fun close() {
        stream.close()
    }
}
