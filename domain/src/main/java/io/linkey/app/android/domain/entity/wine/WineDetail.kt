package io.linkey.app.android.domain.entity.wine

data class WineDetail(
    val id: Int,
    val image: String,
    val location: String,
    val rating: Rating,
    val wine: String,
    val winery: String
)
