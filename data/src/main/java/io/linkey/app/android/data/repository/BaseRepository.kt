package io.linkey.app.android.data.repository

import android.util.Log
import io.linkey.app.android.data.remote.dto.Page
import io.linkey.app.android.domain.entity.PagingResource
import io.linkey.app.android.domain.entity.Resource
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import retrofit2.Response

open class BaseRepository {

    inline fun<T, R> safeApiCall(
        crossinline apiFunction : suspend () -> Response<T>,
        crossinline mapping : (result : T) -> R
    ) = flow {
        try {
            emit(Resource.Loading<R>())
            val res = apiFunction()
            if (res.isSuccessful) {
                emit(
                    Resource.Success(
                        mapping(
                            res.body()!!
                        )
                    )
                )
            } else {
                emit(Resource.Error<R>(res.code(), "${res.code()} - ${res.message()}"))
            }

        } catch (e : Exception) {
            e.printStackTrace()
            emit(Resource.Error<R>(999, errorMessage(e)))

        }
    }

    inline fun <T> nonBodySafeApiCall(
        crossinline apiFunction : suspend () -> Response<T>
    ) = flow {
        try {
            val res = apiFunction()
            if (res.isSuccessful) {
                emit(Resource.Success(true))
            } else {
                emit(Resource.Error<Boolean>(res.code(), "${res.code()} - ${res.message()}"))
            }

        } catch (e : Exception) {
            e.printStackTrace()
            emit(Resource.Error<Boolean>(999, errorMessage(e)))

        }
    }

    inline fun<T, R> pagingSafeApiCall(
        crossinline apiFunction : suspend () -> Response<Page<T>>,
        crossinline mapping : (result : List<T>) -> R
    ) = flow {
        try {
            emit(PagingResource.Loading())

            val res = apiFunction()
            if (res.isSuccessful) {
                val body = res.body()!!
                delay(300)
                emit(
                    PagingResource.Success(
                        mapping(body.content),
                        body.totalElements,
                        body.last,
                        body.number
                    )
                )
            } else {
                emit(PagingResource.Error<R>(res.code(), "${res.code()} - ${res.message()}"))
            }

        } catch (e : Exception) {
            e.printStackTrace()
            emit(PagingResource.Error<R>(999, errorMessage(e)))
        }
    }

    fun errorMessage(e: Exception): String {
        val message = "${e.message}"
        Log.e("TAG", "errorMessage = $e")

        return when {
            message.contains("No address associated with hostname") -> {
                "인터넷 연결 상태를 확인해주세요."
            }
            message.contains("java.net.SocketTimeoutException: timeout") -> {
                "서버가 원활하지 않습니다. 관리자에게 문의해주세요."
            }
            else -> "알 수 없는 에러, 관리자에게 문의해주세요."
        }
    }

    companion object {
        const val BadRequest = 400
        const val Unauthorized = 401
        const val NotFound = 404
        const val ServerError = 500
        const val BadGateWay = 502
        const val UnKnownHttpError = 9999
    }

}