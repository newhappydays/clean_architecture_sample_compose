package io.linkey.app.android.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.linkey.app.android.data.remote.service.SampleService
import io.linkey.app.android.data.repository.TodoRepositoryImpl
import io.linkey.app.android.data.repository.WineRepositoryImpl
import io.linkey.app.android.data.room.DatabaseHelper
import io.linkey.app.android.data.room.dao.TodoDao
import io.linkey.app.android.domain.repository.TodoRepository
import io.linkey.app.android.domain.repository.WineRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun getSampleRepository(
        sampleService: SampleService
    ) : WineRepository = WineRepositoryImpl(sampleService)

    @Provides
    @Singleton
    fun getTodoRepository(
        db : DatabaseHelper
    ) : TodoRepository = TodoRepositoryImpl(db.todoDao)
}