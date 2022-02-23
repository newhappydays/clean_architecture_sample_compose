package io.linkey.app.android.domain.entity

sealed class PagingResource<T>(
    val data : T? = null,
    val totalElements : Int = 0,
    val last : Boolean = true,
    val pageNumber : Int = 0,
    val code : Int = 0,
    val message : String = "",
) {
    class Success<T>(
        data : T?,
        totalElements: Int,
        last: Boolean,
        pageNumber : Int
    ) : PagingResource<T>(data, totalElements, last, pageNumber)

    class Error<T>(
        code : Int,
        message: String
    ): PagingResource<T>(code = code, message = message)

    class Loading<T> : PagingResource<T>()
}