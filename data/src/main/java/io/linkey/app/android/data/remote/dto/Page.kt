package io.linkey.app.android.data.remote.dto

import com.google.gson.annotations.SerializedName

data class Page<T>(
    val content : List<T>,
    @SerializedName("totalElements")
    val totalElements : Int,
    val last : Boolean,
    @SerializedName("number")
    val number : Int
)
