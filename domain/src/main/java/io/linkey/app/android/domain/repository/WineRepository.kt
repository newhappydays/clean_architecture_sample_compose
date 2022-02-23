package io.linkey.app.android.domain.repository

import io.linkey.app.android.domain.entity.Resource
import io.linkey.app.android.domain.entity.wine.Wine
import io.linkey.app.android.domain.entity.wine.WineDetail
import kotlinx.coroutines.flow.Flow

interface WineRepository {

    fun getWines(wine : String) : Flow<Resource<List<Wine>>>

    fun getWineById(wine : String, sampleId : Long) : Flow<Resource<WineDetail>>

}