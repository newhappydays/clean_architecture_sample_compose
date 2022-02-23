package io.linkey.app.android.data.remote.dto.sample

import io.linkey.app.android.domain.entity.wine.Rating

data class RatingDto(
    val average: String,
    val reviews: String
)

fun RatingDto.toRating() = Rating(
    average = average,
    reviews = reviews
)