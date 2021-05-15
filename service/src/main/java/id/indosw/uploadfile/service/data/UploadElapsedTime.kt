package id.indosw.uploadfile.service.data

data class UploadElapsedTime(val minutes: Int, val seconds: Int) {
    val totalSeconds: Int
        get() = minutes * 60 + seconds
}
