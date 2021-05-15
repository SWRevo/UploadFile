package id.indosw.uploadfile.service.extensions

import id.indosw.uploadfile.service.data.NameValue

fun ArrayList<NameValue>.addHeader(name: String, value: String) {
    add(NameValue(name, value).validateAsHeader())
}

fun LinkedHashMap<String, String>.setOrRemove(key: String, value: String?) {
    if (value == null) {
        remove(key)
    } else {
        this[key] = value
    }
}
