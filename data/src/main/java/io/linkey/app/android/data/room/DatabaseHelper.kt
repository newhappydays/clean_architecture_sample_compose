package io.linkey.app.android.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import io.linkey.app.android.data.room.dao.TodoDao
import io.linkey.app.android.data.room.entity.TodoEntity

@Database(entities = [
    TodoEntity::class
], version = 1)
abstract class DatabaseHelper : RoomDatabase() {

    abstract val todoDao : TodoDao

    companion object {
        const val DATABASE_NAME = "todo_database"
    }

}