package io.linkey.app.android.domain.entity.wine

data class Wine(
    val id: Int,
    val image: String,
    val rating: Rating,
    val wine: String,
)
