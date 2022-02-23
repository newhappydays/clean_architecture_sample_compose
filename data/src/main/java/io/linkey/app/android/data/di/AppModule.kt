package io.linkey.app.android.data.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.linkey.app.android.data.room.DatabaseHelper
import io.linkey.app.android.data.room.dao.TodoDao
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideTodoDatabase(app : Application) = Room.databaseBuilder(
        app,
        DatabaseHelper::class.java,
        DatabaseHelper.DATABASE_NAME
    ).build()
}