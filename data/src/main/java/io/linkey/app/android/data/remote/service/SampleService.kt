package io.linkey.app.android.data.remote.service

import io.linkey.app.android.data.remote.dto.Page
import io.linkey.app.android.data.remote.dto.sample.WineDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface SampleService {

    @GET("/wines/{wine}")
    suspend fun getRedsWines(
        @Path("wine") wine : String
    ): Response<List<WineDto>>

    @GET("/wines/{wine}/{wineId}")
    suspend fun getRedWhineById(
        @Path("wine") wine : String,
        @Path("wineId") coinId : Long
    ): Response<WineDto>

}