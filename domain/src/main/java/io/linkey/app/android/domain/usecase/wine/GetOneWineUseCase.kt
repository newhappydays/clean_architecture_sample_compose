package io.linkey.app.android.domain.usecase.wine

import io.linkey.app.android.domain.repository.WineRepository
import javax.inject.Inject

class GetOneWineUseCase @Inject constructor(
    private val sampleRepository: WineRepository
) {
    fun invoke(
        wine : String,
        wineId : Long
    ) = sampleRepository.getWineById(wine, wineId)
}