package io.linkey.app.android.data.remote.dto.sample

import io.linkey.app.android.domain.entity.wine.Wine
import io.linkey.app.android.domain.entity.wine.WineDetail

data class WineDto(
    val id: Int,
    val image: String,
    val location: String,
    val rating: RatingDto,
    val wine: String,
    val winery: String
)

fun WineDto.toWine() = Wine(
    id = id,
    image = image,
    rating = rating.toRating(),
    wine = wine,
)

fun WineDto.toWineDetail() = WineDetail(
    id = id,
    image = image,
    location = location,
    rating = rating.toRating(),
    wine = wine,
    winery = winery
)
