package io.linkey.app.android.domain.entity

sealed class Resource<T>(
    val data : T? = null,
    val code : Int = 0,
    val message : String = ""
) {
    class Success<T>(data: T?) : Resource<T>(data)

    class Error<T>(
        code : Int,
        message: String
    ): Resource<T>(code = code, message = message)

    class Loading<T> : Resource<T>()
}