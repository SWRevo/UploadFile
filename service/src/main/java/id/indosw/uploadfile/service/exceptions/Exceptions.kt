package id.indosw.uploadfile.service.exceptions

import id.indosw.uploadfile.service.network.ServerResponse

class UserCancelledUploadException : Throwable("User cancelled upload")
class UploadError(val serverResponse: ServerResponse) : Throwable("Upload error")
