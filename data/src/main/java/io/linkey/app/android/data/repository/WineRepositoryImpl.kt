package io.linkey.app.android.data.repository

import io.linkey.app.android.data.remote.dto.sample.toWine
import io.linkey.app.android.data.remote.dto.sample.toWineDetail
import io.linkey.app.android.data.remote.service.SampleService
import io.linkey.app.android.domain.repository.WineRepository

class WineRepositoryImpl(
    private val sampleService: SampleService
) : WineRepository ,BaseRepository() {

    override fun getWines(wine : String) = safeApiCall(
        { sampleService.getRedsWines(wine) },
        { data -> data.map { it.toWine() } }
    )

    override fun getWineById(
        wine: String,
        wineId: Long
    ) = safeApiCall(
        { sampleService.getRedWhineById(wine, wineId) },
        { it.toWineDetail() }
    )
}